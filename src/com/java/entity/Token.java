package com.java.entity;
/**
 *@author: wangq
 *@date: 2015-8-15上午08:57:46
 *@version:
 *@description：
 */
public class Token {
	
	private Integer tokenId;          // 自增ID
	private Integer userId;           // 用户ID
	private String userAgent;         // 随机数(md5形式)
	private String token;             // TOKEN值(md5(username+md5(useragent)))
	private String createTime;        // 创建时间
	private String expireTime;        // 过期时间
	
	public Integer getTokenId() {
		return tokenId;
	}
	public void setTokenId(Integer tokenId) {
		this.tokenId = tokenId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	
}