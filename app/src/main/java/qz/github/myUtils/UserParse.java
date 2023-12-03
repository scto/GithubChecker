package qz.github.myUtils;

import java.net.URI;
import java.net.URISyntaxException;

public class UserParse {
    public static String parseUrl(String test) {
        try {
            URI uri = new URI(test);
            return uri.getPath().replace("/", "");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return test;
        }
    }
}