package ar.com.koodi.sermedboundaries.ServiceIntegrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ModificarPersonaIntegrationTest {

    private String url = "http://localhost:8080";

    @Test
    public void modificarPersona_DatosCorrectos_devuelve200() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new ParameterNamesModule());
        mapper.registerModule(new Jdk8Module());
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpPost post = new HttpPost(url+"/sermed/persona/modificar");
        StringEntity se = new StringEntity(factoryPersona().toString());
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        post.setEntity(se);
        post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpUriRequest request = post;
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void modificarPersona_FaltanDatos_Devuelve412() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new ParameterNamesModule());
        mapper.registerModule(new Jdk8Module());
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        JSONObject personaJSON = new JSONObject();
        personaJSON.put("idPersona", 1);
        personaJSON.put("apellidos", "");
        personaJSON.put("nombres", "");
        personaJSON.put("fechaNacimiento", LocalDate.of(1999, 4, 30));
        personaJSON.put("domicilio", "");
        personaJSON.put("tipoDocumento", factoryTipoDocumento());
        personaJSON.put("documento", "12345678");
        personaJSON.put("sangre", factorySangre());
        personaJSON.put("telefono","11111");
        personaJSON.put("obraSocial", factoryObraSocial());
        personaJSON.put("nroAfiliado", "191001");
        personaJSON.put("nroOrden", 1);
        personaJSON.put("antecedentesMedico", factoryAntecedentesMedicos());
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpPost post = new HttpPost(url+"/sermed/persona/modificar");
        StringEntity se = new StringEntity(personaJSON.toString());
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        post.setEntity(se);
        post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpUriRequest request = post;
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_PRECONDITION_FAILED));
    }

    @Test
    public void modificarPersona_PersonaYaExiste_Devuelve412() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new ParameterNamesModule());
        mapper.registerModule(new Jdk8Module());
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        JSONObject personaJSON = new JSONObject();
        personaJSON.put("idPersona", 1);
        personaJSON.put("apellidos", "Morales");
        personaJSON.put("nombres", "Cachetes");
        personaJSON.put("fechaNacimiento", LocalDate.of(1999, 4, 30));
        personaJSON.put("domicilio", "25 de mayo 456");
        personaJSON.put("tipoDocumento", factoryTipoDocumentoDNI());
        personaJSON.put("documento", "11111111");
        personaJSON.put("sangre", factorySangre());
        personaJSON.put("telefono","123456");
        personaJSON.put("obraSocial", factoryObraSocial());
        personaJSON.put("nroAfiliado", "192001");
        personaJSON.put("nroOrden", 1);
        personaJSON.put("antecedentesMedico", factoryAntecedentesMedicos());
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpPost post = new HttpPost(url+"/sermed/persona/modificar");
        StringEntity se = new StringEntity(personaJSON.toString());
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        post.setEntity(se);
        post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpUriRequest request = post;
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_PRECONDITION_FAILED));
    }

    @Test
    public void modificarPersona_DNIConPuntos_Devuelve412() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new ParameterNamesModule());
        mapper.registerModule(new Jdk8Module());
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        JSONObject personaJSON = new JSONObject();
        personaJSON.put("idPersona", 1);
        personaJSON.put("apellidos", "Gomez");
        personaJSON.put("nombres", "Droopy");
        personaJSON.put("fechaNacimiento", LocalDate.of(1999, 4, 30));
        personaJSON.put("domicilio", "25 de mayo 456");
        personaJSON.put("tipoDocumento", factoryTipoDocumentoDNI());
        personaJSON.put("documento", "37.415.281");
        personaJSON.put("sangre", factorySangre());
        personaJSON.put("telefono","123456");
        personaJSON.put("obraSocial", factoryObraSocial());
        personaJSON.put("nroAfiliado", "192001");
        personaJSON.put("nroOrden", 1);
        personaJSON.put("antecedentesMedico", factoryAntecedentesMedicos());
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpPost post = new HttpPost(url+"/sermed/persona/modificar");
        StringEntity se = new StringEntity(personaJSON.toString());
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        post.setEntity(se);
        post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpUriRequest request = post;
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_PRECONDITION_FAILED));
    }

    public JSONObject factoryPersona() throws JSONException {
        JSONObject personaJSON = new JSONObject();
        personaJSON.put("idPersona", 1);
        personaJSON.put("apellidos", "Power");
        personaJSON.put("nombres", "Max");
        personaJSON.put("fechaNacimiento", LocalDate.of(1999, 4, 30));
        personaJSON.put("domicilio", "Av. Siempre Viva 400");
        personaJSON.put("tipoDocumento", factoryTipoDocumento());
        personaJSON.put("documento", "12345678");
        personaJSON.put("sangre", factorySangre());
        personaJSON.put("telefono","11111");
        personaJSON.put("obraSocial", factoryObraSocial());
        personaJSON.put("nroAfiliado", "191001");
        personaJSON.put("nroOrden", 1);
        personaJSON.put("antecedentesMedico", factoryAntecedentesMedicos());
        return personaJSON;
    }

    public JSONArray factoryAntecedentesMedicos() throws JSONException {
        JSONArray antecedentesArray = new JSONArray();
        JSONObject antecedente = new JSONObject();
        JSONObject afeccion = new JSONObject();
        afeccion.put("idAfeccion", 2);
        afeccion.put("nombreAfeccion", "Gripe");
        antecedente.put("idAntecedenteMedico", null);
        antecedente.put("afeccion", afeccion);
        antecedente.put("observacion", "pa tras");
        antecedentesArray.put(antecedente);
        return antecedentesArray;
    }

    public JSONObject factoryTipoDocumento() throws JSONException {
        JSONObject tipoDocumento = new JSONObject();
        tipoDocumento.put("idTipoDocumento", 2);
        tipoDocumento.put("nombre", "Libreta Civica");
        return tipoDocumento;
    }

    private JSONObject factoryTipoDocumentoDNI() throws JSONException {
        JSONObject tipoDocumento = new JSONObject();
        tipoDocumento.put("idTipoDocumento", 1);
        tipoDocumento.put("nombre", "DNI");
        return tipoDocumento;
    }

    public JSONObject factorySangre() throws JSONException {
        JSONObject sangre = new JSONObject();
        sangre.put("idSangre", 2);
        sangre.put("grupo", "A");
        sangre.put("factor", "RH-");
        return sangre;
    }

    public JSONObject factoryObraSocial() throws JSONException {
        JSONObject obraSocial= new JSONObject();
        obraSocial.put("idObraSocial", 2);
        obraSocial.put("obraSocial", "OSDE");
        return obraSocial;
    }
}
