package dynatrace.task.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BidAskRateResponse extends BidAskRate{
    private double difference;
}
