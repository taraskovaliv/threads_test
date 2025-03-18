package dev.kovaliv.utils;

import lombok.extern.log4j.Log4j2;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import java.io.IOException;

@Log4j2
public class UrlUtils {
    public static final String BASE_URL = "https://kovaliv.dev";

    public static void getRequest(String url) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().disableAutomaticRetries().build()) {
            httpClient.execute(ClassicRequestBuilder.get(url).build(), HttpResponse::getCode);
        } catch (IOException e) {
            log.warn("Error while sending request to {}", url, e);
        }
    }
}
