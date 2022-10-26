package utilities;

import core.BaseApiSpec;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestMethods extends BaseApiSpec {
    public static Response getResource(String... args) {
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            sb.append(arg).append("/");
        }
        return given()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .baseUri(baseURI)
                .basePath(sb.toString())
                .when()
                .get();
    }

    public static Response getResource(Map<String, String> queryParams, String... args) {
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            sb.append(arg).append("/");
        }
        return given()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .baseUri(baseURI)
                .basePath(sb.toString())
                .queryParams(queryParams)
                .when()
                .get();
    }


    public static Response postResource(String path, Object body) {
        return given()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .baseUri(baseURI)
                .basePath(path)
                .when()
                .with()
                .body(body)
                .post();
    }

    public static Response putResource(String path, Object body) {
        return given()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .baseUri(baseURI)
                .basePath(path)
                .when()
                .with()
                .body(body)
                .put();
    }

    public static Response patchResource(String path, Object body) {
        return given()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .baseUri(baseURI)
                .basePath(path)
                .when()
                .with()
                .body(body)
                .patch();
    }

    public static Response deleteResource(String resource, String resourceId){
        return given()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .baseUri(baseURI)
                .basePath(resource + "/" + resourceId)
                .when().delete();
    }

}
