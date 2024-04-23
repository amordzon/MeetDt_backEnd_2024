package dynatrace.task.controller;


import dynatrace.task.dto.BidAskRate;
import dynatrace.task.dto.BidAskRateResponse;
import dynatrace.task.dto.MinMaxRate;
import dynatrace.task.dto.AverageRate;
import dynatrace.task.service.NBPService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getAverageExchangeRate(@RequestParam String code, @RequestParam LocalDate date){
        try {
            AverageRate avgRate = nbpService.getAverageExchangeRate(code, date);
            return new ResponseEntity<>(avgRate, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("An error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/min-max")
    public ResponseEntity<?> getMinMaxExchangeRate(@RequestParam String code, @RequestParam int N) {
        try {
            MinMaxRate rates = nbpService.getMinMaxExchangeRate(code, N);
            return new ResponseEntity<>(rates, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("An error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/biggest-difference")
    public ResponseEntity<?> getBiggestDifferenceRate(@RequestParam String code, @RequestParam int N) {
        try{
            BidAskRateResponse rateResponse = nbpService.getBiggestDifferenceRate(code, N);
            return new ResponseEntity<>(rateResponse, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("An error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
