package guzzi.project.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@CrossOrigin(origins="http://localhost:3000, https://guzzi-frontend.vercel.app/")
@RestController
@RequestMapping("/security")
public class SecurityController {
    @Autowired
    private SecurityService securityService;

    @GetMapping("/create/token")
    public Map<String, Object> createToken (@RequestParam(value = "subject") String subject){
//        System.out.println(subject.getClass().getName()); // == java.lang.String
        String token = securityService.createToken(subject, (60*60*1000*60));
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", token);
        return map;
    }


    @GetMapping("/get/subject")
    public Map<String, Object> getSubject (@RequestParam(value = "token") String token){
        String subject = securityService.getSubject(token);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", subject);
        return map;
    }

}
