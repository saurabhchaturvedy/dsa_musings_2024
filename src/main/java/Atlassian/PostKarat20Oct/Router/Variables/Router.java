package Atlassian.PostKarat20Oct.Router.Variables;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Router {


    Map<String, String> staticRoutes;
    List<WildCardRouter> wildCardRouters;


    Router(RouteBuilder routeBuilder) {

        this.staticRoutes = routeBuilder.staticRoutes;
        this.wildCardRouters = routeBuilder.wildCardRouters;
    }


    public RouteResponse callRoute(String path) {

        if (staticRoutes.containsKey(path)) {

            return new RouteResponse(staticRoutes.get(path), Collections.emptyMap());
        }

        for (WildCardRouter wildCardRouter : wildCardRouters) {

            Map<String, String> variables = wildCardRouter.extractVariableValues(path);

            if (variables != null) {

                return new RouteResponse(wildCardRouter.getResult(), variables);
            }
        }

        return new RouteResponse("return 404 : Not Found", Collections.emptyMap());
    }


    private static class RouteResponse {

        String result;
        Map<String, String> variables;


        public RouteResponse(String result, Map<String, String> variables) {
            this.result = result;
            this.variables = variables;
        }


        public String getResult() {
            return result;
        }

        public Map<String, String> getVariables() {
            return variables;
        }

        @Override
        public String toString() {
            return "RouteResponse{" +
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
            this.specificity = calculateSpecificity(pattern);
            this.regexPattern = getRegexPattern(pattern);

        }

        public Pattern getRegexPattern(String pattern) {

            String[] parts = pattern.split("/");
            StringBuilder sb = new StringBuilder();

            for (String part : parts) {

                if (part.isEmpty()) {
                    continue;
                }

                sb.append("/");
                if (part.startsWith(":")) {

                    this.variables.add(part.substring(1));
                    sb.append("([^/]+)");
                } else {

                    sb.append(Pattern.quote(part));
                }
            }
            sb.append("/?$");

            return Pattern.compile(sb.toString());
        }


        public Map<String, String> extractVariableValues(String pattern) {

            Matcher matcher = regexPattern.matcher(pattern);

            if (!matcher.matches()) {
                return null;
            }

            Map<String, String> variablesMap = new HashMap<>();

            for (int i = 0; i < this.variables.size(); i++) {

                variablesMap.put(this.variables.get(i), matcher.group(i + 1));
            }

            return variablesMap;
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

        public List<String> getVariables() {
            return variables;
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


        Router router = new Router.RouteBuilder().withRoute("/account/:accountNumber/status", "PayPal_Landing_Page").build();

        System.out.println(router.callRoute("/account/46477489/status"));
    }

}
