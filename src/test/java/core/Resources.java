package core;

public enum Resources {
    POSTS("posts", "post"),
    COMMENTS("comments", "comment"),
    ALBUMS("albums", "album"),
    PHOTOS("photos", "photo"),
    USERS("users", "user"),
    TODOS("todos", "todo");

    private final String plural;
    private final String singular;

    Resources(String plural, String singular) {
        this.plural = plural;
        this.singular = singular;
    }

    public String getPlural() {
        return plural;
    }

    public String getSingular() {
        return singular;
    }
}
