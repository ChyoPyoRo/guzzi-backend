package guzzi.project.security;

import guzzi.project.exception.CustomException;
import guzzi.project.service.UserServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static guzzi.project.exception.ErrorCode.*;

@Service
public class Token {

    @Autowired
    SecurityService securityService;

    @Autowired
    UserServiceImpl userService;

    public Map<String, Object> accessVal (HttpServletRequest request) throws Exception, ExpiredJwtException{
        /*
        Login 검증 시 이용
        * accessToken validation
        * return : user_id 값
        *
        * access 만료시 refreshToken validataion
        * return : user_id 값
        * database refreshToken 값 update
        *
        * access + refreshToken 만료
        * return Exception
        * */
        Map<String, Object> USER_ID = new HashMap<>();
        Map<String, Object> tokenInfo;
        String user_id;

//        System.out.println("USER_ID");
//        System.out.println(USER_ID);      // {}
//        System.out.println("2🫥");

//        System.out.println(request.getCookies()); // null

        if(request.getCookies() == null){
            throw new CustomException(TOKEN_NOT_FOUND);
        }





        // cookie parsing
        Map<String, String> tokens = cookieParser(request);


        // access token 만료 검증
        String accessToken = tokens.get("access_token");
        String refreshToken = tokens.get("refresh_token");

        try{
            // access 검증
            user_id = securityService.getSubject(accessToken);

        }catch (ExpiredJwtException A){
            System.out.println("access token이 만료되었습니다.");
            // access 만료 + refresh 검증
            user_id = refreshVal(refreshToken);
            // refresh 검증 완료 후 refresh, access 재발급
            tokenInfo = userService.updateAccessAndRefresh(user_id);
            USER_ID.put("token", tokenInfo);


        }catch (JwtException A) {
            System.out.println("token 검증에 실패하였습니다.");
            throw new CustomException(MISMATCH_REFRESH_TOKEN);
        }

        USER_ID.put("USER_ID", user_id);

        return USER_ID;

    }

    public String refreshVal (String refreshToken) throws ExpiredJwtException, JwtException{
        /*
        * refreshToken validation
        * return : access + refreshToken
        * */

        String user_id;
        try {
            user_id = securityService.getSubject(refreshToken);


        }catch (ExpiredJwtException R){

            System.out.println("refresh token이 만료되었습니다.");
            throw new CustomException(INVALID_REFRESH_TOKEN);

        }catch (JwtException R) {

            System.out.println("refresh token 검증에 실패하였습니다.");
            throw new CustomException(MISMATCH_REFRESH_TOKEN);

        }

        return user_id;



    }

    public Map<String, String> cookieParser(HttpServletRequest request){
        /*
        * HttpServletRequest request 파라미터에서 cookie 파싱
        * */
        Cookie[] cookies = request.getCookies();

        Map<String, String> tokens = new HashMap<>();
        for(Cookie cookie : cookies) {
            System.out.println("쿠키 이름: " + cookie.getName());
            System.out.println("쿠키 값: " + cookie.getValue());
            tokens.put( cookie.getName(), cookie.getValue());
        }
        return tokens;

    }

    public Map<String, Object> TokenVal (HttpServletRequest request) throws Exception, ExpiredJwtException{
    // 일반 로직에서 USER_ID 추출 시 사용//
        Map<String, Object> USER_ID = new HashMap<>();
        Map<String, Object> tokenInfo;
        String user_id = null;



        // cookie parsing
        Map<String, String> tokens = cookieParser(request);
        String accessToken = tokens.get("access_token");



        try{
            // access 검증
            user_id = securityService.getSubject(accessToken);

        }catch (ExpiredJwtException A){
            System.out.println("access token이 만료되었습니다.");
            throw new CustomException(INVALID_ACCESS_TOKEN);

        }catch (JwtException A) {
            System.out.println("access token 검증에 실패하였습니다.");
            throw new RuntimeException(A);
        }

        USER_ID.put("USER_ID", user_id);

        return USER_ID;

    }
}
