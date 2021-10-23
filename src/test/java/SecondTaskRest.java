import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.List;

public class SecondTaskRest {

    @Test
    public void URL() {
        Response response = RestAssured.get("https://chercher.tech/sample/api/product/read");
        System.out.println(response.getBody().asString());

        int statusCode = response.getStatusCode();
        assert statusCode == 200;

    }

    @Test
    public void checkTestCase() {
        Response response = RestAssured.get("https://chercher.tech/sample/api/product/read").
                then().statusCode(200).extract().response();
        JsonPath jsonPath = response.jsonPath();

        String lastValue = "CreateRecord";
        String lastRecordNameValue = jsonPath.get("records[1699].name");
        assert lastRecordNameValue.equals(lastValue);

        List<String> allRecordsTime = jsonPath.get("records.created");
        
        int currentTime = 2021;
        boolean temp = false;
        for (int i = 0; i < allRecordsTime.size(); i++) {
            if (allRecordsTime.get(i).contains("2021")) {
                temp = true;
                break;
            }
        }

        Response secondResponse = RestAssured.get("https://reqres.in/api/users ").then()
                .statusCode(200).extract().response();

    }
}
