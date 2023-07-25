package guzzi.project.controller;

import guzzi.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class userController {
//    private final static String DEFAULT_PATH = "guzzi.project";

    @Autowired
    UserService userService;
//    https://velog.io/@joungeun/ResponseEntity-Http-Status -- ResponseEntity status 정리 블로그
    /*
     * selectUserById - userId 로 user info 조회 (토큰 관련 로직에 연결)
     * @author 남현정 >>> param HttpServletRequest 로 바꾸기
     * @param paramMap
     * @return paramMap
     * @exception SQLException
     * @exception Exception
     * */
    @GetMapping("/isMe")
    public ResponseEntity<Map<String,Object>> selectUserById(@RequestBody Long request) throws SQLException, Exception{
        Map<String,Object> resultMap = new HashMap<String,Object>();
        Map<String,Long> paramMap = new HashMap<String,Long>();
        paramMap.put("userId", request);

        try {
            Map<String,Object> userInfo = userService.selectUserById(paramMap);
            resultMap.put("userInfo", userInfo);
            HttpHeaders headers= new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(resultMap);

        }catch (SQLException e){
            System.out.println(e.getErrorCode());
            System.out.println(e.toString());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.toString());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        }

    }

}
