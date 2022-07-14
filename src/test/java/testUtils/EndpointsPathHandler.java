package testUtils;

public class EndpointsPathHandler {
    public static String getPathWithNameParameter(String path, String id) {
        if (id.equals("")) {
            return path.replace("/{id}", "");
        } else {
            return path.replace("{id}", id);
        }
    }
}
