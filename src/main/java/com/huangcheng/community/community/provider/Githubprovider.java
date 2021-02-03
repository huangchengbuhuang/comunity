package com.huangcheng.community.community.provider;

import com.huangcheng.community.community.dto.AcesstokenDto;
//import okhttp3.MediaType;
import org.springframework.stereotype.Component;

/**
 * @author 荒城
 * @title: Githubprovider
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/323:11
 */
@Component
public class Githubprovider {

    public String getaccesstoken(AcesstokenDto acesstokenDto)
    {
        public static final MediaType JSON
                = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    }
}
