package stepdefinitions;

import core.Resources;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import responseobjects.Comment;
import tasks.FilterResource;
import tasks.ViewContent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommentsSteps {
    private Response viewCommentsResponse;

    @When("I query for all {string} pertinent to post {string}")
    public void i_query_for_all_pertinent_to_a_post(String resource, String postId) {
        ViewContent viewComments = new ViewContent();
        viewCommentsResponse = viewComments.inTheNetwork(Resources.POSTS.getPlural(), postId, resource);
    }

    @Then("I validate that I can only view comments for post {string}")
    public void i_validate_that_i_can_only_view_comments_for_that_post(String postId) {
       Comment[] comments = viewCommentsResponse.as(Comment[].class);
       Arrays.stream(comments).forEach(comment -> Assertions.assertTrue(comment.getPostId().equalsIgnoreCase(postId)));
    }

    @When("I filter comments pertinent to post {string}")
    public void i_query_for_all_pertinent_to_post_with_query_parameter(String postId) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("postId", postId);
        FilterResource filterResource = new FilterResource();
        viewCommentsResponse = filterResource.pertinentToAPost(queryParams, Resources.COMMENTS.getPlural());

    }


}
