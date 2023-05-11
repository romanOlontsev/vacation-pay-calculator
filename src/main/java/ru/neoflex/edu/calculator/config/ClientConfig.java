package ru.neoflex.edu.calculator.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class ClientConfig {
    @Value("${client.response-timeout}")
    public int timeout;
    @Value("${holiday.api.base-url}")
    private String holidayApiBaseUrl;

    @Bean
    public WebClient holidayApiClientWithTimeout() {
        final HttpClient httpClient = HttpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeout)
                .responseTimeout(Duration.ofMillis(timeout))
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(timeout, TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(timeout, TimeUnit.MILLISECONDS));
                });
        return WebClient.builder()
                        .baseUrl(holidayApiBaseUrl)
                        .defaultHeader(HttpHeaders.ACCEPT, "application/json")
                        .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                        .clientConnector(new ReactorClientHttpConnector(httpClient))
                        .build();
    }
}
