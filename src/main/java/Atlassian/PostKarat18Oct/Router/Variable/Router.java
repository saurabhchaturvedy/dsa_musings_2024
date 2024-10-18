package Atlassian.PostKarat18Oct.Router.Variable;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Router {


    Map<String, String> staticRoutes;
    List<WildCardRouter> wildCardRouters;


    Router(RouteBuilder routeBuilder) {

        this.staticRoutes = routeBuilder.staticRoutes;
        this.wildCardRouters = routeBuilder.wildCardRouters;
        this.wildCardRouters.sort(Comparator.comparingInt(WildCardRouter::getSpecificity).reversed());

    }


    public RouteResult callRoute(String path) {

        if (staticRoutes.containsKey(path)) {

            return new RouteResult(staticRoutes.get(path), Collections.emptyMap());
        }

        for (WildCardRouter wildCardRouter : wildCardRouters) {

            Map<String, String> variables = wildCardRouter.matchAndExtract(path);

            if (variables != null) {

                return new RouteResult(wildCardRouter.getResult(), variables);
            }
        }

        return new RouteResult("404 : Not Found", Collections.emptyMap());
    }


    private static class RouteResult {

        String result;
        Map<String, String> variables;

        public RouteResult(String result, Map<String, String> variables) {
            this.result = result;
            this.variables = variables;
        }


        String getResult() {
            return result;
        }

        Map<String, String> getVariables() {
            return variables;
        }


        @Override
        public String toString() {
            return "RouteResult{" +
                    "result='" + result + '\'' +
                    ", variables=" + variables +
                    '}';
        }
    }


    private static class WildCardRouter {

        String pattern;
        String result;
        List<String> variables;
        Pattern regexPattern;
        int specificity;


        WildCardRouter(String pattern, String result) {

            this.pattern = pattern;
            this.result = result;
            this.variables = new ArrayList<>();
            this.regexPattern = getRegexPattern(pattern);
            this.specificity = calculateSpecificity(pattern);


        }


        public Pattern getRegexPattern(String pattern) {

            StringBuilder regex = new StringBuilder();

            String[] parts = pattern.split("/");

            for (String part : parts) {

                if (part.isEmpty()) {
                    continue;
                }

                regex.append("/");
                if (part.startsWith(":")) {

                    variables.add(part.substring(1));
                    regex.append("([^/]+)");
                } else {

                    regex.append(Pattern.quote(part));
                }
            }
            regex.append("/?$");
            return Pattern.compile(regex.toString());
        }


        public Map<String, String> matchAndExtract(String pattern) {

            Matcher matcher = regexPattern.matcher(pattern);

            if (!matcher.matches()) {
                return null;
            }

            Map<String, String> variables = new HashMap<>();

            for (int i = 0; i < this.variables.size(); i++) {

                variables.put(this.variables.get(i), matcher.group(i + 1));
            }

            return variables;
        }


        public int calculateSpecificity(String pattern) {

            String[] segments = pattern.split("/");

            int specificity = 0;

            for (String segment : segments) {

                if (!segment.startsWith(":")) {
                    specificity++;
                }
            }

            return specificity;
        }

        public String getResult() {
            return result;
        }

        public int getSpecificity() {
            return specificity;
        }

    }


    private static class RouteBuilder {

        Map<String, String> staticRoutes = new HashMap<>();
        List<WildCardRouter> wildCardRouters = new ArrayList<>();


        public RouteBuilder withRoute(String path, String result) {

            if (path.contains("*") || path.contains(":")) {
                wildCardRouters.add(new WildCardRouter(path, result));
            } else {

                staticRoutes.put(path, result);

            }

            return this;
        }


        public Router build() {
            return new Router(this);
        }
    }


    public static void main(String[] args) {


        Router router = new Router.RouteBuilder().
                withRoute("/account/:accountNumber/status", "PayPal_Account").build();


        System.out.println(router.callRoute("/account/123/status"));
        System.out.println(router.callRoute("/accountx/123/status"));

    }
}
