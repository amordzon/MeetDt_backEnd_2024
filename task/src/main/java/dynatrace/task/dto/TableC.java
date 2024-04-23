package dynatrace.task.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TableC extends Table {
    private List<BidAskRate> rates;
}
