import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class ThirdTask {
    @Test
    public void URL() {

        Response response = RestAssured.get("https://reqres.in/api/register");
        System.out.println(response.getBody().asString());

        int statusCode = response.getStatusCode();
        assert statusCode == 200;

        JSONObject request = new JSONObject();
        request.put( "email", "eve.holt@reqres.in");
        request.put( "password","pistol");

        RestAssured.given().
                body(request.toJSONString()).
                when().
                post("https://reqres.in/api/register");

        if (response.statusCode() == 200) {
            Temporary responseBody = response.as(Temporary.class);

            assert "eve.holt@reqres.in".equals(responseBody.email);
            assert "pistol".equals(responseBody.password);

        }

    }


    @Test
    public void checkUsers() {
        Response secondResponse = RestAssured.get("https://reqres.in/api/users").then()
                .statusCode(200).extract().response();

        JSONObject request = new JSONObject();
        request.put( "name", "morpheus");
        request.put( "job","leader");

        RestAssured.given().
                body(request.toJSONString()).
                when().
                post("https://reqres.in/api/users");
    }
}

class Temporary {
    public String email;
    public String password;
}
