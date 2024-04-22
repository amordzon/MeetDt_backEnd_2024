package dynatrace.task.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TableA {
    private String table;
    private String currenncy;
    private String code;
    private List<Rates> rates;
}
