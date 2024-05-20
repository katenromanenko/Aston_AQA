import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostmanEchoTests {

    static {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    public void testGetMethod() {
        given()
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .body("args", equalTo("args"));
    }

    @Test
    public void testPostMethod() {
        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo(requestBody))
                .body("url", equalTo("https://postman-echo.com/post"));
    }

    @Test
    public void testPutMethod() {
        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .body(requestBody)
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("data", equalTo(requestBody))
                .body("url", equalTo("https://postman-echo.com/put"));
    }

    @Test
    public void testDeleteMethod() {
        given()
                .when()
                .delete("/delete")
                .then()
                .statusCode(200)
                .body("json", equalTo(null));
    }

    @Test
    public void testPatchMethod() {
        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .body(requestBody)
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .body("data", equalTo(requestBody))
                .body("url", equalTo("https://postman-echo.com/patch"));
    }

    @Test
    public void testHeadMethod() {
        given()
                .when()
                .head("/get")
                .then()
                .statusCode(200);
    }

    @Test
    public void testOptionsMethod() {
        given()
                .when()
                .options("/get")
                .then()
                .statusCode(200)
                .header("Allow", equalTo("GET,HEAD,PUT,POST,DELETE,PATCH"));
    }
}
