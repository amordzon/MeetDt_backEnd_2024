package dynatrace.task.controller;


import dynatrace.task.dto.MinMaxRate;
import dynatrace.task.dto.Rates;
import dynatrace.task.service.NBPService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/nbp")
public class NBPController {
    private final NBPService nbpService;

    public NBPController(NBPService nbpService){
        this.nbpService = nbpService;
    }

    @GetMapping("/exchange-rate")
    public Rates getAverageExchangeRate(@RequestParam String code, @RequestParam LocalDate date) {
        return nbpService.getAverageExchangeRate(code, date);
    }

    @GetMapping("/min-max")
    public MinMaxRate getMinMaxExchangeRate(@RequestParam String code, @RequestParam int N) {
        return nbpService.getMinMaxExchangeRate(code, N);
    }
}
