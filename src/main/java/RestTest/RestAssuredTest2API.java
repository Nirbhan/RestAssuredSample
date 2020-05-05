package RestTest;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static io.restassured.RestAssured.given;

public class RestAssuredTest2API {

    URI uri = null;
    @BeforeMethod
    public void setup(){
        try {
            uri = new URI("https://reqres.in");}
        catch (URISyntaxException e) {
            e.printStackTrace();} }

    @Test
    public void testStatusCode(){
        int code = given().get(uri+"/api/users/2").thenReturn().getStatusCode();
        System.out.println(code);
    }

    @Test
    public void getResponseBody(){
        String body = given().get(uri+"/api/users/2").thenReturn().body().asString();
        System.out.println(body);
    }

}
