package dynatrace.task.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BidAskRate extends Rates{
    private double bid;
    private double ask;
}
