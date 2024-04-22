package dynatrace.task.service;

import dynatrace.task.dto.MinMaxRate;
import dynatrace.task.dto.Rates;

import java.time.LocalDate;
import java.util.List;

public interface NBPService{
    Rates getAverageExchangeRate(String code, LocalDate date);

    MinMaxRate getMinMaxExchangeRate(String code, int n);
}
