package guzzi.project.controller;

import guzzi.project.security.Token;
import guzzi.project.service.VoteServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@CrossOrigin(origins="http://localhost:3000, https://guzzi-frontend.vercel.app/")
@RestController
public class voteController {
    @Autowired
    VoteServiceImpl voteService;
    @Autowired
    Token tokenValidation;

    @Autowired
    userController UserController;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("/create")
    public ResponseEntity<?> createVote(@RequestBody Map<String, Object> vote, HttpServletRequest request )throws Exception{
        Map<String, Object> result;
        Map<String,Object>   user = tokenValidation.TokenVal(request);
        vote.put("USER_ID", user.get("USER_ID"));

        try{
            System.out.println(vote);
            //여기서 생성을 하고 나서 값을 돌려 보낼 만한게 없음
            result = voteService.createVote(vote);
        } catch(Exception e){
            System.out.println(e);
            return ResponseEntity.status(404).body(e.toString());
        }
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
        // param에 쿠키에서 가져온 userId 값 추가해줘야 함.
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

    @PatchMapping("/vote")
    public ResponseEntity<?> votePatch(@RequestParam Map<String, Object> paramMap, @RequestBody Map<String, Object> option, HttpServletRequest request) throws Exception{
        Map<String, Object> result = null;
        HashMap<String, Object> dataList = new HashMap<>();
        Map<String,Object>   user = tokenValidation.TokenVal(request);

        try {
            //데이터 저장하기
            dataList.put("VOTE_ID", paramMap.get("VOTEID"));
            dataList.put("CHK", option.get("option"));
            dataList.put("USER_ID", user.get("USER_ID"));

            //여기에서 login_required 확인 ( 나중에 추가 )
            result = voteService.makeUserVote(dataList);
            return ResponseEntity.ok().body(result);
        }catch(Exception e){
            System.out.println(e);
            return ResponseEntity.status(404).body(e.toString());
        }
    }



    @GetMapping("/isMyVote")
    public ResponseEntity<?> isMyVote(HttpServletRequest request, @RequestParam Map<String, Object> voteId)  throws SQLException, Exception {
        Map<String,Boolean> isMyVote = new HashMap<>();
        Map<String, Object> data = tokenValidation.TokenVal(request);
        data.put("VOTE_ID",voteId.get("VOTE_ID"));

        try{
            Boolean result = voteService.isMyVote(data);
            System.out.println(data);
            System.out.println(result);
            isMyVote.put("isMyVote", result);
        } catch(Exception e){
            System.out.println(e);
            return ResponseEntity.status(404).body(e.toString());
        }
        return ResponseEntity.ok().body(isMyVote);


    }
}
