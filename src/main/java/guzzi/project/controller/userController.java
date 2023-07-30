package guzzi.project.controller;

import guzzi.project.service.UserServiceImpl;
import guzzi.project.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@RestController
public class userController {
//    private final static String DEFAULT_PATH = "guzzi.project";

    @Autowired
    UserServiceImpl userService;
//    https://velog.io/@joungeun/ResponseEntity-Http-Status -- ResponseEntity status 정리 블로그

    @GetMapping("/user/all")
    public @ResponseBody List<UserVO> findAllMemberBySelect(){

        System.out.print("Get : /user/all");
        List<UserVO> list = userService.findAllMemberBySelect();
        System.out.print(list + "\n");
        return list;
    }
}
