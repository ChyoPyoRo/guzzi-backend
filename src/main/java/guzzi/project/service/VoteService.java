package guzzi.project.service;


import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface VoteService {
    public List<Map<String,Object>> findVoteAll();

    public Map<String, Object> createVote(@Param("vote") Map<String, Object> vote);

//    Map<String, Object> getVoteOne(Map<String, Object> paramMap) throws SQLException, Exception;
    Map<String, Object> getVoteOne(Map<String, Object> paramMap) throws SQLException, Exception;


    HashMap<String, Object> getVoteList(Map<String, Object> paramMap) throws SQLException, Exception;

    Map<String, Object> makeUserVote(HashMap<String, Object> paramMap) throws Exception;
    Boolean isMyVote(Map<String, Object> paramMap) throws Exception;

}