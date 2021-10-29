import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeAssignment {

    ApiResponses apiResponses = new ApiResponses();
    Variables variables = new Variables();

    @Test(description = "Register new user with userName, password and externalId that randomly generate each run")
    public void registerNewUser() {
        Utilities.generateNewUserDetails(variables);
        apiResponses.createNewUserRequest(variables.getUserName(), variables.getPassword(), variables.getExternalId());

        System.out.println("Response Body: \n" + apiResponses.getResponseBody());
        System.out.println("Response Headers: \n" + apiResponses.getResponseHeaders());

        Assert.assertTrue(apiResponses.isHeaderExistInResponse("X-Kaltura-Session"));
        Assert.assertTrue(apiResponses.idKeyExist());
        Assert.assertTrue(apiResponses.countryIdKeyExist());
        Assert.assertEquals(apiResponses.idKeyExistenceAndType(), "String");
        Assert.assertEquals(apiResponses.countryIdKeyExistenceAndType(), "Integer");
    }

    @Test(description = "Login with the new user that we just registered", dependsOnMethods = {"registerNewUser"})
    public void loginWithTheNewUser() {
        apiResponses.loginRequest(variables.getUserName(), variables.getPassword());

        System.out.println("Response Body: \n" + apiResponses.getResponseBody());
        System.out.println("Response Headers: \n" + apiResponses.getResponseHeaders());

        Assert.assertTrue(apiResponses.lastLoginDateKeyExist(variables));
        System.out.println("Last login date in a valid date format: " + Utilities.parseMillisecondsDate(variables.getLastLoginDate()));
    }

    @Test(description = "Second attempt to register new user but with userName, password and externalId that already exist in the system", dependsOnMethods = {"registerNewUser"})
    public void registerWithExistenceDetails() {
        apiResponses.createNewUserRequest(variables.getUserName(), variables.getPassword(), variables.getExternalId());

        System.out.println("Response Body: \n" + apiResponses.getResponseBody());
        System.out.println("Response Headers: \n" + apiResponses.getResponseHeaders());

        apiResponses.registerErrorMessage(variables);
        Assert.assertEquals(variables.getErrorCode(), 2014);
        Assert.assertEquals(variables.getErrorMessage(), "User exists");
    }
}
