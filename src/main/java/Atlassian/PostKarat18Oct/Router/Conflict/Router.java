package Atlassian.PostKarat18Oct.Router.Conflict;

import java.util.*;

public class Router {


    Map<String, String> staticRoutes;
    List<WildCardRouter> wildCardRoutes;


    Router(RouteBuilder routeBuilder) {

        this.staticRoutes = routeBuilder.staticRoutes;
        this.wildCardRoutes = routeBuilder.wildCardRouters;
        this.wildCardRoutes.sort(Comparator.comparingInt(WildCardRouter::getSpecificity).reversed());
    }


    public String callRoute(String path) {

        if (staticRoutes.containsKey(path)) {

            return staticRoutes.get(path);
        }

        for (WildCardRouter wildCardRouter : wildCardRoutes) {

            if (wildCardRouter.matches(path)) {

                return wildCardRouter.getResult();
            }
        }

        return "404 : Not Found";
    }


    private static class WildCardRouter {

        String pattern;
        String result;
        int specificity;


        WildCardRouter(String pattern, String result) {

            this.pattern = pattern;
            this.result = result;
            this.specificity = calculateSpecificity(pattern);
        }


        public boolean matches(String path) {

            String regex = pattern.replace("*", ".*");
            return path.matches(regex);
        }

        public String getResult() {
            return result;
        }


        public static int calculateSpecificity(String pattern) {

            String[] segments = pattern.split("/");
            int specificity = 0;

            for (String segment : segments) {

                if (!segment.contains("*")) {
                    specificity++;
                }
            }
            return specificity;
        }

        public int getSpecificity() {
            return specificity;
        }
    }


    private static class RouteBuilder {

        Map<String, String> staticRoutes = new HashMap<>();
        List<WildCardRouter> wildCardRouters = new ArrayList<>();


        public RouteBuilder withRoute(String path, String result) {

            if (path.contains("*")) {

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


        Router router = new Router.RouteBuilder().withRoute("/foo", "fooResult")
                .withRoute("/bar/*/baz", "specificWildcardResult")
                .withRoute("/bar/*", "generalWildcardResult")
                .withRoute("/bar/exact", "exactBar").build();


        System.out.println(router.callRoute("/foo"));
        System.out.println(router.callRoute("/bar/test/baz"));
        System.out.println(router.callRoute("/bar/other"));
        System.out.println(router.callRoute("/bar/exact"));
        System.out.println(router.callRoute("/unknown"));
    }

}