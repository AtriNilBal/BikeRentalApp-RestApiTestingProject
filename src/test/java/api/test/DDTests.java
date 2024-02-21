package api.test;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DDTests {

    @Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
    public void testPostUser(String userID, String userName, String fname, String lname, String userEmail, String phone, String pwd) {
        User userPayload=new User();
        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(userName);
        userPayload.setFirstName(fname);
        userPayload.setLastName(lname);
        userPayload.setEmail(userEmail);
        userPayload.setPassword(pwd);
        userPayload.setPhone(phone);

        Response response= UserEndpoints.createUser(userPayload);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
    public void testDeleteUserByName(String userName) {
        Response response=UserEndpoints.deleteUser(userName);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
