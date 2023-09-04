package guzzi.project.mapper;

import guzzi.project.DTO.votePostDto;
import guzzi.project.config.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface VoteMapper {
    Map<String, Object> getVoteOne(Map<String, Object> paramMap);
    List<Map<String, Object>> getVoteList(Pagination pagination);
    int getTotalVoteCnt();
    public List<votePostDto> findVoteAll();
    //@param("변수명") 타입 변수
    public Map<String,Object> createVote(@Param("vote") Map<String,Object> vote);

}