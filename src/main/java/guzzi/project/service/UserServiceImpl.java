package guzzi.project.service;


//import guzzi.project.config.SHA512PasswordEncoder;

import guzzi.project.DTO.userDto;
import guzzi.project.exception.CustomException;
import guzzi.project.mapper.UserMapper;
import guzzi.project.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static guzzi.project.exception.ErrorCode.DUPLICATE_RESOURCE;
import static guzzi.project.exception.ErrorCode.MEMBER_NOT_FOUND;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;

    @Autowired
    SecurityService securityService;

//    @Autowired
//    SHA512PasswordEncoder PasswordEncoder;

//    @Bean
//        public SHA512PasswordEncoder passwordEncoder() {
//            return new SHA512PasswordEncoder();
//        }

    @Override
    public void signup (userDto signupData) throws SQLException, Exception{
//        1. 비밀번호 암호화 하기 (보류)
//        2. 아이디 중복 확인
        int idChkResult = userMapper.idChk(signupData);
        if(idChkResult != 0) {
            throw new CustomException(DUPLICATE_RESOURCE);
        }
//        3. 회원가입
        userMapper.signup(signupData);
//        4. 생성된 유저 정보로 token table insert
        Map<String, Object> signupResult = userMapper.getUserData(signupData);
        userMapper.createTokenTable(signupResult);

    }

    @Override
    public Map<String, Object> login(userDto loginData) throws SQLException, Exception{

        // 1. 사용자 정보 조회
        Map<String, Object> loginResult = userMapper.getUserData(loginData);
            System.out.println("userService Impl login ===> loginResult");
            System.out.println(loginResult);

        // 1.1 user 정보 없으면 정보 없는 에러 발생
        if(loginResult == null){
            throw new CustomException(MEMBER_NOT_FOUND);
        }

        /*
        // 2. 비밀번호 복호화 해서 대조하기 (보류)
        String testPass = "abcd";
        String HashPW = passwordEncoder().encode(testPass);
        Boolean result = passwordEncoder().matches(testPass, HashPW);
        System.out.println("hash match result");
        String password = loginData.getPASSWORD();
        if(비번이 일치하지 않으면){
            throw new CustomException(비번 틀렸다는 코드 추가하기)
        }
        */


        String userId = String.valueOf(loginResult.get("USER_ID")) ;
        // 3. refreshToken & accessToken 재발급
        Map<String, Object> tokenInfo = updateAccessAndRefresh(userId);


        // 3.2 refreshToken, accessToken  return
        return tokenInfo;

    }

    @Override
    public void findByUserId() throws SQLException, Exception{}


    @Override
    public Map<String, Object> updateAccessAndRefresh(String userId) throws SQLException, Exception{
//     refreshToken & accessToken 재발급
        String refreshToken = securityService.createToken(userId, ( 24 * 60 * 60 * 1000));
        String accessToken = securityService.createToken(userId, (30 * 60 * 1000));

//      refreshToken DB에 저장
        Map<String, Object> tokenInfo = new HashMap<>();
        tokenInfo.put("refreshToken", refreshToken);
        tokenInfo.put("accessToken", accessToken);
        tokenInfo.put("USER_ID", userId);
        userMapper.updateToken(tokenInfo);

        return tokenInfo;

    }


}
