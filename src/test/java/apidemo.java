import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Optional;

import static io.restassured.RestAssured.given;

public class apidemo {


    String basurl ="https://reqres.in/api/users?page=1";

    @Test
    public void apiget(){
        RestAssured.baseURI = "https://reqres.in/";
        Response response = RestAssured
                .given().contentType("application/json")

                .queryParam("page", "1")

                .when().get("api/users")

                .then().assertThat().statusCode(200).extract().response();


        System.out.println(response.getBody().asString());

        JsonPath jsonPathevel = response.jsonPath();

        Assert.assertEquals(jsonPathevel.get("$.page"),"1");
        String page = jsonPathevel.get("$.page");

        System.out.println(page);



    }
}
