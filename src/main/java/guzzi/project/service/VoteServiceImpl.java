package guzzi.project.service;


import guzzi.project.DTO.votePostDto;
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
    public void createVote(HashMap<String,Object> vote) {
        voteMapper.createVote(vote);
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
    public List<Map<String, Object>> getVoteList(Map<String, Object> paramMap) throws SQLException, Exception {
        List<Map<String, Object>> voteList = voteMapper.getVoteList(paramMap);
        System.out.println("Service Imple voteList");
        System.out.println(voteList);
        if (voteList != null){
            return voteList ;
        }else{
            throw new CustomException(VOTE_NOT_FOUND);
        }
    }

}
