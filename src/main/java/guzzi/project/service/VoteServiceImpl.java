package guzzi.project.service;

import guzzi.project.DTO.votePostDto;
import guzzi.project.mapper.VoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.HashMap;
import java.util.List;


@Service
public class VoteServiceImpl implements   VoteService{
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
}
