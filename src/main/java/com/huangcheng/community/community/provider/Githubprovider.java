package com.huangcheng.community.community.provider;

import com.alibaba.fastjson.JSON;
import com.huangcheng.community.community.dto.AcesstokenDto;
import com.huangcheng.community.community.dto.Githubuser;

import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author 荒城
 * @title: Githubprovider
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/323:11
 */
@Component
public class Githubprovider {

    public String getaccesstoken(AcesstokenDto acesstokenDto) {
        MediaType mediaType
                = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(acesstokenDto));
       // System.out.print(JSON.toJSONString(acesstokenDto));
System.out.print(body.contentType());
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {

            String string = response.body().string();//返回是access_token=e72e16c7e42f292c6912e7710c838347ae178b4a&token_type=bearer

            String token = string.split("&")[0].split("=")[1];//获取token

            // String tokenstr = split[0];
            //  String token = tokenstr.split("=")[1];原文中的变量不能被识别，所以将变量放回到原文中
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    //通过access_token来获取用户的信息
    public Githubuser getuser(String access_token) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + access_token)
                .header("Authorization","token "+access_token)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();//来拿到数据

            Githubuser githubuser = JSON.parseObject(string, Githubuser.class);
            return githubuser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
