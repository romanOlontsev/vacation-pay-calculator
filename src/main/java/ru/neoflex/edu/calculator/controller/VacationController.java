package ru.neoflex.edu.calculator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.edu.calculator.domain.response.VacationPayResponse;
import ru.neoflex.edu.calculator.service.VacationCalculatorService;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class VacationController implements Vacation {
    private final VacationCalculatorService vacationCalculatorService;

    @Override
    public ResponseEntity<VacationPayResponse> calculate(BigDecimal averageSalary,
                                                         Long vacationDaysCount,
                                                         LocalDate vacationStart) {
        VacationPayResponse response = vacationCalculatorService.calculate(
                averageSalary,
                vacationDaysCount,
                vacationStart);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
