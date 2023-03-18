package com.example.demo.mapper;

import com.example.demo.enty.PubInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
@Repository
public interface PubMapper {
    List<PubInfo> allPub();

    List<PubInfo> pubByName(@RequestParam("name") String name);

    int countBook(@RequestParam("name")String name);

    void deletePub(@RequestParam("id")int id);

    int pubById(@RequestParam("id")int id);

    List<PubInfo> pubByIdInfo(@RequestParam("id")int id);

    void addPub(@RequestParam("name")String name,@RequestParam("address")String address,@RequestParam("person")String person,@RequestParam("phone")String phone);

    int confirmAdd(@RequestParam("name")String name,@RequestParam("address")String address,@RequestParam("person")String person,@RequestParam("phone")String phone);

    void editPub(@RequestParam("id")int id,@RequestParam("name")String name,@RequestParam("address")String address,@RequestParam("person")String person,@RequestParam("phone")String phone);
}
