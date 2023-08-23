package guzzi.project.service;


import guzzi.project.DTO.votePostDto;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface VoteService {
    public List<votePostDto> findVoteAll();

    public void createVote(@Param("vote") HashMap<String, Object> vote);

//    Map<String, Object> getVoteOne(Map<String, Object> paramMap) throws SQLException, Exception;
    Map<String, Object> getVoteOne(Map<String, Object> paramMap) throws SQLException, Exception;


    List<Map<String, Object>> getVoteList(Map<String, Object> paramMap) throws SQLException, Exception;


}