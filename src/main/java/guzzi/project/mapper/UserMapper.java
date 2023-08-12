package guzzi.project.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper {
    void signup(Map<String, Object> paramMap);
    int idChk(Map<String, Object> paramMap);
    //    public List<UserVO> findAllMemberBySelect();
    void login(Map<String, Object> paramMap) ;
    //accesstoken 으로 유저의 id 얻어서 userId값 반환
     void findByUserId();
    // access 만료 시 token 재발급을 위한 api
     void updateAccesstoken();
}


