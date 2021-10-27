import io.restassured.path.json.JsonPath;

public class ApiResponses extends ApiRequests {

    JsonPath jsonPath;

    public String getResponseHeaders() {
        return request.getHeaders().toString();
    }

    public String getResponseBody() {
        return request.body().asString();
    }

    public boolean isHeaderExistInResponse(String headerName) {
        return request.getHeaders().hasHeaderWithName(headerName);
    }

    public void creationResponse() {
        jsonPath = request.jsonPath();

        System.out.println(jsonPath.getMap("result").containsKey("id"));
        System.out.println(jsonPath.getString("result.id").getClass().getSimpleName());
        System.out.println(jsonPath.getMap("result").containsKey("countryId"));
        System.out.println(jsonPath.getInt("result.countryId").getClass().getSimpleName());
    }
}
