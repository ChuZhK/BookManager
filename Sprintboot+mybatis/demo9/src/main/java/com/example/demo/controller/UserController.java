package com.example.demo.controller;

import com.example.demo.Result;
import com.example.demo.enty.UserInfo;
import com.example.demo.mapper.BUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private BUserMapper mapper;

    @GetMapping ("/load")
    public Result getLoadInfo(@RequestParam("uid") int uid,@RequestParam("pwd") String pwd){
        System.out.print(uid);
        System.out.print(mapper.getLoadInfo(uid,pwd));
        if(mapper.getLoadInfo(uid,pwd)>0){
            return Result.success(mapper.getLevelById(uid));
        }
        return Result.success(-1);
    }

    @PostMapping("/fineUser")
    public Result fineUser(@RequestParam("uid")int uid,@RequestParam("wage")double wage){
        mapper.fineUser(uid,wage);
        return Result.success(0);
    }

    @GetMapping("/getAllUser")
    public Result getAllUser(){
        return Result.success(mapper.getAllUser());
    }

    @PostMapping("/deleteUser")
    public Result deleteUser(@RequestParam("uid") String uid){
        if(mapper.checkUser(uid)!=0){
            return Result.success(-1);
        }
        mapper.deleteUser(uid);
        return Result.success(mapper.countUid(uid));
    }

    @GetMapping("/getUserById")
    public Result getUserById(@RequestParam("uid") String uid){
        return Result.success(mapper.getUserById(uid));
    }

    @PostMapping("/addUser")
    public Result addUser(@RequestBody UserInfo uinfo){
        if(mapper.countUid(uinfo.getUid())>0){
            return Result.success(-1);
        }
        mapper.addUser(uinfo.getUid(),uinfo.getPwd(),uinfo.getLev(),uinfo.getSex(),uinfo.getPhone(),uinfo.getBorrow_num(),uinfo.getReturn_num(),uinfo.getWage());
        return Result.success(mapper.countUid(uinfo.getUid()));
    }

    @PostMapping("/editUser")
    public Result editUser(@RequestBody UserInfo uinfo){
        if(mapper.countUid(uinfo.getUid())==0){
            return Result.success(-1);
        }
        mapper.editUser(uinfo.getUid(),uinfo.getPwd(),uinfo.getLev(),uinfo.getSex(),uinfo.getPhone());
        return Result.success(mapper.editConfirm(uinfo.getUid(),uinfo.getPwd(),uinfo.getLev(),uinfo.getSex(),uinfo.getPhone()));
    }
}
