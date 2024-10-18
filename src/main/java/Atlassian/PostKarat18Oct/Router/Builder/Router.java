package Atlassian.PostKarat18Oct.Router.Builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Router {


    Map<String, String> staticRoutes;
    List<WildCardRouter> wildCardRouters;


    Router(RouteBuilder routeBuilder) {

        this.staticRoutes = routeBuilder.staticRoutes;
        this.wildCardRouters = routeBuilder.wildCardRoutes;
    }


    public String callResult(String path) {

        if (staticRoutes.containsKey(path)) {

            return staticRoutes.get(path);
        }

        for (WildCardRouter wildCardRouter : wildCardRouters) {

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


        public String getResult() {
            return result;
        }
    }

    private static class RouteBuilder {

        Map<String, String> staticRoutes = new HashMap<>();
        List<WildCardRouter> wildCardRoutes = new ArrayList<>();


        public RouteBuilder withRoute(String path, String result) {

            if (path.contains("*")) {
                wildCardRoutes.add(new WildCardRouter(path, result));
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


        Router router = new RouteBuilder().
                withRoute("/bar", "profileResult").
                withRoute("/get/*/data", "cityData").build();


        System.out.println(router.callResult("/bar"));
        System.out.println(router.callResult("/get/12354/data"));
        System.out.println(router.callResult("/get/12354/abc/data"));
    }
}
