package com.example.demo.controller;


import com.example.demo.Result;
import com.example.demo.mapper.BorrowMapper;
import com.example.demo.mapper.ReturnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableTransactionManagement
public class ReturnInfoController {

    @Autowired
    private ReturnMapper mapper;

    @GetMapping("/ReturnAll")
    Result borrowAll(){
        return Result.success(mapper.ReturnAll());
    }

    @GetMapping("/ReturnById")
    Result borrowById(@RequestParam("bid") String bid){
        return Result.success(mapper.ReturnById(bid));
    }

    @GetMapping("/ReturnInfoByIndex")
    Result borrowInfoById(@RequestParam("bindex") String bindex){
        return Result.success(mapper.ReturnInfoByIndex(bindex));
    }
}
