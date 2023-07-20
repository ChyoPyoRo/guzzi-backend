package com.project.guzzi.vote;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface MybatisTestMapper {
    List<Map<String, Object>> getTest();

}
