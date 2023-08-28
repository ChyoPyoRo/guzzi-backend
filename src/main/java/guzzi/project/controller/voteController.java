package guzzi.project.controller;

import guzzi.project.DTO.votePostDto;
import guzzi.project.service.VoteServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
public class voteController {
    @Autowired
    VoteServiceImpl voteService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("/createvote")
    public ResponseEntity<?> createVote(@RequestBody votePostDto votePost){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
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


    @GetMapping("/vote")
    public ResponseEntity<?> getVoteOne(@RequestParam Map<String, Object> paramMap) throws SQLException, Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            Map<String, Object> vote =
                    voteService.getVoteOne(paramMap);
            resultMap.put("VOTE", vote);


        }catch (SQLException e){
            System.out.println(e);
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        } catch(Exception e){
            System.out.println(e);
            return ResponseEntity.status(404).body(e.toString());
        }
        return ResponseEntity.ok()
                .body(resultMap);

    }

    @GetMapping("/votes")
    public ResponseEntity<?> getVoteList(@RequestParam Map<String, Object> paramMap) throws SQLException, Exception {

        HashMap<String, Object> voteList = null;

        try {
            voteList = voteService.getVoteList(paramMap);

        }catch (SQLException e){
            System.out.println(e);
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(Exception e){
            System.out.println(e);
            return ResponseEntity.status(404).body(e.toString());
        }
        return ResponseEntity.ok().body(voteList);

    }

}
