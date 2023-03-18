package com.example.demo.controller;

import com.example.demo.Result;
import com.example.demo.enty.BaseInfo;
import com.example.demo.enty.BookInfo;
import com.example.demo.enty.ReturnBook;
import com.example.demo.mapper.BUserMapper;
import com.example.demo.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@EnableTransactionManagement
public class BookController {

    @Autowired
    private BookMapper mapper;

    @GetMapping("/getall")
    Result getAllBooks(){
        return Result.success(mapper.getAllBooks());
    }

    @GetMapping("/getName")
    Result getByName(@RequestParam("name") String name ){
        return Result.success(mapper.getByName(name));
    }

    @GetMapping("/getWriter")
    Result getByWer(@RequestParam("auther") String auther){
        return Result.success(mapper.getByWer(auther));
    }

    @GetMapping("/getIndex")
    Result getByIdx(@RequestParam("bindex") String bindex){
        return Result.success(mapper.getByIdx(bindex));
    }

    @GetMapping("/getByIsbn")
    Result getByIsbn(@RequestParam("isbn") String isbn){
        return Result.success(mapper.getByIsbn(isbn));
    }

    @PostMapping("/lendBook")
    @Transactional
    Result lendBook(@RequestBody Map map){

//        if(mapper.isUnborrowed(bindex)>0){
//            mapper.lendBookUser(uid); //更改用户借阅数量
//            mapper.lendBookBook(bindex); //改变书籍状态
//            mapper.lendBookBorrow(bindex,uid); //插入借阅记录
//            return Result.success(mapper.isBorrowed(bindex));
//        }
        if(mapper.isUnborrowed(map.get("bindex").toString())>0){
            mapper.lendBookUser(Integer.parseInt(map.get("uid").toString())); //更改用户借阅数量
            mapper.lendBookBook(map.get("bindex").toString()); //改变书籍状态
            mapper.lendBookBorrow(map.get("bindex").toString(),Integer.parseInt(map.get("uid").toString())); //插入借阅记录
            return Result.success(mapper.isBorrowed(map.get("bindex").toString()));
        }
        return Result.success(0);

    }


    @PostMapping("/returnBook")
    @Transactional
    Result returnBook(@RequestBody ReturnBook rtn_book){
        System.out.println(mapper.isBorrowed(rtn_book.bindex)>0);
        if(mapper.isBorrowed(rtn_book.bindex)>0){
            System.out.println("the book has been borrowed");
            //更改书籍状态
            mapper.returnBookStatus(rtn_book.bindex);
            //查找借书人
            int uid=mapper.returnBookUser(rtn_book.bindex);
            //插入还书记录
            mapper.returnBookInfo(rtn_book.bindex,uid);
            //更改读者还书量
            mapper.returnBookNum(uid);
            //查询是否超期
            int return_day=mapper.returnDays(rtn_book.bindex);
            System.out.println(return_day);
            //查询权限
            int user_lev=mapper.returnLev(uid);

            //计算罚款
            double wage=(return_day-user_lev*30 > 0? (return_day-(user_lev+1)*30)*0.01 : 0 );
            System.out.println(wage);
            System.out.println(return_day-(user_lev+1)*30);
            if(return_day-(user_lev+1)*30 > 0){
                mapper.returnFine(wage,uid);
                mapper.fineUserInBook(uid,rtn_book.getWage());
                return Result.success((return_day-(user_lev+1)*30)*(-0.01)- rtn_book.getWage());
            }
            else{
                System.out.println("-------->>>>>"+rtn_book.getWage());
                if(rtn_book.getWage()>0){
//                    mapper.fineUserInBook(uid,rtn_book.getWage());
                    return Result.success(0-rtn_book.getWage());
                }
                //确认还书完成
                return Result.success(mapper.isUnborrowed(rtn_book.bindex));
            }


        }
        return Result.success(0);
    }

    @PostMapping("/saveBook")
    Result saveBook(@RequestBody BookInfo binfo){
        mapper.saveBook(binfo.getName(), binfo.getIsbn(), binfo.getPrice(), binfo.getAuther(), binfo.getBindex(), binfo.getStatus(), binfo.getPub());
        return Result.success(mapper.confirmBook(binfo.getName(), binfo.getIsbn(), binfo.getPrice(), binfo.getAuther(), binfo.getBindex(), binfo.getStatus(), binfo.getPub()));
    }

    @PostMapping("/addBook")
    Result addBook(@RequestParam("name") String name,@RequestParam("isbn")String isbn,@RequestParam("price") BigDecimal price,
                   @RequestParam("auther") String auther,@RequestParam("bindex")String bindex,@RequestParam("status")int status,@RequestParam("pub") String pub){
        System.out.println(bindex);
        if(mapper.getByIdx(bindex).size()>0){
            return Result.success(-1);
        }
        mapper.addBook(name, isbn, price, auther, bindex, status, pub);
        return Result.success(mapper.confirmBook(name, isbn, price, auther, bindex, status, pub));
    }

    @PostMapping("/deleteBook")
    Result deleteBook(@RequestParam("bindex") String bindex){
        System.out.println("this is in delete book "+ bindex);
        if(mapper.getByIdx(bindex).size()==0 ){
            return Result.success(-1); //没有这本书
        }
//        if(mapper.checkStatus(bindex)==0){
//            return Result.success(-2);
//        }
        mapper.deleteBook(bindex);
        if(mapper.getByIdx(bindex).size()==0){
            return Result.success(1);
        }
        else {
            return Result.success(0); //删除失败
        }
    }

    @PostMapping("/saveBase") //保存基本图书信息
    Result saveBase(@RequestBody BaseInfo binfo){
        System.out.println("this is in saveBase function");

        if(mapper.getByIsbn(binfo.getIsbn()).size()==0){
            return Result.success(-1); //没有这书
        }
        mapper.saveBase(binfo.getName(), binfo.getIsbn(), binfo.getPrice(), binfo.getAuther(), binfo.getPub());
        int rows=mapper.confirmIsbn(binfo.getIsbn())-mapper.confirmBase(binfo.getName(), binfo.getIsbn(), binfo.getPrice(), binfo.getAuther(), binfo.getPub());
        System.out.println(rows);
        return Result.success(rows);
    }

    @PostMapping("/deleteBase")
    Result deleteBase(@RequestBody BaseInfo binfo){
        System.out.println("this is in delete Base"+binfo.getPrice());
        if(mapper.confirmBase(binfo.getName(), binfo.getIsbn(), binfo.getPrice(), binfo.getAuther(), binfo.getPub())==0){
            return Result.success(-1);
        }
        mapper.deleteBase(binfo.getName(), binfo.getIsbn(), binfo.getPrice(), binfo.getAuther(), binfo.getPub());
        return Result.success(mapper.confirmBase(binfo.getName(), binfo.getIsbn(), binfo.getPrice(), binfo.getAuther(), binfo.getPub()));
    }



}
