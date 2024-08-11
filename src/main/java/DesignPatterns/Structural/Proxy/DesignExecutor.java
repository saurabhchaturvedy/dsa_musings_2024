package DesignPatterns.Structural.Proxy;

public class DesignExecutor {


    public static void main(String[] args) {

        String url = "https://www.google.com";

        ProxyWebServer proxyWebServer = new ProxyWebServer();
        proxyWebServer.blockWebsite(url);

        proxyWebServer.makeRequest(url);

        proxyWebServer.makeRequest("https://www.agoda.com");
    }
}