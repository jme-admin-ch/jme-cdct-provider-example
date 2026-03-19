package ch.admin.bit.jeap.jme.processcontext.test;

import ch.admin.bit.jeap.jme.test.BootServiceIntegrationTestBase;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Slf4j
@SuppressWarnings("unchecked")
public class CdctProviderExampleIT extends BootServiceIntegrationTestBase {

    private static final String AUTH_BASE_URL = "http://localhost:8180/jme-cdct-auth-scs";
    private static final String SCS_BASE_URL = "http://localhost:8080/jme-cdct-provider-service";

    @BeforeAll
    static void startServices() throws Exception {
        startService("jme-cdct-auth-scs", AUTH_BASE_URL);
        startService("jme-cdct-provider-service", SCS_BASE_URL);
    }

    @Test
    void createAndGetTask() {
        String accessToken = fetchAccessToken(AUTH_BASE_URL, "jme-cdct-consumer-service", "secret");

        // Create a new task
        String taskId = given()
                .baseUri(SCS_BASE_URL)
                .header("Authorization", "Bearer " + accessToken)
                .contentType(ContentType.JSON)
                .body("""
                        {"title": "Integration Test Task", "content": "Created by IT", "tag": "test"}
                        """)
                .when()
                .post("/api/task")
                .then()
                .statusCode(200)
                .extract().path("id");

        // Get the created task by id and assert fields
        given()
                .baseUri(SCS_BASE_URL)
                .header("Authorization", "Bearer " + accessToken)
                .accept(ContentType.JSON)
                .when()
                .get("/api/task/{id}", taskId)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(taskId))
                .body("title", equalTo("Integration Test Task"))
                .body("content", equalTo("Created by IT"))
                .body("tag", equalTo("test"))
                .body("createdAt", notNullValue());
    }
}
