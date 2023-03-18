package com.example.demo.enty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookInfo {

    private String name;
    private String isbn;
    private BigDecimal price;
    private String auther;
    private String bindex;
    private int    status;
    private String pub;

    BookInfo(String name,String isbn,BigDecimal price,String auther,String pub){
        this.name=name;
        this.isbn=isbn;
        this.price=price;
        this.auther=auther;
        this.pub=pub;
    }

}
