package Atlassian.PostKarat.Router;

import java.util.HashMap;
import java.util.Map;

public class Router {


    Map<String, String> map;


    Router() {

        this.map = new HashMap<>();
    }


    public void withRoute(String path, String result) {

        map.put(path, result);
    }


    public String callRoute(String path) {

        if (map.containsKey(path)) {

            return map.get(path);
        }

        return "404 : Route Not Found";
    }


    public static void main(String[] args) {


        Router router = new Router();


        router.withRoute("/foo", "xyz");
        router.withRoute("/bar", "def");


        System.out.println(router.callRoute("/foo"));
        System.out.println(router.callRoute("/baz"));
    }
}
