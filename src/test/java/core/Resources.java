package core;

public enum Resources {
    POSTS("posts"),
    COMMENTS("comments"),
    ALBUMS("albums"),
    PHOTOS("photos"),
    USERS("users"),
    TODOS("todos");

    private final String value;

    Resources(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}
