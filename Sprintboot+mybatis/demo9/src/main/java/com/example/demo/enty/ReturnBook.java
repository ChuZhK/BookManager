package com.example.demo.enty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


//还书接口 POST 请求的请求体
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnBook {
    public String bindex;
    public double wage;
}
