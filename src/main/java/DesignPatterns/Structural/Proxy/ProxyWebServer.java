package DesignPatterns.Structural.Proxy;

import java.util.ArrayList;
import java.util.List;

public class ProxyWebServer implements WebServer {

    RealWebServer realWebServer;
    List<String> blockedWebsites = new ArrayList<>();

    ProxyWebServer() {
        this.realWebServer = new RealWebServer();
    }

    public void blockWebsite(String url) {
        this.blockedWebsites.add(url);
    }

    @Override
    public void makeRequest(String url) {

        if (!blockedWebsites.contains(url)) {
            realWebServer.makeRequest(url);
        } else {
            System.out.println("REQUEST_PROCESSING_ERROR : " + url + " is blocked in your org !! Please contact your IT administrator");
        }
    }
}