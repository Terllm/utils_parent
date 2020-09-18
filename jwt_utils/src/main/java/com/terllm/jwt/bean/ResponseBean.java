package com.terllm.jwt.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Classname ResponseBean
 * @Description 响应消息体
 * @Date 2020/9/18 17:22
 * @Created by Terllm
 */
@Data
@Setter
@Getter
public class ResponseBean<T> {


    private int code;

    private String msg;

    private T datas;





}
