package dynatrace.task.controller;


import dynatrace.task.dto.BidAskRate;
import dynatrace.task.dto.BidAskRateResponse;
import dynatrace.task.dto.MinMaxRate;
import dynatrace.task.dto.AverageRate;
import dynatrace.task.service.NBPService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/nbp")
public class NBPController {
    private final NBPService nbpService;

    public NBPController(NBPService nbpService){
        this.nbpService = nbpService;
    }

    @GetMapping("/exchange-rate")
    public AverageRate getAverageExchangeRate(@RequestParam String code, @RequestParam LocalDate date) {
        return nbpService.getAverageExchangeRate(code, date);
    }

    @GetMapping("/min-max")
    public MinMaxRate getMinMaxExchangeRate(@RequestParam String code, @RequestParam int N) {
        return nbpService.getMinMaxExchangeRate(code, N);
    }

    @GetMapping("/biggest-difference")
    public BidAskRateResponse getBiggestDifferenceRate(@RequestParam String code, @RequestParam int N) {
        return nbpService.getBiggestDifferenceRate(code, N);
    }
}
