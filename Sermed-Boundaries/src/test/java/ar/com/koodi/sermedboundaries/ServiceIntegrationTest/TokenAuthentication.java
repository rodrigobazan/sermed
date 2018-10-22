package ar.com.koodi.sermedboundaries.ServiceIntegrationTest;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


public class TokenAuthentication {

    static String url = "http://localhost:8080";


    public static String obtainAccessToken(String usuario, String password) throws Exception {
        String token;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("login", "usuario");
        jsonObject.put("password", "123456");
        HttpPost post = new HttpPost(url + "/sermed/auth/signin");
        StringEntity se = new StringEntity(jsonObject.toString());
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        post.setEntity(se);
        post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        final HttpUriRequest request = post;
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        String resultado = EntityUtils.toString(httpResponse.getEntity());
        token = new JSONObject(resultado).get("token").toString();
        return token;
    }
}
