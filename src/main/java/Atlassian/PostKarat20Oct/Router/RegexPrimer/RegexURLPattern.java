package Atlassian.PostKarat20Oct.Router.RegexPrimer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexURLPattern {


    public static void main(String[] args) {


        String path = "/user/profile/123";
        Pattern pattern = Pattern.compile("/([^/]+)");
        Matcher matcher = pattern.matcher(path);

        while (matcher.find()) {
            System.out.println(matcher.group()); // Prints each captured segment: "user", "profile", "123"
        }
    }
}
