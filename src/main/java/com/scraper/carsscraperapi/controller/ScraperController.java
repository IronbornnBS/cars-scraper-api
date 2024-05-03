package com.scraper.carsscraperapi.controller;

import com.scraper.carsscraperapi.model.ResponseDTO;
import com.scraper.carsscraperapi.service.ScraperService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class ScraperController {

   private final ScraperService scraperService;

    public ScraperController(ScraperService scraperService) {
        this.scraperService = scraperService;
    }

    @GetMapping(path = "/{vehicleModel}")
    public Mono<List<ResponseDTO>> getIkVehicle(@PathVariable String vehicleModel) {
        return  scraperService.extractDataFromIk(vehicleModel);
    }
}
