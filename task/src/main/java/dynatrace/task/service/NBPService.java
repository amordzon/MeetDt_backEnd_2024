package dynatrace.task.service;

import java.time.LocalDate;

public interface NBPService{
    double getAverageExchangeRate(String code, LocalDate date);
}
