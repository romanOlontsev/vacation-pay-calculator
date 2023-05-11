package ru.neoflex.edu.calculator.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import ru.neoflex.edu.calculator.domain.response.VacationPayResponse;
import ru.neoflex.edu.calculator.exception.BadRequestException;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VacationCalculatorServiceTest {
    @Autowired
    private VacationCalculatorService service;

    @Test
    void calculate_vacationStartIsNull_shouldReturnExpectedResponse() {
        VacationPayResponse expectedResponse = VacationPayResponse.builder()
                                                                  .vacationPay(BigDecimal.valueOf(12984.73))
                                                                  .build();

        VacationPayResponse response = service.calculate(
                BigDecimal.valueOf(456543L),
                10L,
                null);

        assertAll(
                () -> assertThat(response).isNotNull()
                                          .isEqualTo(expectedResponse)
        );
    }

    @Test
    void calculate_vacationStartIsNotNull_shouldReturnExpectedResponse() {
        VacationPayResponse expectedResponse = VacationPayResponse.builder()
                                                                  .vacationPay(BigDecimal.valueOf(2596.95))
                                                                  .build();

        VacationPayResponse response = service.calculate(
                BigDecimal.valueOf(456543L),
                10L,
                LocalDate.of(2023, 1, 1));

        assertAll(
                () -> assertThat(response).isNotNull()
                                          .isEqualTo(expectedResponse)
        );
    }

    @Test
    void calculate_averageSalaryIsNull_shouldThrowBadRequestException() {
        assertAll(
                () -> assertThatThrownBy(() -> service.calculate(
                        null, 10L, null))
                        .isInstanceOf(BadRequestException.class)
                        .hasMessage("Input data missing")
        );
    }

    @Test
    void calculate_vacationDaysCountIsNull_shouldThrowBadRequestException() {
        assertAll(
                () -> assertThatThrownBy(() -> service.calculate(
                        BigDecimal.valueOf(541215), null, null))
                        .isInstanceOf(BadRequestException.class)
                        .hasMessage("Input data missing")
        );
    }
}