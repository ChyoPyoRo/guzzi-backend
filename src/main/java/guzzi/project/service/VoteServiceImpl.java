package guzzi.project.service;


import guzzi.project.DTO.votePostDto;
import guzzi.project.config.Pagination;
import guzzi.project.exception.CustomException;
import guzzi.project.mapper.VoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static guzzi.project.exception.ErrorCode.VOTE_NOT_FOUND;



@Service
public class VoteServiceImpl implements VoteService{
    @Autowired
    VoteMapper voteMapper;

    @Override
    public List<votePostDto> findVoteAll() {
    //결과조회
        return voteMapper.findVoteAll();
    }
    @Override
    public void createVote(Map<String,Object> vote) {
        //나중에 security 되면 user_id 값 수정
        vote.put("USER_ID", 1);
        System.out.println(vote);
        Map<String, Object> result = voteMapper.createVote(vote);
        System.out.println((result));
    }
    @Override
    public Map<String, Object> getVoteOne(Map<String, Object> paramMap) throws SQLException, Exception{

        Map<String, Object> vote = voteMapper.getVoteOne(paramMap);
        System.out.println("vote");
        System.out.println(vote);

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
        Pagination pagination = new Pagination(paramMap.get("page"),paramMap.get("size"), getTotalVoteCnt);
        pagination.setTotalRecordCount(getTotalVoteCnt);
//      vote list return
        List<Map<String, Object>> voteList = voteMapper.getVoteList(pagination);
        resMap.put("pagination",pagination);
        resMap.put("voteList", voteList);

        if (voteList != null){
            return resMap;
        }else{
            throw new CustomException(VOTE_NOT_FOUND);
        }
    }

}
