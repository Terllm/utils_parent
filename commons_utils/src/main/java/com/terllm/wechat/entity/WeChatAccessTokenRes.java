package com.terllm.wechat.entity;


import lombok.*;

/**
 *
 * @author terllm 2019-09-04
 * 实体类
 *
 */
@Data
@Setter
@Getter
@ToString
@NoArgsConstructor
public class WeChatAccessTokenRes  {


	
	/**
	 * Access Token
	 */
	private String access_token;
	/**
	 * 过期时间(S)
	 */
	private int expires_in;
	





}
