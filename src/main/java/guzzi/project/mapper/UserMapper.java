package guzzi.project.mapper;

import guzzi.project.DTO.userDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper {
    void signup(userDto paramMap);
    void createTokenTable(Map<String, Object> paramMap);
    void updateToken(Map<String, Object> refreshToken);
    int idChk(userDto paramMap);
    Map<String, Object> getUserData(userDto loginData) ;
    //accesstoken 으로 유저의 id 얻어서 userId값 반환
    void findByUserId();

    // access 만료 시 token 재발급을 위한 api
}


