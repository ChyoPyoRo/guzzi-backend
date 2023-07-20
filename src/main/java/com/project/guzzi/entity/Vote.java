package com.project.guzzi.entity;

public class Vote {
	//Id값은 난수로 작업
	private Long userId;
	private Long voteId;
	private String content;
	private String firstAnswer;
	private String secondAnswer;
	private Integer answerCount;

	public Long getVoteId() {
		return voteId;
	}
	public Long getUserId() {
		return userId;
	}
	public String getContent() {
		return content;
	}
	public String getFirstAnswer() {
		return firstAnswer;
	}
	public String getSecondAnswer() {
		return secondAnswer;
	}
	public Integer getAnswerCount() {
		return answerCount;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public void setFirstAnswer(String firstAnswer) {
		this.firstAnswer = firstAnswer;
	}
	public void setSecondAnswer(String secondAnswer) {
		this.secondAnswer = secondAnswer;
	}
	public void setAnswerCount(Integer answerCount) {
		this.answerCount = answerCount;
	}

}
