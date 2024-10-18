package Atlassian.PostKarat18Oct.Router.WildCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Router {


    Map<String, String> staticRoutes;
    List<WildCardRouter> wildCardRoutes;


    Router() {
        this.staticRoutes = new HashMap<>();
        this.wildCardRoutes = new ArrayList<>();
    }


    public void withRoute(String path, String result) {

        if (path.contains("*")) {

            wildCardRoutes.add(new WildCardRouter(path, result));
        } else {

            staticRoutes.put(path, result);
        }

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


        public String getResult() {
            return result;
        }
    }


    public static void main(String[] args) {


        Router router = new Router();


        router.withRoute("/bar/*/baz", "shoppingCart");
        router.withRoute("/top/*/result", "shoppingCart-2");
        router.withRoute("/foo", "shoppingCart-3");


        System.out.println(router.callRoute("/bar/kite/baz"));
        System.out.println(router.callRoute("/bar/kite/bap"));
        System.out.println(router.callRoute("/foo"));
        System.out.println(router.callRoute("/koo"));
    }
}
