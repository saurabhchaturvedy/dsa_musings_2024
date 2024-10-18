package Atlassian.PostKarat18Oct.Router.Basic;

import java.util.HashMap;
import java.util.Map;

public class Router {


    Map<String, String> map;


    Router() {

        this.map = new HashMap<>();
    }


    public void withRoute(String path, String result) {

        this.map.put(path, result);
    }


    public String callRoute(String path) {

        if (map.containsKey(path)) {
            return this.map.get(path);
        }
        return "404 : Not Found";
    }


    public static void main(String[] args) {


        Router router = new Router();


        router.withRoute("/foo", "profilePage");
        router.withRoute("/bar", "loginPage");

        System.out.println(router.callRoute("/foo"));
        System.out.println(router.callRoute("/bar"));
        System.out.println(router.callRoute("/baz"));
    }
}
