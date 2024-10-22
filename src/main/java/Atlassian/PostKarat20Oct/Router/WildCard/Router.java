package Atlassian.PostKarat20Oct.Router.WildCard;

import java.util.*;

public class Router {


    Map<String, String> staticRoutes;
    List<WildCardRouter> wildCardRoutes;


    Router(RouteBuilder routeBuilder) {

        this.staticRoutes = routeBuilder.staticRoutes;
        this.wildCardRoutes = routeBuilder.wildCardRouters;
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


        WildCardRouter(String pattern, String result) {

            this.pattern = pattern;
            this.result = result;
        }


        public boolean matches(String path) {

            String regex = pattern.replace("*", ".*");
            return path.matches(regex);
        }

        public String getPattern() {
            return pattern;
        }

        public String getResult() {
            return result;
        }
    }


    private static class RouteBuilder {

        Map<String, String> staticRoutes = new HashMap<>();
        List<WildCardRouter> wildCardRouters = new ArrayList<>();


        public RouteBuilder withRoute(String path, String result) {

            if (path.contains("*")) {
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


        Router router = new Router.RouteBuilder().withRoute("/foo", "homePage").withRoute("/data/*/view", "accountsView").build();

        System.out.println(router.callRoute("/foo"));
        System.out.println(router.callRoute("/fooz"));
        System.out.println(router.callRoute("/data/123/view"));
    }
}



