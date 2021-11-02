import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class TaskFive {
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
            Temp responseBody = response.as(Temp.class);

            assert "eve.holt@reqres.in".equals(responseBody.email);
            assert "pistol".equals(responseBody.password);

        }

    }
}

class Temp {
    public String email;
    public String password;
}
