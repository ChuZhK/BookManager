package com.example.demo.controller;

import com.example.demo.Result;
import com.example.demo.enty.PubInfo;
import com.example.demo.mapper.PubMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableTransactionManagement
public class PubController {
    @Autowired
    private PubMapper mapper;

    @GetMapping("/allPub")
    public Result allPub(){
        return Result.success(mapper.allPub());
    }

    @GetMapping("/pubByName")
    public Result pubById(@RequestParam("name") String name){
        return Result.success(mapper.pubByName(name));
    }

    @GetMapping("/pubByIdInfo")
    public Result pubById(@RequestParam("id") int id){
        return Result.success(mapper.pubByIdInfo(id));
    }


    @PostMapping("/deletePub")
    public Result deletePub(@RequestParam("name")String name,@RequestParam("id")int id){
        //如果还有书就不能删
        System.out.println("this is in deletPub fun...");
        if(mapper.countBook(name)>0 ){
            return Result.success(-1);
        }

        mapper.deletePub(id);
        return Result.success(mapper.pubById(id));
    }

    @PostMapping("/addPub")
    public Result addPub(@RequestBody PubInfo pinfo){
        if(mapper.confirmAdd(pinfo.getName(),pinfo.getAddress(),pinfo.getPerson(),pinfo.getPhone())>0 ){
            return Result.success(-1);
        }
        mapper.addPub(pinfo.getName(),pinfo.getAddress(),pinfo.getPerson(),pinfo.getPhone());
        return Result.success(mapper.confirmAdd(pinfo.getName(),pinfo.getAddress(),pinfo.getPerson(),pinfo.getPhone()));
    }

    @PostMapping("/editPub")
    public Result editPub(@RequestBody PubInfo pinfo){
        if(mapper.pubById(pinfo.getId())==0){
            return Result.success(-1);
        }
        mapper.editPub(pinfo.getId(),pinfo.getName(),pinfo.getAddress(),pinfo.getPerson(),pinfo.getPhone());
        return Result.success(mapper.confirmAdd(pinfo.getName(),pinfo.getAddress(),pinfo.getPerson(),pinfo.getPhone()));
    }
}
