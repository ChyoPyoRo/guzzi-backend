package com.project.guzzi.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.guzzi.entity.Vote;
import com.project.guzzi.mapper.VoteMapper;

import java.util.List;
import java.util.Map;

//@Service
//public class VoteService {


	public interface MybatisTestService {
		List<Map<String, Object>> getTest();
	}






//	@Autowired
//	VoteRepository voteRepository;

	/*
	private VoteRepository voteRepository;

	@Autowired
	public VoteService(VoteRepository voteRepository) {
		this.voteRepository = voteRepository;
	}
	*/
	/*
	 * 중복체크 로직 예시
	 *
	 * private void validateDuplicateMember(Member member){
	 * 		memberRepository,findByName(member.getName())
    * 				.ifPersent(m -> {
    * 						throw new IllegalStateException("이미 존재하는 회원입니다.")
    * 						});
 * )
	 *
	 * }
	 *
	 */
//    @Autowired
//	private final VoteMapper voteMapper;
//
//    public VoteService(VoteMapper voteMapper) {
//        this.voteMapper = voteMapper;
//    }
//
//    public Vote getVoteById(Long voteId) {
//        return voteMapper.getVoteById(voteId);
//    }

	/*
	 * 투표 작성
	 */
//	public Vote createVote(CreateVoteRequest request) {
//
////		CreateVoteResponse.createVote(request);
//		return voteRepository.createVote(request);
//
//
//	}
//
//	public Vote findOneVote(Long voteId){
//		return voteMapper.getVoteById(voteId);
//	}

//}
