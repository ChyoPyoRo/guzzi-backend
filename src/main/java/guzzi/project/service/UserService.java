package guzzi.project.service;

import guzzi.project.vo.UserVO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public interface UserService {


     void signup(Map<String, Object> paramMap) throws SQLException, Exception;

    void login(Map<String,Object> paramMap) throws SQLException, Exception;



    //accesstoken 으로 유저의 id 얻어서 userId값 반환
    public void findByUserId() throws SQLException, Exception;

    // access 만료 시 token 재발급을 위한 api
    public void updateAccesstoken() throws SQLException, Exception;

}
