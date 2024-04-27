package api.endpoints;

import api.payloads.ResponseObject;
import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.given;

public class UserEndpointOperations {

    public static Response createUser(User payload) {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
        .when()
                .post(UserEndpoints.postUrl);

        return response;
    }

    public static ResponseObject createTestUser(User payload) {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(UserEndpoints.postUrl);

        return new ResponseObject(response.statusCode(), response.body().print());
    }

    public static Response readUser(String username) {
        Response response=given()
                    .pathParam("username", username)
                .when()
                .get(UserEndpoints.getUrl);
        return response;
    }

    public static Response updateUser(String username, User payload) {
        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",username)
                .body(payload)
                .when()
                .put(UserEndpoints.updateUrl);
        return response;
    }

    public static Response deleteUser(String username) {
        Response response=given()
                .pathParam("username", username)
                .when()
                .get(UserEndpoints.deleteUrl);
        return response;
    }
}
