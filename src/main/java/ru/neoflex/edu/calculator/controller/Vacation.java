package ru.neoflex.edu.calculator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.neoflex.edu.calculator.domain.response.ApiErrorResponse;
import ru.neoflex.edu.calculator.domain.response.VacationPayResponse;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Validated
public interface Vacation {
    @Operation(summary = "Calculate vacation pay")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Vacation pay has been successfully calculated",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = VacationPayResponse.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request parameters",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @GetMapping(value = "/calculate", produces = "application/json")
    ResponseEntity<VacationPayResponse> calculate(
            @Parameter(
                    in = ParameterIn.QUERY,
                    required = true,
                    description = "Average salary for 12 months")
            @Valid
            @Positive
            @RequestParam BigDecimal averageSalary,
            @Parameter(
                    in = ParameterIn.QUERY,
                    required = true,
                    description = "Number of vacation days")
            @Valid
            @Positive
            @Max(365)
            @RequestParam Long vacationDaysCount,
            @Parameter(
                    in = ParameterIn.QUERY,
                    description = "Vacation start date in format yyyy-mm-dd")
            @Valid
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate vacationStart);
}
