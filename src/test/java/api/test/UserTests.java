package api.test;

import api.endpoints.UserEndpointOperations;
import api.payloads.ResponseObject;
import api.payloads.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {

    Faker faker;
    User userPayload;
    private static final Logger LOGGER = LogManager.getLogger(UserTests.class.getName());

    @BeforeClass
    public void setup() {
        faker=new Faker();
        userPayload=new User();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        LOGGER.info(userPayload.toString());
    }

    @Test(priority=1)
    public void testPostUser() {
        Response response= UserEndpointOperations.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        LOGGER.info(" ****** created user ****** ");
    }

    @Test(priority=2)
    public void testGetUserByName() {
        Response response= UserEndpointOperations.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        LOGGER.info(" ****** received user details ****** ");
    }

    @Test(priority=3)
    public void testUpdateUser() {
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response= UserEndpointOperations.updateUser(userPayload.getUsername(), this.userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

        Response responseAfterUpdate= UserEndpointOperations.readUser(this.userPayload.getUsername());
        responseAfterUpdate.then().log().body().statusCode(200);
        Assert.assertEquals(response.getStatusCode(), 200);
        LOGGER.info(" ****** updated user ****** ");
    }

    @Test(priority = 4)
    public void testDeleteUserByName() {
        Response response= UserEndpointOperations.deleteUser(userPayload.getUsername());
        response.then().log().body().statusCode(200);
        LOGGER.info(" ****** deleted user ****** ");
    }

}
