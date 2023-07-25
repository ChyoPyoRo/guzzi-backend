package guzzi.project.service;


import guzzi.project.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Map;

@Service("UserService")
public class userServiceImplement implements UserService {
    @Autowired
    UserDao userDao;


    /*
     * selectUserById - userId 로 user info 조회 (토큰 관련 로직에 연결)
     * @author 남현정
     * @param paramMap
     * @return paramMap
     * @exception SQLException
     * @exception Exception
     * */
    @Override
    public Map<String,Object> selectUserById(Map<String,Long> paramMap) throws SQLException,Exception{
//        Map<String,Object> userInfo = userDao.selectUserById(paramMap);
        return  userDao.selectUserById(paramMap);
    }





}
