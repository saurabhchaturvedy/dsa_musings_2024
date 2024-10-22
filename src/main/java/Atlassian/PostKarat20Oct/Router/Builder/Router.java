package Atlassian.PostKarat20Oct.Router.Builder;

import java.util.HashMap;
import java.util.Map;

public class Router {


    String path;
    String result;
    Map<String, String> map = new HashMap<>();


    Router(RouteBuilder routeBuilder) {

        this.map = routeBuilder.map;
    }


    public String callRoute(String path) {

        if (this.map.containsKey(path)) {
            return map.get(path);
        }

        return "404 : Not Found";
    }


    private static class RouteBuilder {

        String path;
        String result;
        Map<String, String> map = new HashMap<>();


        public RouteBuilder withRoute(String path, String result) {

            map.put(path, result);

            return this;
        }


        public Router build() {
            return new Router(this);
        }
    }

    public static void main(String[] args) {


        Router router = new Router.RouteBuilder().withRoute("/foo", "loginPage").withRoute("/bar", "cartPage").build();

        System.out.println(router.callRoute("/foo"));
        System.out.println(router.callRoute("/baz"));
    }
}
