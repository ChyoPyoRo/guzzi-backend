package guzzi.project.mapper;

import guzzi.project.config.Pagination;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface VoteMapper {
    Map<String, Object> getVoteOne(Map<String, Object> paramMap);
    List<Map<String, Object>> getVoteList(Pagination pagination);
    int getTotalVoteCnt();
    public List<Map<String, Object>> findVoteAll();
    public List<Map<String,Object>> recentVote(Map<String ,Object> paramMap);
    //@param("변수명") 타입 변수
    public void createVote(Map<String,Object> paramMap);
    public void create_vote_answer(Map<String,Object> paramMap);
    Map<String, Object> getVoteOneRecent(Map<String, Object> paramMap);

    public void create_my_vote_answer(Map<String, Object> paramMap);

    Map<String,Object> findOneVote(Map<String, Object> paramMap);
    public void updateVoteAnswer(Map<String , Object> paramMap);
    public void update_my_vote_answer(Map<String,Object>paramMap);
    Map<String,Object> findMyVote(Map<String,Object>paramMap);

    int isMyVote(Map<String,Object> paramMap);

}