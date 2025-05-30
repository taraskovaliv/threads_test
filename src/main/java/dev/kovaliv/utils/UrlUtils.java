package dev.kovaliv.utils;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import java.io.IOException;

public class UrlUtils {
    public static final String BASE_URL = "https://kovaliv.dev";

    public static void getRequest(String url) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().disableAutomaticRetries().build()) {
            httpClient.execute(ClassicRequestBuilder.get(url).build(), HttpResponse::getCode);
        } catch (IOException e) {
            System.out.println("Error while sending request to " +  url + ": " + e.getMessage());
        }
    }
}
