import io.restassured.path.json.JsonPath;

import java.util.Map;

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

    public boolean idKeyExist() {
        jsonPath = request.jsonPath();
        return jsonPath.getMap("result").containsKey("id");
    }

    public boolean countryIdKeyExist() {
        jsonPath = request.jsonPath();
        return jsonPath.getMap("result").containsKey("countryId");
    }

    public String idKeyExistenceAndType() {
        jsonPath = request.jsonPath();
        if (jsonPath.getMap("result").containsKey("id")) {
            try {
                System.out.println("Type of 'id' value: " + jsonPath.getString("result.id").getClass().getSimpleName());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else System.out.println("No such kay exist in the 'result' map");
        return jsonPath.getString("result.id").getClass().getSimpleName();
    }

    public String countryIdKeyExistenceAndType() {
        jsonPath = request.jsonPath();
        if (jsonPath.getMap("result").containsKey("countryId")) {
            try {
                System.out.println("Type of 'countryId' value: " + ((Object) jsonPath.get("result.countryId")).getClass().getSimpleName());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else System.out.println("No such kay exist in the 'result' map");
        return ((Object) jsonPath.get("result.countryId")).getClass().getSimpleName();
    }

    public boolean lastLoginDateKeyExist(Variables variables) {
        jsonPath = request.jsonPath();
        for (Map.Entry<Object, Object> entry : jsonPath.getMap("result").entrySet()) {
            System.out.println("Does key '" + entry.getKey() + "' contain the value 'lastLoginDate': " + entry.getValue().toString().contains("lastLoginDate"));
            if (entry.getValue().toString().contains("lastLoginDate")) {
                variables.setLastLoginDate(jsonPath.getInt("result." + entry.getKey() +".lastLoginDate"));
                return true;
            }
        }
        return false;
    }

    public void registerErrorMessage(Variables variables) {
        jsonPath = request.jsonPath();
        System.out.println("Error code: " + jsonPath.getInt("result.error.code"));
        System.out.println("Error message: " + jsonPath.getString("result.error.message"));
        variables.setErrorCode(jsonPath.getInt("result.error.code"));
        variables.setErrorMessage(jsonPath.getString("result.error.message"));
    }
}
