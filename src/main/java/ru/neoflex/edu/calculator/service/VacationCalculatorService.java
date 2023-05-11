package ru.neoflex.edu.calculator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.neoflex.edu.calculator.domain.response.HolidayResponse;
import ru.neoflex.edu.calculator.domain.response.VacationPayResponse;
import ru.neoflex.edu.calculator.exception.BadRequestException;
import ru.neoflex.edu.calculator.service.client.HolidayClient;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VacationCalculatorService {
    private final static double AVERAGE_WORK_DAYS_PER_YEAR = 351.6;
    private final HolidayClient holidayClient;

    public VacationPayResponse calculate(BigDecimal averageSalary,
                                         Long vacationDaysCount,
                                         LocalDate vacationStart) {
        inputDataCheck(averageSalary, vacationDaysCount);
        BigDecimal vacationPay = getVacationPay(averageSalary, vacationDaysCount, vacationStart);
        return VacationPayResponse.builder()
                                  .vacationPay(vacationPay)
                                  .build();
    }

    private BigDecimal getVacationPay(BigDecimal averageSalary, Long vacationDaysCount, LocalDate vacationStart) {
        BigDecimal averageDailyIncome = averageSalary.divide(
                BigDecimal.valueOf(AVERAGE_WORK_DAYS_PER_YEAR), 5, RoundingMode.HALF_DOWN);
        if (vacationStart != null) {
            List<HolidayResponse> holidaysList = holidayClient.getHolidays(
                                                                      vacationStart,
                                                                      vacationStart.plusDays(vacationDaysCount))
                                                              .blockOptional()
                                                              .orElse(List.of());
            vacationDaysCount -= holidaysList.size();
        }
        return averageDailyIncome.multiply(BigDecimal.valueOf(vacationDaysCount))
                                 .setScale(2, RoundingMode.HALF_DOWN);
    }

    private void inputDataCheck(BigDecimal averageSalary, Long vacationDaysCount) {
        if (averageSalary == null || vacationDaysCount == null) {
            throw new BadRequestException("Input data missing");
        }
    }
}
