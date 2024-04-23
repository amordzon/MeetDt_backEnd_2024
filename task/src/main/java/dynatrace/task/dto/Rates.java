package dynatrace.task.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Rates {
    private String no;
    private LocalDate effectiveDate;
}
