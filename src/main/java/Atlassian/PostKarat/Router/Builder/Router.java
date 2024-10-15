package Atlassian.PostKarat.Router.Builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Router {


    Map<String, String> staticRoutes;
    List<WildCardRoute> wildCardRoutes;


    Router(RouteBuilder routeBuilder) {

        this.staticRoutes = routeBuilder.staticRoutes;
        this.wildCardRoutes = routeBuilder.wildCardRoutes;
    }


    public String callRoute(String path) {

        if (staticRoutes.containsKey(path)) {

            return staticRoutes.get(path);
        }


        for (WildCardRoute wildCardRoute : wildCardRoutes) {

            if (wildCardRoute.matches(path)) {

                return wildCardRoute.getResult();
            }
        }

        return "404 : Route Not Found";
    }


    private static class RouteBuilder {


        Map<String, String> staticRoutes = new HashMap<>();
        List<WildCardRoute> wildCardRoutes = new ArrayList<>();


        public RouteBuilder withRoute(String path, String result) {

            if (path.contains("*")) {
                wildCardRoutes.add(new WildCardRoute(path, result));
            }

            staticRoutes.put(path, result);

            return this;
        }


        public Atlassian.PostKarat.Router.Builder.Router build() {

            return new Atlassian.PostKarat.Router.Builder.Router(this);
        }
    }


    private static class WildCardRoute {

        String pattern;
        String result;


        WildCardRoute(String pattern, String result) {

            this.pattern = pattern;
            this.result = result;
        }


        public boolean matches(String path) {

            String regex = pattern.replace("*", ".*");

            return path.matches(regex);
        }


        public String getResult() {

            return result;
        }
    }


    public static void main(String[] args) {


        Router router = new Router.RouteBuilder()
                .withRoute("/foo", "fooResult")
                .withRoute("/bar/*/baz", "wildcardResult")
                .withRoute("/bar/exact", "exactBar")
                .build();

        System.out.println(router.callRoute("/foo"));
        System.out.println(router.callRoute("/bar/test/baz"));
        System.out.println(router.callRoute("/bar/other/baz"));
        System.out.println(router.callRoute("/bar/exact"));
        System.out.println(router.callRoute("/unknown"));
    }
}
