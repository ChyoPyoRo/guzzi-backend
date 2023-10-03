package guzzi.project.controller;

import guzzi.project.DTO.userDto;
import guzzi.project.security.SecurityService;
import guzzi.project.security.Token;
import guzzi.project.service.UserServiceImpl;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Slf4j
@RestController
public class userController {
//    private final static String DEFAULT_PATH = "guzzi.project";
    @Value("${JWT.SECRET.KEY}")
    private String JWT_SECRET_KEY;
    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    SecurityService securityService;

    @Autowired
    Token tokenValidation;


    private final Logger logger = LoggerFactory.getLogger(getClass());

//    https://velog.io/@joungeun/ResponseEntity-Http-Status -- ResponseEntity status 정리 블로그


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody userDto signupData) throws SQLException, Exception {

        try {
            userServiceImpl.signup(signupData);

        }catch (SQLException e){
            System.out.println(e);
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        } catch(Exception e){
            System.out.println(e);
            return ResponseEntity.status(403).body(e.toString());
        }
        return ResponseEntity.ok()
                .body("회원가입이 정상적으로 완료되었습니다.");

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody userDto loginData) throws SQLException, Exception {

        Map<String, Object> result = userServiceImpl.login(loginData);



        String refreshToken = (String) result.get("refreshToken");
        String accessToken = (String) result.get("accessToken");
        result.remove("refreshToken");
        result.remove("accessToken");


        HttpHeaders headers = new HttpHeaders();
        try {

            headers.add("Set-Cookie", "access_token=" + accessToken + ";Secure;SameSite=None");
            headers.add("Set-Cookie", "refresh_token=" + refreshToken + ";Secure;SameSite=None");

        } catch(Exception e){
            logger.error((Supplier<String>) e);
        }
        ResponseEntity<?> responseEntity = ResponseEntity.ok()
                .headers(headers)
                .body(result);
        return responseEntity;


    }


    @GetMapping("/isMe")
    public ResponseEntity<?> isMe(HttpServletRequest request)  throws SQLException, Exception {
        /*
        * 1. accessToken header에서 받은 후 user_id 반환 test용
        *
        * 2. access 만료되었으면 refresh 검증 후 access + refresh 재 반환
        *
        * - 사용시에는 컨트롤러에서 헤더값 파라미터 받아서 해당 로직 끌어다쓰기 -
        * */
        HttpHeaders headers = new HttpHeaders();
        Map<String, Object> tokenResult;



        try {
            tokenResult = tokenValidation.accessVal(request);
            Object tokens = tokenResult.get("token");
            if (tokens != null){

                String data = (String) tokens.toString();
                Map<String, String> map = parseDataString(data);

                String access_Token = map.get("accessToken");
                String refresh_Token = map.get("refreshToken");

                headers.add("Set-Cookie", "access_token=" + access_Token + ";Secure;SameSite=None");
                headers.add("Set-Cookie", "refresh_token=" + refresh_Token + ";Secure;SameSite=None");
                tokenResult.remove("token");

            }

        }catch (JwtException e){
            System.out.println(e);
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        } catch(Exception e){
            System.out.println(e);
            return ResponseEntity.status(403).body(e.toString());
        }

        ResponseEntity<?> responseEntity = ResponseEntity.ok()
                .headers(headers)
                .body(tokenResult);
        return responseEntity;





    }

    public static Map<String, String> parseDataString(String data) {
        // 문자열을 쉼표와 중괄호를 기준으로 분할하여 Map에 저장
        Map<String, String> map = new HashMap<>();
        String[] keyValuePairs = data.split(", ");
        for (String pair : keyValuePairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                map.put(keyValue[0], keyValue[1]);
            }
        }
        return map;
    }


}
