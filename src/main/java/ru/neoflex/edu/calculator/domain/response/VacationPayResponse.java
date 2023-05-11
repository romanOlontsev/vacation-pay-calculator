package ru.neoflex.edu.calculator.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class VacationPayResponse {
    @JsonProperty(value = "vacation_pay")
    private BigDecimal vacationPay;
}
