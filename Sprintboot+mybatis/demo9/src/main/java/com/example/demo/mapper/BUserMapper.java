package com.example.demo.mapper;


import com.example.demo.enty.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
@Repository
public interface BUserMapper {
    int getLoadInfo(@RequestParam("uid") int uid,@RequestParam("pwd") String pwd);

    int getLevelById(@RequestParam("uid") int uid);

    void fineUser(@RequestParam("uid")int uid, @RequestParam("wage")double wage);

    List<UserInfo> getAllUser();

    void deleteUser(@RequestParam("uid")String uid);

    int countUid(@RequestParam("uid") String uid);

    UserInfo getUserById(@RequestParam("uid") String uid);

    void addUser(@RequestParam("uid") String uid,@RequestParam("pwd")String pwd,@RequestParam("lev")int lev,@RequestParam("sex")int sex,@RequestParam("phone")String phone, @RequestParam("borrow_num")int borrow_num,@RequestParam("return_num")int return_num,@RequestParam("wage")double wage);

    void editUser(@RequestParam("uid") String uid,@RequestParam("pwd")String pwd,@RequestParam("lev")int lev,@RequestParam("sex")int sex,@RequestParam("phone")String phone);


    int editConfirm(@RequestParam("uid") String uid,@RequestParam("pwd")String pwd,@RequestParam("lev")int lev,@RequestParam("sex")int sex,@RequestParam("phone")String phone);

    int checkUser(@RequestParam("uid")String uid);
}
