package dynatrace.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MinMaxRate {
    private Rates minRate;
    private Rates maxRate;
}
