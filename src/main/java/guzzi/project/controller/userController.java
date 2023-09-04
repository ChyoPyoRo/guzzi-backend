package guzzi.project.controller;

import guzzi.project.DTO.userDto;
import guzzi.project.security.SecurityService;
import guzzi.project.service.UserServiceImpl;
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

import java.sql.SQLException;
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

    private final Logger logger = LoggerFactory.getLogger(getClass());

//    https://velog.io/@joungeun/ResponseEntity-Http-Status -- ResponseEntity status 정리 블로그

//    @GetMapping("/user/all")
//    public @ResponseBody List<UserVO> findAllMemberBySelect(){
//
//        System.out.print("Get : /user/all");
//        List<UserVO> list = userService.findAllMemberBySelect();
//        System.out.print(list + "\n");
//        return list;
//    }


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

        System.out.println(result);


        HttpHeaders headers = new HttpHeaders();
        try {

            headers.add("Set-Cookie", "access_token=" + accessToken);
            headers.add("Set-Cookie", "refresh_token=" + refreshToken);

        } catch(Exception e){
            logger.error((Supplier<String>) e);
        }
        ResponseEntity<?> responseEntity = ResponseEntity.ok()
                .headers(headers)
                .body(result);
        return responseEntity;


    }


    @GetMapping("/isMe")
    public void isMe()  throws SQLException, Exception {

    }

    @GetMapping("/refresh")
    public void refresh()  throws SQLException, Exception {

    }
}
