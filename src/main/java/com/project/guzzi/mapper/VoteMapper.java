package com.project.guzzi.mapper;

import com.project.guzzi.entity.Vote;
import org.apache.ibatis.annotations.Select;
// bean 등록하기

public interface VoteMapper {
	@Select("SELECT * FROM vote WHERE voteId=#{voteId}")
	Vote getVoteById(Long voteId);

}