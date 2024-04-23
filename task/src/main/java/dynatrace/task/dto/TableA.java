package dynatrace.task.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TableA extends Table{
    private List<AverageRate> rates;
}
