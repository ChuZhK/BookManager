package com.example.demo.mapper;

import com.example.demo.enty.BorrowInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
@Repository
public interface BorrowMapper {
    List<BorrowInfo> BorrowAll();

    List<BorrowInfo> BorrowById(@RequestParam("bid")String bid);

    List<BorrowInfo> BorrowInfoByIndex(@RequestParam("bindex")String bindex);
}
