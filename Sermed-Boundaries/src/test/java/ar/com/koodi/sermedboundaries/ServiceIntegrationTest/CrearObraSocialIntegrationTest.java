package ar.com.koodi.sermedboundaries.ServiceIntegrationTest;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CrearObraSocialIntegrationTest {

    private String url = "http://localhost:8080";

    @Test
    public void crearObraSocial_NoExisteObraSocial_Devuelve200() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idObraSocial", "");
        jsonObject.put("obraSocial", "Nueva Obra Social");
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpPost post = new HttpPost(url+"/sermed/obraSocial/nueva");
        StringEntity se = new StringEntity(jsonObject.toString());
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        post.setEntity(se);
        post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpUriRequest request = post;
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void crearObraSocial_ExisteObraSocial_Devuelve412() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idObraSocial", "");
        jsonObject.put("obraSocial", "APOS");
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpPost post = new HttpPost(url+"/sermed/obraSocial/nueva");
        StringEntity se = new StringEntity(jsonObject.toString());
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        post.setEntity(se);
        post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpUriRequest request = post;
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_PRECONDITION_FAILED));
    }

}
