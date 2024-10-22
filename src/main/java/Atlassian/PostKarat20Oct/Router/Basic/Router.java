package Atlassian.PostKarat20Oct.Router.Basic;

import java.util.HashMap;
import java.util.Map;

public class Router {


    String path;
    String result;

    Map<String, String> map = new HashMap<>();


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


        return "404 : Not Found";
    }


    public static void main(String[] args) {


        Router router = new Router();

        router.withRoute("/baz", "homePage");
        router.withRoute("/foo", "loginPage");
        router.withRoute("/bar", "cartPage");

        System.out.println(router.callRoute("/baz"));
        System.out.println(router.callRoute("/foo"));
        System.out.println(router.callRoute("/bat"));
    }
}
