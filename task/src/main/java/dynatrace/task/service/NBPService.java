package dynatrace.task.service;

import dynatrace.task.dto.MinMaxRate;
import dynatrace.task.dto.AverageRate;

import java.time.LocalDate;

public interface NBPService{
    AverageRate getAverageExchangeRate(String code, LocalDate date);

    MinMaxRate getMinMaxExchangeRate(String code, int n);

    AverageRate getBiggestDifferenceRate(String code, int n);
}
