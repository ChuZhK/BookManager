package com.example.demo.controller;

import com.example.demo.Result;
import com.example.demo.mapper.BookMapper;
import com.example.demo.mapper.BorrowMapper;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableTransactionManagement
public class BorrowInfoController {
    @Autowired
    private BorrowMapper mapper;

    @GetMapping("/BorrowAll")
    Result borrowAll(){
        return Result.success(mapper.BorrowAll());
    }

    @GetMapping("/BorrowById")
    Result borrowById(@RequestParam("bid") String bid){
        return Result.success(mapper.BorrowById(bid));
    }

    @GetMapping("/BorrowInfoByIndex")
    Result borrowInfoById(@RequestParam("bindex") String bindex){
        return Result.success(mapper.BorrowInfoByIndex(bindex));
    }
}
