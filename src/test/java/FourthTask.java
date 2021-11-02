import io.restassured.RestAssured;
import io.restassured.internal.path.xml.NodeChildrenImpl;
import io.restassured.path.xml.element.Node;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

public class FourthTask {

    NodeChildrenImpl countries = RestAssured.given().when()
            .get("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/ListOfContinentsByName")
            .then().extract().path("ArrayOftContinent.tContinent.sName");

    NodeChildrenImpl sCode = RestAssured.given().when()
            .get("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/ListOfContinentsByName")
            .then().extract().path("ArrayOftContinent.tContinent");

    @Test
    public void checkCountries() {

        ArrayList<Node> countryList = new ArrayList<>();

        System.out.println(countries.size());

        for (int i = 0; i < 6; i++) {
            countryList.add(countries.get(i));
        }

        assert "Africa".equals(countryList.get(0).toString());
        assert "Antarctica".equals(countryList.get(1).toString());
        assert "Asia".equals(countryList.get(2).toString());
        assert "Europe".equals(countryList.get(3).toString());
        assert "Ocenania".equals(countryList.get(4).toString());
        assert "The Americas".equals(countryList.get(5).toString());

        System.out.println(countryList);

    }

    @Test
    public void validateSizeOfSnameValues() {
        assert 6 == countries.size();
    }

    @Test
    public void validateScode() {

        ArrayList<Node> sCodeList = new ArrayList<>();
        for (int i = 0; i < sCode.size(); i++) {
            sCodeList.add(sCode.get(i));
        }

        int index = 0;
        for (int i = 0; i < sCodeList.size(); i++) {
            if (sCodeList.get(i).toString().equals("AN")) {
                index = i;
            }
        }
        assert "Antarctica".equals(countries.get(index).toString());
    }

    @Test
    public void validateLastContinent() {
        assert "The Americas".equals(countries.get(5).toString());
    }

    @Test
    public void c() {
        System.out.println(sCode.size());
    }
}


//http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/ListOfContinentsByName