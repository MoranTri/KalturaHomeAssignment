import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class homeAssignment {

    ApiResponses apiResponses;

    @BeforeMethod
    public void initSession() {
        apiResponses = new ApiResponses();
    }

    @Test
    public void t01() {
        String user = Utilities.randomString();
        String external = Utilities.randomString();
        apiResponses.postNewUser(user, external);

        System.out.println("Response Body: \n" + apiResponses.getResponseBody());
        System.out.println("Response Headers: \n" + apiResponses.getResponseHeaders());

        Assert.assertTrue(apiResponses.isHeaderExistInResponse("X-Kaltura-Session"));
        apiResponses.creationResponse();

    }
}
