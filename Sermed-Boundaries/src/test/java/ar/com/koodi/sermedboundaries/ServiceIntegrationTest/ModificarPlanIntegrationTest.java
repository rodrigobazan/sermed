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
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ModificarPlanIntegrationTest {

    private String url = "http://localhost:8080";

    @Test
    public void modificarPlan_DatosCorrectos_Devuelve200() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idPlan", 2);
        jsonObject.put("nombrePlan", "Nuevo Plan Modificado");
        jsonObject.put("listaPrecios", new JSONObject(factoryListaPreciosModificados()));
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpPost post = new HttpPost(url+"/sermed/plan/modificar");
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
    public void modificarPlan_PlanExiste_Devuelve412() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idPlan", 3);
        jsonObject.put("nombrePlan", "Nuevo Plan");
        jsonObject.put("listaPrecios", null);
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpPost post = new HttpPost(url+"/sermed/plan/modificar");
        StringEntity se = new StringEntity(jsonObject.toString());
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        post.setEntity(se);
        post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpUriRequest request = post;
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_PRECONDITION_FAILED));
    }

    @Test
    public void modificarPlan_PlanIncompleto_Devuelve412() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idPlan", 2);
        jsonObject.put("nombrePlan", "");
        jsonObject.put("listaPrecios", new JSONObject(factoryListaPreciosModificados()));
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpPost post = new HttpPost(url+"/sermed/plan/modificar");
        StringEntity se = new StringEntity(jsonObject.toString());
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        post.setEntity(se);
        post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpUriRequest request = post;
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_PRECONDITION_FAILED));
    }

    private HashMap<String, Double> factoryListaPreciosModificados(){
        try {
            HashMap<String, Double> listaPrecios = new HashMap<>();
            listaPrecios.put("1", (double) 400);
            listaPrecios.put("2", (double) 500);
            listaPrecios.put("3", (double) 600);
            listaPrecios.put("4", (double) 700);
            return listaPrecios;
        }catch (Exception e){
            e.printStackTrace();
            return new HashMap<>();
        }
    }

}
