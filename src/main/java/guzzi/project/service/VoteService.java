package guzzi.project.service;

import guzzi.project.DTO.votePostDto;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface VoteService {
    public List<votePostDto> findVoteAll();
    public void createVote(@Param("vote") HashMap<String,Object> vote);
}
