package com.example.demo.enty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    private String uid;
    private String pwd;
    private int  lev;
    private int  sex;
    private String phone;
    private int borrow_num;
    private int return_num;
    private double wage;
}
