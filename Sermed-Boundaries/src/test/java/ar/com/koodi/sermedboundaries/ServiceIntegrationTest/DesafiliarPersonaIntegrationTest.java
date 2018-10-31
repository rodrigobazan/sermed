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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DesafiliarPersonaIntegrationTest {

    private String url="http://localhost:8080";

    @Test
    public void desafiliarPersona_personaAfiliada_Devuelve200() throws Exception {
        JSONObject persona = factoryPersona();
        JSONObject afiliado = factoryAfiliado();
        JSONObject envoltura = new JSONObject();
        envoltura.put("persona", persona);
        envoltura.put("afiliado", afiliado);
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpPost post = new HttpPost(url+"/sermed/afiliado/desafiliarPersona");
        StringEntity se = new StringEntity(envoltura.toString());
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        post.setEntity(se);
        post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpUriRequest request = post;
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void desafiliarPersona_PersonaNoEsMiembro_Devuelve412() throws Exception {
        JSONObject persona = factoryPersona();
        JSONObject afiliado = factoryAfiliado();
        JSONObject envoltura = new JSONObject();
        envoltura.put("persona", persona);
        envoltura.put("afiliado", afiliado);
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpPost post = new HttpPost(url+"/sermed/afiliado/desafiliarPersona");
        StringEntity se = new StringEntity(envoltura.toString());
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        post.setEntity(se);
        post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpUriRequest request = post;
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_PRECONDITION_FAILED));
    }

    private JSONObject factoryAfiliado() throws JSONException {
        JSONObject afiliado = new JSONObject();
        afiliado.put("idAfiliado", 12);
        afiliado.put("activo", false);
        afiliado.put("diaDelMesPagoAcordado", 15);
        afiliado.put("fechaAfiliacion", LocalDate.of(2018,3,30));
        afiliado.put("fechaDeBaja", LocalDate.of(2018,10,31));
        afiliado.put("numeroAfiliado", 190600);
        afiliado.put("plan", factoryPlan());
        afiliado.put("titular", factoryPersona());
        afiliado.put("miembros", factoryMiembros());
        return afiliado;
    }

    private JSONArray factoryMiembros() throws JSONException {
        JSONArray array = new JSONArray();
        array.put(factoryPersona());
        return array;
    }

    private JSONObject factoryPlan() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idPlan", 3);
        jsonObject.put("nombrePlan", "Nuevo Plan");
        jsonObject.put("listaPrecios", new JSONObject(factoryListaPrecios()));
        return jsonObject;
    }

    private HashMap<String, Double> factoryListaPrecios(){
        try {
            HashMap<String, Double> listaPrecios = new HashMap<>();
            listaPrecios.put("1", (double) 380);
            listaPrecios.put("2", (double) 480);
            listaPrecios.put("3", (double) 550);
            listaPrecios.put("4", (double) 600);
            return listaPrecios;
        }catch (Exception e){
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    private JSONObject factoryPersona() throws JSONException {
        JSONObject personaJSON = new JSONObject();
        personaJSON.put("idPersona", 2);
        personaJSON.put("apellidos", "Simpson");
        personaJSON.put("nombres", "Homero");
        personaJSON.put("fechaNacimiento", LocalDate.of(2000, 3, 30));
        personaJSON.put("domicilio", "Av. Siempre Viva 900");
        JSONObject tipoDocumento = new JSONObject();
        tipoDocumento.put("idTipoDocumento", 1);
        tipoDocumento.put("nombre", "DNI");
        personaJSON.put("tipoDocumento", tipoDocumento);
        personaJSON.put("documento", "11111111");
        JSONObject sangre = new JSONObject();
        sangre.put("idSangre", 1);
        sangre.put("grupo", "A");
        sangre.put("factor", "RH+");
        personaJSON.put("sangre", sangre);
        personaJSON.put("telefono","9999");
        JSONObject obraSocial= new JSONObject();
        obraSocial.put("idObraSocial", 1);
        obraSocial.put("obraSocial", "OSFATUN");
        personaJSON.put("obraSocial", obraSocial);
        personaJSON.put("nroAfiliado", "190001");
        personaJSON.put("nroOrden", 1);
        personaJSON.put("antecedentesMedico", null);
        return personaJSON;
    }
}
