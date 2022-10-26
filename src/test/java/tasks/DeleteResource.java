package tasks;

import io.restassured.response.Response;
import utilities.RestMethods;

public class DeleteResource {
    public Response fromTheNetwork(String resource, String resourceId) {
      return  RestMethods.deleteResource(resource, resourceId);
    }
}
