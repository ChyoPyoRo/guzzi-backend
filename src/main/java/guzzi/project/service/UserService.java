package guzzi.project.service;

import guzzi.project.DTO.userDto;

import java.sql.SQLException;
import java.util.Map;


public interface UserService {


     void signup(userDto paramMap) throws SQLException, Exception;

    Map<String, Object> login(userDto loginData) throws SQLException, Exception;



    //accesstoken 으로 유저의 id 얻어서 userId값 반환
    public void findByUserId() throws SQLException, Exception;

    // access 만료 시 token 재발급을 위한 api
    public Map<String, Object> updateAccessAndRefresh(String user_id) throws SQLException, Exception;

}
