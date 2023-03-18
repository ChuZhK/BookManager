package com.example.demo.enty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowInfo {
    private int borrow_index;
    private String bindex;
    private String bid;
    private Timestamp borrow_time;
}
