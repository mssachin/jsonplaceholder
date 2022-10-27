package stepdefinitions;

import core.Resource;
import core.Resources;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import requestobjects.NewPost;
import responseobjects.*;
import tasks.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CommonSteps {
    private String scenarioUserId;
    private Response scenarioResponse;
    private Post[] myPosts;
    private Response postResourceRequestResponse;
    private Response editResourceRequestResponse;
    private Response queryResourceRequestResponse;
    private Response deleteResourceRequestResponse;

    @Given("I am a {string}")
    public void i_am_a(String user) {
        System.out.println("User is " + user);
    }

    @When("I query for all {string} in the network")
    public void i_query_for_all_in_the_network(String path) {
        ViewContent viewContent = new ViewContent();
        switch (path) {
            case "posts" -> scenarioResponse = viewContent.inTheNetwork(Resources.POSTS.getValue());
            case "albums" -> scenarioResponse = viewContent.inTheNetwork(Resources.ALBUMS.getValue());
            case "photos" -> scenarioResponse = viewContent.inTheNetwork(Resources.PHOTOS.getValue());
            case "todos" -> scenarioResponse = viewContent.inTheNetwork(Resources.TODOS.getValue());
            default -> throw new IllegalArgumentException("Unknown Object");
        }
    }

    @Then("I validate there are a total of {string} {string}")
    public void i_validate_there_are_a_total_of_posts_in_the_network(String numberOfObjects, String resource) {
        switch (resource) {
            case "posts" -> {
                Post[] allPosts = scenarioResponse
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(Post[].class);
                assertEquals(Integer.parseInt(numberOfObjects), allPosts.length);
            }
            case "albums" -> {
                Album[] allAlbums = scenarioResponse
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(Album[].class);
                assertEquals(Integer.parseInt(numberOfObjects), allAlbums.length);
            }
            case "photos" -> {
                Photo[] allPhotos = scenarioResponse
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(Photo[].class);
                assertEquals(Integer.parseInt(numberOfObjects), allPhotos.length);
            }
            case "todos" -> {
                Todo[] allTodos = scenarioResponse
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(Todo[].class);
                assertEquals(Integer.parseInt(numberOfObjects), allTodos.length);
            }
            default -> throw new IllegalArgumentException("Unknown Object");
        }

    }

    @Given("I am {string}")
    public void i_am(String username) {
        GetUserId getUserId = new GetUserId();
        scenarioUserId = getUserId.ofTheUser(username);
    }

    @When("I query for my {string} in the network")
    public void i_query_for_my_in_the_network(String path) {
        ViewContent posts = new ViewContent();
        scenarioResponse = posts.inTheNetwork(Resources.USERS.getValue(), scenarioUserId, path);
        myPosts = scenarioResponse
                .then()
                .statusCode(200)
                .extract()
                .as(Post[].class);

    }

    @Then("Only the posts pertinent to me are visible")
    public void only_the_posts_pertinent_to_me_are_visible() {
        boolean isOnlyMyPostsVisible = Arrays
                .stream(myPosts)
                .allMatch(post -> post.getUserId().equals(scenarioUserId));
        assertTrue(isOnlyMyPostsVisible);
    }

    @When("I make a post in the network")
    public void i_publish_a_in_the_network(DataTable postDetails) {
        Map<String, String> postDetailsAsMap = postDetails.asMap();
        NewPost newPost = new NewPost();
        newPost.setUserId(scenarioUserId);
        newPost.setTitle(postDetailsAsMap.get("title"));
        newPost.setBody(postDetailsAsMap.get("body"));
        PostAResource postAResource = new PostAResource();
        postResourceRequestResponse = postAResource.inTheNetwork(Resources.POSTS.getValue(), newPost);
    }

    @Then("I validate that post is successful")
    public void i_validate_that_post_is_successful() {
        String postId = postResourceRequestResponse.
                then()
                .statusCode(201)
                .extract()
                .as(Standard.class).getId();
        assertNotNull(postId);
    }

    @When("I query for a specific post {string}")
    public void i_query_for_a_specific_post(String postId) {
        ViewContent posts = new ViewContent();
        queryResourceRequestResponse = posts.inTheNetwork(Resources.POSTS.getValue(), postId);
    }

    @Then("I validate the post details")
    public void i_validate_the_post_details(DataTable postDetails) {
        Map<String, String> postDetailsAsMap = postDetails.asMap();
        Post expectedPost = new Post();
        expectedPost.setUserId(postDetailsAsMap.get("userId"));
        expectedPost.setId(postDetailsAsMap.get("id"));
        expectedPost.setTitle(postDetailsAsMap.get("title"));
        expectedPost.setBody(postDetailsAsMap.get("body"));
        Post actualPost = queryResourceRequestResponse.then().statusCode(200).extract().as(Post.class);
        assertEquals(expectedPost, actualPost);
    }

    @Then("I validate the post is not available")
    public void i_validate_the_post_is_not_available() {
        queryResourceRequestResponse.then().statusCode(404);
    }

    @When("I update a post of mine")
    public void i_update_a_post_of_mine(DataTable postDetails) {
        PutResource putResource = new PutResource();
        Map<String, String> postDetailsAsMap = postDetails.asMap();
        Map<String, String> postDetailsAsMapMutated = new HashMap<>(postDetailsAsMap);
        postDetailsAsMapMutated.put("userId", scenarioUserId);
        editResourceRequestResponse = putResource.inTheNetwork(Resources.POSTS.getValue() + "/" + postDetailsAsMapMutated.get("id"), postDetailsAsMapMutated);
    }

    @Then("The post is successfully updated")
    public void the_post_is_successfully_updated() {
        editResourceRequestResponse.then().statusCode(200);
    }

    @Then("The get an error response")
    public void the_get_a_valid_error_response() {
        editResourceRequestResponse.then().statusCode(500);
    }

    @When("I edit a post of mine")
    public void i_patch_a_post_of_mine(DataTable postDetails) {
        PatchResource patchResource = new PatchResource();
        Map<String, String> postDetailsAsMap = postDetails.asMap();
        Map<String, String> postDetailsAsMapMutated = new HashMap<>(postDetailsAsMap);
        postDetailsAsMapMutated.put("userId", scenarioUserId);
        editResourceRequestResponse = patchResource.inTheNetwork(Resources.POSTS.getValue() + "/" + postDetailsAsMapMutated.get("id"), postDetailsAsMapMutated);
    }

    @When("I delete {string} {string}")
    public void i_delete_a_post(String resource, String resourceId) {
        DeleteResource deleteResource = new DeleteResource();
        if (resource.equalsIgnoreCase(Resource.POST.getValue())) {
            deleteResourceRequestResponse = deleteResource.fromTheNetwork(Resources.POSTS.getValue(), resourceId);
        } else {
            throw new IllegalArgumentException("Unknown Object");
        }
    }

    @Then("I validate the post is successfully deleted")
    public void i_validate_the_post_is_successfully_deleted() {
        deleteResourceRequestResponse.then().statusCode(200);
    }

    @When("I attempt to post {string}")
    public void i_attempt_to_post_an(String resource, DataTable postDetails) {
        Map<String, String> postDetailsAsMap = postDetails.asMap();
        Map<String, String> postDetailsAsMapClone = new HashMap<>(postDetailsAsMap);
        postDetailsAsMapClone.put("userId", scenarioUserId);
        PostAResource postAResource = new PostAResource();
        postResourceRequestResponse = postAResource.inTheNetwork(resource, postDetailsAsMapClone);

    }

    @Then("I get success response")
    public void i_get_success_response() {
        Standard standard = postResourceRequestResponse.then().statusCode(201).extract().as(Standard.class);
        assertNotNull(standard.getId());
    }
}
