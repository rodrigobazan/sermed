package ar.com.koodi.sermedboundaries.ServiceIntegrationTest;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ModificarMedicoIntegrationTest {

    private String url = "http://localhost:8080";

    @Test
    public void modificarMedico_DatosCorrectos_Devuelve200() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idMedico",1);
        jsonObject.put("apellido", "Paez Yaneez");
        jsonObject.put("nombre", "Martin");
        jsonObject.put("matricula", 192035);
        jsonObject.put("telefono", "987654321");
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpPost post = new HttpPost(url+"/sermed/medico/modificar");
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
    public void modificarMedico_MatriculaExiste_Devuelve412() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idMedico", 1);
        jsonObject.put("apellido", "Paez Yaneez");
        jsonObject.put("nombre", "Martin");
        jsonObject.put("matricula", 156651);
        jsonObject.put("telefono", "987654321");
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpPost post = new HttpPost(url+"/sermed/medico/modificar");
        StringEntity se = new StringEntity(jsonObject.toString());
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        post.setEntity(se);
        post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpUriRequest request = post;
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_PRECONDITION_FAILED));
        Assertions.assertEquals("Matricula ya existe", EntityUtils.toString(httpResponse.getEntity()));
    }

    @Test
    public void modificarMedico_MedicoIncompleto_Devuelve412() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idMedico", 2);
        jsonObject.put("apellido", "");
        jsonObject.put("nombre", "");
        jsonObject.put("matricula", 156651);
        jsonObject.put("telefono", "987654321");
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpPost post = new HttpPost(url+"/sermed/medico/modificar");
        StringEntity se = new StringEntity(jsonObject.toString());
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        post.setEntity(se);
        post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpUriRequest request = post;
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_PRECONDITION_FAILED));
        Assertions.assertEquals("Faltan datos", EntityUtils.toString(httpResponse.getEntity()));
    }
}
