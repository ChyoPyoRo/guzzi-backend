package com.project.guzzi.vote;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VoteServiceImpl implements MybatisTestService {
    public MybatisTestMapper mybatisTestMapper;

    @Autowired
    public void MybatisTestServiceImpl(MybatisTestMapper mybatisTestMapper){
        this.mybatisTestMapper = mybatisTestMapper;
    }

    @Override
    public List<Map<String, Object>> getTest(){
        return mybatisTestMapper.getTest();
    }
}
