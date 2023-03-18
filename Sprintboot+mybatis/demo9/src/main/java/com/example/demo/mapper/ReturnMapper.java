package com.example.demo.mapper;


import com.example.demo.enty.BorrowInfo;
import com.example.demo.enty.ReturnInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
@Repository
public interface ReturnMapper {
    List<ReturnInfo> ReturnAll();

    List<ReturnInfo> ReturnById(@RequestParam("bid")String bid);

    List<ReturnInfo> ReturnInfoByIndex(@RequestParam("bindex")String bindex);
}
