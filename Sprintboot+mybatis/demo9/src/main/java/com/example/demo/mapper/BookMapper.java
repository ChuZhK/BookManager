package com.example.demo.mapper;

import com.example.demo.enty.BookInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Mapper
@Repository
public interface BookMapper {

    List<BookInfo> getAllBooks();

    List<BookInfo> getByName(@RequestParam("name") String name);

    List<BookInfo> getByWer(@RequestParam("auther") String auther);

    List<BookInfo> getByIdx(@RequestParam("bindex") String bindex);

    List<BookInfo> getByIsbn(@RequestParam("isbn") String isbn);

    //改变书籍状态 --> 借出
    @Transactional
    void lendBookBook(@RequestParam("bindex") String bindex);

    @Transactional
    void lendBookBorrow(@RequestParam("bindex") String bindex, @RequestParam("uid") int uid);

    @Transactional
    void lendBookUser(@RequestParam("uid") int uid);

    //确保书籍还没有被借阅
    @Transactional
    int isUnborrowed(@RequestParam("bindex") String bindex);

    //确认书籍被借阅
    @Transactional
    int isBorrowed(@RequestParam("bindex") String bindex);

    //改变书籍状态 --》 归还
    @Transactional
    void returnBookStatus(@RequestParam("bindex") String bindex);

    //插入还书记录
    @Transactional
    void returnBookInfo(@RequestParam("bindex") String bindex,@RequestParam("bid") int bid);

    //查找借书人
    @Transactional
    int returnBookUser(@RequestParam("bindex") String bindex);

    //更改读者还书量
    @Transactional
    void returnBookNum(@RequestParam("uid") int uid);

    //查询超期天数
    @Transactional
    int returnDays(@RequestParam("bindex")String bindex);

    //查询读者等级
    @Transactional
    int returnLev(@RequestParam("uid") int uid);

    //罚款 读者账户余额减少
    @Transactional
    void returnFine(@RequestParam("wage")double wage,@RequestParam("uid") int uid);


    //保存图书修改信息
    void saveBook(@RequestParam("name") String name,@RequestParam("isbn")String isbn,@RequestParam("price") BigDecimal price,
    @RequestParam("auther") String auther,@RequestParam("bindex")String bindex,@RequestParam("status")int status,@RequestParam("pub") String pub);

    //罚款
    void fineUserInBook(@RequestParam("uid")int uid, @RequestParam("wage")double wage);
    //保存信息后确认
    int confirmBook(@RequestParam("name") String name,@RequestParam("isbn")String isbn,@RequestParam("price") BigDecimal price,
                    @RequestParam("auther") String auther,@RequestParam("bindex")String bindex,@RequestParam("status")int status,@RequestParam("pub") String pub);

    void addBook(@RequestParam("name") String name,@RequestParam("isbn")String isbn,@RequestParam("price") BigDecimal price,
                 @RequestParam("auther") String auther,@RequestParam("bindex")String bindex,@RequestParam("status")int status,@RequestParam("pub") String pub);

    Boolean deleteBook(@RequestParam("bindex") String bindex);

    void saveBase(@RequestParam("name") String name,@RequestParam("isbn") String isbn,@RequestParam("price") BigDecimal price,
        @RequestParam("auther") String auther, @RequestParam("pub") String pub    );

    int confirmIsbn(@RequestParam("isbn")String isbn);

    int confirmBase(@RequestParam("name") String name,@RequestParam("isbn") String isbn,@RequestParam("price") BigDecimal price,
                    @RequestParam("auther") String auther, @RequestParam("pub") String pub);

    void deleteBase(@RequestParam("name") String name,@RequestParam("isbn") String isbn,@RequestParam("price") BigDecimal price,
                    @RequestParam("auther") String auther, @RequestParam("pub") String pub);


    int checkStatus(@RequestParam("bindex")String bindex);
}
