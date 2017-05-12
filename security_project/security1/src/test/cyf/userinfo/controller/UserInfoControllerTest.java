package cyf.userinfo.controller;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;


/**
 * Created by Administrator on 2017-2-16.
 */
public class UserInfoControllerTest {

    @Test
    public void testSendPost2() throws IOException {
        HttpClient client = new DefaultHttpClient();

        String path ="http://localhost:8080/security/userinfo/add";
        String requestInfo = "{\"username\": \"Taylor\",\"mobile\": \"110\"}";

        HttpPost post = new HttpPost(path);
       // Maintain v = new Maintain();
       // JSONObject contentObject = JSONObject.fromObject(v);
        //String content = contentObject.toString();
        String content = requestInfo;
        //String content = JSONBinder.binder(People.class).toJSON(v);
        StringEntity entity = null;
        try {
            entity = new StringEntity(content, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        entity.setContentEncoding("utf-8");
        entity.setContentType("application/json");
        post.setEntity(entity);
        HttpResponse response = null;
        try {
            response = client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("响应状态码：" + response.getStatusLine().getStatusCode());
        InputStream is = null;
        try {
            is = response.getEntity().getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuffer buffer = new StringBuffer();
        byte[] bytes = new byte[4096];
        for (int n;(n=is.read(bytes))!= -1;){
            buffer.append(new String(bytes,0,n));
        }
        System.out.println(buffer);
    }


}