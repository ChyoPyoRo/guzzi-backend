package com.project.guzzi.vote;

public class CreateVoteRequest {

	private String content;

	private String firstAnswer;

	private String secondAnswer;

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return this.content;
	}

	public void setFirstAnswer(String firstAnswer) {
		this.firstAnswer = firstAnswer;
	}

	public String getFirstAnswer() {
		return this.firstAnswer;
	}

	public void setSecondAnswer(String secondAnswer) {
		this.secondAnswer = secondAnswer;
	}

	public String getSecondAnswer() {
		return this.secondAnswer;
	}
}
