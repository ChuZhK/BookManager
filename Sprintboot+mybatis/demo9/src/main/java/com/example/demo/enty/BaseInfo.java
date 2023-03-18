package com.example.demo.enty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseInfo {
    private String name;
    private String isbn;
    private BigDecimal price;
    private String auther;
    private String pub;
}
