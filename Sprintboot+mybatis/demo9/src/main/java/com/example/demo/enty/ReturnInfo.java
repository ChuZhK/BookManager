package com.example.demo.enty;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnInfo {
    private int return_index;
    private String bindex;
    private String bid;
    private Timestamp return_time;
}
