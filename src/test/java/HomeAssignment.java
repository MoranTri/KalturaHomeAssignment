import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeAssignment {

    ApiResponses apiResponses = new ApiResponses();
    Variables variables = new Variables();

    @Test
    public void createNewUser() {
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

    @Test(dependsOnMethods = {"createNewUser"})
    public void loginWithTheNewUser() {
        apiResponses.loginRequest(variables.getUserName(), variables.getPassword());

        System.out.println("Response Body: \n" + apiResponses.getResponseBody());
        System.out.println("Response Headers: \n" + apiResponses.getResponseHeaders());

        Assert.assertTrue(apiResponses.lastLoginDateKeyExist(variables));
        System.out.println(Utilities.parseMillisecondsDate(variables.getLastLoginDate()));
    }

    @Test(dependsOnMethods = {"createNewUser"})
    public void registerWithExistenceDetails() {
        apiResponses.createNewUserRequest(variables.getUserName(), variables.getPassword(), variables.getExternalId());

        System.out.println("Response Body: \n" + apiResponses.getResponseBody());
        System.out.println("Response Headers: \n" + apiResponses.getResponseHeaders());

        apiResponses.registerErrorMessage(variables);
        Assert.assertEquals(variables.getErrorCode(), 2014);
        Assert.assertEquals(variables.getErrorMessage(), "User exists");
    }
}
