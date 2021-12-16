package exam.properties;

public class ConnectionParameters {
    private final String url = "jdbc:postgresql://localhost:5432/database";
    private final String username = "postgres";
    private final String password = "Post777SQL";

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
