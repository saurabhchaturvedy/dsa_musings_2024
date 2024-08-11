package DesignPatterns.Structural.Proxy;

public class RealWebServer implements WebServer {
    @Override
    public void makeRequest(String url) {
        System.out.println(" Request processed for the URL : " + url);
    }
}