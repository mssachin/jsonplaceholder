package tasks;

import responseobjects.User;
import utilities.RestMethods;

import java.util.Arrays;

public class GetUserId {
    public String ofTheUser(String username) {
        User[] users = RestMethods.getResource("users").as(User[].class);
        return Arrays.stream(users)
                .filter(user -> user.getUsername()
                        .equalsIgnoreCase(username))
                .findFirst()
                .orElse(new User())
                .getId();
    }
}
