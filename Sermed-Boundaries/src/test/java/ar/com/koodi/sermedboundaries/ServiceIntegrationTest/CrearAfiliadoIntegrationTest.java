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

public class CrearAfiliadoIntegrationTest {

    private String url = "http://localhost:8080";

    @Test
    public void crearAfiliado_AfiliadoNoExisteYTitularExisteEnLaBD_Devuelve200() throws Exception {
        JSONObject afiliado = new JSONObject();
        afiliado.put("idAfiliado", null);
        afiliado.put("fechaAfiliacion", LocalDate.of(2018, 3, 30));
        afiliado.put("numeroAfiliado", "190600");
        afiliado.put("miembros", null);
        afiliado.put("titular", factoryTitular());
        afiliado.put("activo", true);
        afiliado.put("fechaDeBaja", null);
        afiliado.put("diaDelMesPagoAcordado", 15);
        afiliado.put("plan", factoryPlan());
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpPost post = new HttpPost(url+"/sermed/afiliado/nuevo");
        StringEntity se = new StringEntity(afiliado.toString());
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        post.setEntity(se);
        post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpUriRequest request = post;
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }



    private JSONObject factoryTitular() throws JSONException {
        JSONObject personaJSON = new JSONObject();
        personaJSON.put("idPersona", 3);
        personaJSON.put("apellidos", "Titular");
        personaJSON.put("nombres", "Persona");
        personaJSON.put("fechaNacimiento", LocalDate.of(2000, 3, 30));
        personaJSON.put("domicilio", "9 de Julio 530");
        personaJSON.put("tipoDocumento", factoryTipoDocumentoDNI());
        personaJSON.put("documento", "87654321");
        personaJSON.put("sangre", factorySangre());
        personaJSON.put("telefono","9999");
        personaJSON.put("obraSocial", factoryObraSocial());
        personaJSON.put("nroAfiliado", "");
        personaJSON.put("nroOrden", "");
        personaJSON.put("antecedentesMedico", null);
        return personaJSON;
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


    public JSONArray factoryMiembros() throws JSONException {
        JSONArray miembros = new JSONArray();
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
        JSONObject persona2JSON = new JSONObject();
        persona2JSON.put("idPersona", 2);
        persona2JSON.put("apellidos", "Simpson");
        persona2JSON.put("nombres", "Homero");
        persona2JSON.put("fechaNacimiento", LocalDate.of(2000, 3, 30));
        persona2JSON.put("domicilio", "Av. Siempre Viva 982");
        persona2JSON.put("tipoDocumento", factoryTipoDocumentoDNI());
        persona2JSON.put("documento", "12345678");
        persona2JSON.put("sangre", factorySangre());
        persona2JSON.put("telefono","11111");
        persona2JSON.put("obraSocial", factoryObraSocial());
        persona2JSON.put("nroAfiliado", "191001");
        persona2JSON.put("nroOrden", 1);
        persona2JSON.put("antecedentesMedico", factoryAntecedentesMedicos());
        miembros.put(personaJSON);
        miembros.put(persona2JSON);
        return miembros;
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
