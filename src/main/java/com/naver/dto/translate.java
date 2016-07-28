package com.naver.dto;

public class translate {
	private String id;
	private String ko;
	private String en;
	private String content;
	private String lang;
	private int flag;
	private long writerUserNo;
	private long channelNo;
	
	
	
	public long getChannelNo() {
		return channelNo;
	}
	public void setChannelNo(long channelNo) {
		this.channelNo = channelNo;
	}
	public long getWriterUserNo() {
		return writerUserNo;
	}
	public void setWriterUserNo(long writerUserNo) {
		this.writerUserNo = writerUserNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKo() {
		return ko;
	}
	public void setKo(String ko) {
		this.ko = ko;
	}
	public String getEn() {
		return en;
	}
	public void setEn(String en) {
		this.en = en;
	}
	
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	

}
