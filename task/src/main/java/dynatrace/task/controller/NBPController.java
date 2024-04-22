package dynatrace.task.controller;


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
    public double getAverageExchangeRate(@RequestParam String code, @RequestParam LocalDate date) {
        return nbpService.getAverageExchangeRate(code, date);
    }
}
