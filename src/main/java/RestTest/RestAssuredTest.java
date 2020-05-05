package RestTest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class RestAssuredTest {
     URI uri = null;

   @BeforeMethod
   public void setup(){
       try {
           uri = new URI("http://jsonplaceholder.typicode.com/posts/3");}
       catch (URISyntaxException e) {
           e.printStackTrace();} }

    @Test
    public void testStatusCode(){
        int code = given().get(uri).thenReturn().getStatusCode();
        System.out.println(code);
//      Response response = given().accept(ContentType.XML).get("http://jsonplaceholder.typicode.com/posts/3");
//      System.out.println(response.toString());
//      Assert.assertEquals(HttpStatus.SC_OK,code);
    }

    @Test
    public void getResponseBody(){
    String body = given().get(uri).thenReturn().body().asString();
    System.out.println(body);
    }

    @Test
    public void useHeaderReuest() {
    Map<String,String> headermap = new HashMap();
    headermap.put("Accept","application/json");
    Map<String,String> headermap2 = new HashMap();
    headermap2.put("Accept","application/xml");
    String body = given().headers(headermap).get(uri).thenReturn().body().asString();
    System.out.println(body);
    System.out.println(given().headers(headermap2).get(uri).thenReturn().body().asString());
    }



    @Test
    public void testResponseContent() {
        Map<String,String> headermap = new HashMap();
        headermap.put("Accept","application/json");
        given().headers(headermap).get(uri).then().body("title", equalToIgnoringCase("ea molestias quasi exercitationem repellat qui ipsa sit aut"),"id",equalTo(3));
    }

    @Test
    public void nestedJsonObjectResponseContent() {
        Map<String,String> headermap = new HashMap();
        headermap.put("Accept","application/json");
        given().headers(headermap).get(uri).then().body("title", equalToIgnoringCase("ea molestias quasi exercitationem repellat qui ipsa sit aut"),"id",equalTo(3));
    }

    @Test
    public void nestedJsonObjectResponseContentContains() {
        Map<String,String> headermap = new HashMap();
        headermap.put("Accept","application/json");
        given().headers(headermap).get(uri).then().body("title", equalToIgnoringCase("ea molestias quasi exercitationem repellat qui ipsa sit aut"), "body",containsString("occaecati"));
    }

    @Test
    public void responseBodyfilters() {
        Map<String,String> headermap = new HashMap();
        headermap.put("Accept","application/json");
        String s = given().headers(headermap).get(uri).getBody().asString();
        Response response = given().headers(headermap).get(uri).thenReturn();
        JsonPath jsonPath = new JsonPath(s);
        System.out.println(jsonPath.getInt("id"));
        System.out.println(jsonPath.getString("email"));
    }

}
