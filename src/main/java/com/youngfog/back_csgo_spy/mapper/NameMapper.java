package com.youngfog.back_csgo_spy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NameMapper {
    List<String> getAll();
}
