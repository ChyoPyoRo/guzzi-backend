package guzzi.project.service;


import guzzi.project.config.Pagination;
import guzzi.project.exception.CustomException;
import guzzi.project.mapper.VoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static guzzi.project.exception.ErrorCode.DUPLICATE_DATA;
import static guzzi.project.exception.ErrorCode.VOTE_NOT_FOUND;



@Service
public class VoteServiceImpl implements VoteService{
    @Autowired
    VoteMapper voteMapper;

    @Override
    public List<Map<String,Object>> findVoteAll() {
    //결과조회
        return voteMapper.findVoteAll();
    }
    @Override
    public Map<String, Object> createVote(Map<String,Object> vote) {

        //vote_id는 VOTE_SEQ, create_at은 XML파일에 작성
        voteMapper.createVote(vote);
        //해당 vote와 같은 vote_id를 가지는 vote_answer를 생성
        //vote_id값을 가져오는데, content, first_answer, second_answer가 일치하는 것 중 가장 최근에 작성된 것으로 가져옴
        Map<String, Object> recentVote = voteMapper.recentVote(vote).get(0);
        vote.put("VOTE_ID", recentVote.get("VOTE_ID"));
        voteMapper.create_vote_answer(vote);
        //방금 작성한 vote를 return 해줘야 하니까 service단에서 처리
        return voteMapper.getVoteOneRecent(vote);
    }
    @Override
    public Map<String, Object> getVoteOne(Map<String, Object> paramMap) throws SQLException, Exception{

        Map<String, Object> vote = voteMapper.getVoteOne(paramMap);
        System.out.println("vote");
        System.out.println(vote);
        if(vote.get("USER_ID").toString().equals(paramMap.get("USER_ID"))){
            vote.put("isMyVote", true);
        }
        else{
            vote.put("isMyVote", false);
        }
        if(vote != null){
            return vote;
        }else {
            throw new CustomException(VOTE_NOT_FOUND);
        }

    }

    @Override
    public HashMap<String, Object> getVoteList(Map<String, Object> paramMap) throws SQLException, Exception {
//      return map 객체 생성
        HashMap<String, Object> resMap = new HashMap<String, Object>();
//      pagination
        int getTotalVoteCnt = voteMapper.getTotalVoteCnt();
        Pagination pagination = new Pagination(paramMap.get("page"),paramMap.get("size"), getTotalVoteCnt, paramMap.get("USER_ID"));
        pagination.setTotalRecordCount(getTotalVoteCnt);
//      vote list return
        List<Map<String, Object>> voteList = voteMapper.getVoteList(pagination);
        //list 길이만큼 반복
        for(int i=0; i < voteList.size();i++){
            //투표의 USER_ID(작성자)가 현재 로그인한 사용자(paramMap의 USER_ID와 같으면
            if(voteList.get(i).get("USER_ID").toString().equals(paramMap.get("USER_ID"))){
                //isMyVote라는 태그를 추가해야됨
                voteList.get(i).put("isMyVote", true);
            }
            else{
                voteList.get(i).put("isMyVote", false);
            }
        }
        resMap.put("pagination",pagination);
        resMap.put("voteList", voteList);

//        else{
//            throw new CustomException(VOTE_NOT_FOUND);
//        }
        try{
            if (voteList != null){
            return resMap;
            }
        }catch (CustomException e){
            throw new CustomException(VOTE_NOT_FOUND);
        }

            return resMap;
    }

    @Override
    public Map<String,Object> makeUserVote(HashMap<String, Object> paramMap) throws Exception{
        //my_vote_answer 생성
        //VOTE_ANSWER 값을 조회한 뒤에 값 수정하기
        //나중에 Map이 아니라 타입이
        Map<String, Object> findVoteAlreadyExist = voteMapper.findMyVote(paramMap);
        Map<String, Object> vote = voteMapper.findOneVote(paramMap);
        int first_answer =  Integer.parseInt(vote.get("FIRST_ANSWER_COUNT").toString()) ;
        int second_answer =  Integer.parseInt(vote.get("SECOND_ANSWER_COUNT").toString()) ;
        System.out.println("plus");
        System.out.println(first_answer + second_answer);
        if(findVoteAlreadyExist == null){
            //기존에 투표가 없으면
            voteMapper.create_my_vote_answer(paramMap);
            if(paramMap.get("CHK").equals("first")){
                first_answer  += 1;
                System.out.println(first_answer);
                vote.replace("FIRST_ANSWER_COUNT", first_answer);
            }
            else if(paramMap.get("CHK").equals("second")){
                second_answer += 1;
                System.out.println(second_answer);
                vote.replace("SECOND_ANSWER_COUNT", second_answer);
            }
        }
        else {
            //기존에 투표가 존재하면
            Map<String, Object> my_vote = voteMapper.findMyVote(paramMap);
            if(paramMap.get("CHK").equals(findVoteAlreadyExist.get("CHK"))){
                //기존에 투표했던 데이터와 동일한 데이터면 에러 발생 시킴
                throw new CustomException(DUPLICATE_DATA);
            }
            else{
                //아니면 데이터 업데이트 후
                voteMapper.update_my_vote_answer(paramMap);
                //param값에서 전달됬던 CHK 값에 따라서 값을 줄일지 말지를 판단
                if(paramMap.get("CHK").equals("first")){
                    first_answer += 1;
                    vote.replace("FIRST_ANSWER_COUNT", first_answer);
                    second_answer -= 1;
                    vote.replace("SECOND_ANSWER_COUNT", second_answer);
                } else if(paramMap.get("CHK").equals("second")){
                    first_answer -= 1;
                    vote.replace("FIRST_ANSWER_COUNT", first_answer);
                    second_answer += 1;
                    vote.replace("SECOND_ANSWER_COUNT", second_answer);

                }
            }
        }
//        타입 선언 후에는 바로 더하기 될 것 같음

        float first_ratio = (first_answer / (float)(first_answer + second_answer));
        float second_ratio = (second_answer / (float)(first_answer + second_answer));
        double fist_ratio_two_digit = Math.round(first_ratio * 100) / 100.0;
        double second_ratio_two_digit = Math.round(second_ratio*100) / 100.0;
        System.out.println(first_ratio);
        System.out.println(fist_ratio_two_digit);
        System.out.println(second_ratio);
        System.out.println(second_ratio_two_digit);
        vote.replace("FIRST_RATIO", fist_ratio_two_digit);
        vote.replace("SECOND_RATIO", second_ratio_two_digit);

        //수정한 값 vote_answer에 update
        voteMapper.updateVoteAnswer(vote);
        //vote_id값을 통해 생성된 결과 찾기
        System.out.println(vote);
        System.out.println((paramMap));
        Map<String, Object> result = voteMapper.getVoteOne(paramMap);
        return result;
    }

    @Override
    public Boolean isMyVote(Map<String, Object> data) throws Exception {
        int resultCnt = voteMapper.isMyVote(data);
        Boolean result = null;
        if(resultCnt == 1){
            result = true;
        }else {
            result = false;
        }
        return result;
    }





}
