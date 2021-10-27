import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiRequests {

    Response request;

    public Response postNewUser(String userName, String externalId) {
        String bodyContent = "{\r\n\"apiVersion\": \"6.0.0\",\r\n\"partnerId\": 3197,\r\n\"user\": {\r\n\"objectType\": \"KalturaOTTUser\",\r\n\"username\": " + "\"" + userName + "\"" + ",\r\n\"firstName\": \"ott_user_lWkiwzTJJGYI\",\r\n\"lastName\": \"1585130417330\",\r\n\"email\": \"QATest_1585130417313@mailinator.com\",\r\n\"address\": \"ott_user_lWkiwzTJJGYI fake address\",\r\n\"city\": \"ott_user_lWkiwzTJJGYI fake city\",\r\n\"countryId\": 5,\r\n\"externalId\": " + "\"" + externalId + "\"" + "},\r\n\"password\": \"password_SlLVWDLl\"\r\n}";

        request = given()
                .header("Content-Type", "application/json")
                .body(bodyContent)
                .when()
                .post("https://api.frs1.ott.kaltura.com/api_v3/service/ottuser/action/register")

                .then()
                .statusCode(200)
                .extract()
                .response();

        return request;
    }
}
