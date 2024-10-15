package Atlassian.PostKarat.Router.Variable;

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

        return new RouteResult("404 : Route Not Found", Collections.emptyMap());
    }


    private static class RouteResult {


        String result;
        Map<String, String> variables;


        RouteResult(String result, Map<String, String> variables) {


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
            return "RouteResult{" +
                    "result='" + result + '\'' +
                    ", variables=" + variables +
                    '}';
        }
    }


    public static class WildCardRouter {

        String pattern;
        String result;
        Pattern regexPattern;
        List<String> variables;
        int specificity;


        WildCardRouter(String pattern, String result) {

            this.pattern = pattern;
            this.result = result;
            this.variables = new ArrayList<>();
            this.regexPattern = generateRegexPattern(pattern);
            this.specificity = calculateSpecificity(pattern);
        }

        private Pattern generateRegexPattern(String pattern) {

            String[] parts = pattern.split("/");
            StringBuilder regex = new StringBuilder();
            for (String part : parts) {

                if (part.startsWith(":")) {

                    variables.add(part.substring(1));
                    regex.append("/([^/]+)");
                } else {

                    regex.append("/").append(Pattern.quote(part));
                }
            }

            return Pattern.compile(regex.toString());
        }


        private Map<String, String> matchAndExtract(String pattern) {

            Matcher matcher = regexPattern.matcher(pattern);

            if (!matcher.matches()) {

                return null;
            }

            Map<String, String> vars = new HashMap<>();

            for (int i = 0; i < variables.size(); i++) {

                vars.put(variables.get(i), matcher.group(i + 1));
            }

            return vars;
        }


        public String getResult() {

            return result;
        }


        public int calculateSpecificity(String pattern) {


            String[] pts = pattern.split("/");
            int specificity = 0;

            for (String patt : pts) {

                if (!patt.startsWith(":")) {

                    specificity++;
                }
            }

            return specificity;
        }

        public int getSpecificity() {

            return specificity;
        }
    }

    public static class RouteBuilder {

        Map<String, String> staticRoutes = new HashMap<>();
        List<WildCardRouter> wildCardRouters = new ArrayList<>();


        public RouteBuilder withRoute(String path, String result) {

            if (path.contains("*") || path.contains(":")) {

                wildCardRouters.add(new WildCardRouter(path, result));
            }


            staticRoutes.put(path, result);
            return this;
        }


        public Router build() {

            return new Router(this);
        }


    }


    public static void main(String[] args) {


        Router router = new Router.RouteBuilder().withRoute("/user/:id", "userProfile")
                .withRoute("/product/:category/:id", "productPage")
                .withRoute("/foo", "fooResult")
                .build();



        System.out.println(router.callRoute("/foo"));
        System.out.println(router.callRoute("/user/123"));
        System.out.println(router.callRoute("/product/electronics/456"));
    }




}
