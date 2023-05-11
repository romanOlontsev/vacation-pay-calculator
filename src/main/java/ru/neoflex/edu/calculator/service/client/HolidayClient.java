package ru.neoflex.edu.calculator.service.client;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import ru.neoflex.edu.calculator.domain.response.HolidayResponse;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HolidayClient {
    private final WebClient webClient;

    public Mono<List<HolidayResponse>> getHolidays(LocalDate fromDate, LocalDate toDate) {
        return webClient.get()
                        .uri(uriBuilder -> uriBuilder.path("/getHolidaysForDateRange/")
                                                     .queryParam("fromDate", fromDate)
                                                     .queryParam("toDate", toDate)
                                                     .queryParam("country", "RU")
                                                     .queryParam("holidayType", "public_holiday")
                                                     .build())
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<List<HolidayResponse>>() {
                        })
                        .retryWhen(Retry.fixedDelay(3, Duration.ofMillis(100)));
    }
}
