package dynatrace.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MinMaxRate {
    private AverageRate minRate;
    private AverageRate maxRate;
}
