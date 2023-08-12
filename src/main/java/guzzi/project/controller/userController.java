package guzzi.project.controller;

import guzzi.project.DTO.userDto;
import guzzi.project.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Slf4j
@RestController
public class userController {
//    private final static String DEFAULT_PATH = "guzzi.project";

    @Autowired
    UserServiceImpl userServiceImpl;

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
//
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("ID",signupData.getID());
        paramMap.put("PASSWORD",signupData.getPASSWORD());
               //id, pw null 여부 확인

        try {
//            System.out.println("Controller");
//            System.out.println(paramMap);
            userServiceImpl.signup(paramMap);

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
    public ResponseEntity<Object> login(@RequestBody userDto loginData) throws SQLException, Exception {
//        System.out.println(loginData.getID());
//        System.out.println(loginData.getPASSWORD());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Game", "Chess");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String ID = loginData.getID();
        String PASSWORD = loginData.getPASSWORD();
        //id, pw null 여부 확인

        try {

            paramMap.put("ID",ID);
            paramMap.put("PASSWORD",PASSWORD);

        } catch(Exception e){
            logger.error((Supplier<String>) e);
        }
        return ResponseEntity.ok()
                .headers(headers)
                .body(paramMap);

    }


    @GetMapping("/isMe")
    public void isMe()  throws SQLException, Exception {

    }

    @GetMapping("/refresh")
    public void refresh()  throws SQLException, Exception {

    }
}
