package com.terllm.wechat.test;

import com.terllm.wechat.configuration.WechatConfigs;

import java.util.HashMap;
import java.util.Map;

/***
 *
 * @author terllm 2019-09-04
 *
 * 使用 js-sdk的接口需要使用签名
 * 签名：通过 access_token 换取 ticket 使用 sha-1算法，ticket、 随机字符串、时间戳，url 散列算法得到签名
 * 回调到前端，
 *
 */
public class Test {



    public static void main(String[] args){




        String noncestr = WechatConfigs.creatNoncestr();

        String timestamp = WechatConfigs.getTimeStamp();

        String signature ="";

        try {

            WechatConfigs.refreshAccessToken();
            WechatConfigs.getJsapiTicket();
            signature = WechatConfigs.getSignature(noncestr, timestamp, "url");


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Map<String,String> result = new HashMap<String, String>();
        result.put("noncestr", noncestr);
        result.put("timestamp", timestamp);
        result.put("signature", signature);


    }


}
