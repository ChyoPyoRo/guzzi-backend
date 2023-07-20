package com.project.guzzi.vote;
//
//public class CreateVoteResponse {
//
//	private
//
//}

import com.project.guzzi.entity.Vote;

public interface CreateVoteResponse{
	Vote createVote(CreateVoteRequest request);
	Vote findByVoteId(Integer voteId);

}