package guzzi.project.controller;

import guzzi.project.DTO.votePostDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
public class voteController {
    @PostMapping(path="/createvote")
    public HashMap<String, Object> createVote(@RequestBody votePostDto votePost){
//        votePostService.inserVote(votePost);
        System.out.println(votePost.getVOTE_ID());
        System.out.println((votePost.getUSER_ID()));
        System.out.println(votePost.getCONTENT());
        //이 부분은 나중에 service로 옮겨야 할 지두
        HashMap<String, Object> responseJson = new HashMap<String, Object>();
        responseJson.put("VOTE_ID", votePost.getVOTE_ID());
        responseJson.put("USER_ID", votePost.getUSER_ID());
        responseJson.put("CREATE_AT", votePost.getCREATE_AT());
        responseJson.put("CONTENT", votePost.getCONTENT());
        responseJson.put("FIRST_ANSWER", votePost.getFIRST_ANSWER());
        responseJson.put("SECOND_ANSWER", votePost.getSECOND_ANSWER());
        return responseJson;

    }

}
