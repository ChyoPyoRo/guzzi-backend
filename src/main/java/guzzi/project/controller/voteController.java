package guzzi.project.controller;

import guzzi.project.DTO.votePostDto;
import guzzi.project.service.VoteServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
public class voteController {
    @Autowired
    VoteServiceImpl voteService;
    @PostMapping(path="/createvote")
    public ResponseEntity<?> createVote(@RequestBody votePostDto votePost){
        //이 부분은 나중에 service로 옮겨야 할 지두
        HashMap<String, Object> vote = new HashMap<String, Object>();
        vote.put("VOTE_ID", votePost.getVOTE_ID());
        vote.put("USER_ID", votePost.getUSER_ID());
        vote.put("CREATE_AT", votePost.getCREATE_AT());
        vote.put("CONTENT", votePost.getCONTENT());
        vote.put("FIRST_ANSWER", votePost.getFIRST_ANSWER());
        vote.put("SECOND_ANSWER", votePost.getSECOND_ANSWER());
        //여기서 보내고 나서
        voteService.createVote(vote);
        //여기서 결과 조회해서 보낸다.
        List<votePostDto> result = voteService.findVoteAll();
        return ResponseEntity.ok().body(result);

    }

}
