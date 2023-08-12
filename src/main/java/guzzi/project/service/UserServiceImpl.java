package guzzi.project.service;


import guzzi.project.exception.CustomException;
import guzzi.project.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Map;

import static guzzi.project.exception.ErrorCode.DUPLICATE_RESOURCE;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;
    @Override
    public void signup (Map<String, Object> paramMap) throws SQLException, Exception{
        System.out.println(paramMap);
        int idChkResult = userMapper.idChk(paramMap);
        System.out.println(idChkResult);
        if(idChkResult == 0) {
            userMapper.signup(paramMap);
        }else {
            throw new CustomException(DUPLICATE_RESOURCE);
        }

    }

    @Override
    public void login(Map<String,Object> paramMap) throws SQLException, Exception{
        userMapper.login(paramMap);

    }

    @Override
    public void findByUserId() throws SQLException, Exception{}

    @Override
    public void updateAccesstoken() throws SQLException, Exception{}


}
