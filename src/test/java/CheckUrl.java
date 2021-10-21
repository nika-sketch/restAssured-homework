import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import static org.hamcrest.Matchers.equalTo;

public class CheckUrl {
    @Test
    public void URL() {
        Response response = RestAssured.get("http://ergast.com/api/f1/2017/circuits.json");
        System.out.println(response.getBody().asString());

        int statusCode = response.getStatusCode();
        assert statusCode == 200;

    }

    @Test
    public void checkFirstAndFifth() {

        String EXPECTED_FIRST_COUNTRY = "americas";
        String EXPECTED_FIFTH_COUNTER = "hungaroring";

        RestAssured.given().get("http://ergast.com/api/f1/2017/circuits.json").then().statusCode(200)
                .body("MRData.CircuitTable.Circuits.circuitId[1]", equalTo(EXPECTED_FIRST_COUNTRY));

        RestAssured.given().get("http://ergast.com/api/f1/2017/circuits.json").then().statusCode(200)
                .body("MRData.CircuitTable.Circuits.circuitId[5]", equalTo(EXPECTED_FIFTH_COUNTER));
    }
}
