package com.jredu.tk.entity;

public class Answer {
	private int id;
	private String answer;
	private String analysis;
	private int sid;
	public Answer(int id, String answer, String analysis, int sid) {
		super();
		this.id = id;
		this.answer = answer;
		this.analysis = analysis;
		this.sid = sid;
	}
	public Answer() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAnalysis() {
		return analysis;
	}
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	
}
