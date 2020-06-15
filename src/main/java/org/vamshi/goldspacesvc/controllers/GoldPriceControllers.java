package org.vamshi.goldspacesvc.controllers;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.*;
import org.vamshi.goldspacesvc.respositories.GoldPriceRepository;
import org.vamshi.goldspacesvc.service.GoldPriceService;
import org.vamshi.goldspacesvc.service.MMTCPAMPService;

import java.math.BigInteger;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/goldPrices")
public class GoldPriceControllers {

    @Autowired
    GoldPriceService goldPriceService;

    @Autowired
    MMTCPAMPService mmtcpampService;

    @Autowired
    GoldPriceRepository goldPriceRepository;

    ApplicationContext applicationContext;

    @PostMapping
    @RequestMapping("/mmtc-pamp")
    public void resgiter() {
        goldPriceService.registerGoldPrice(mmtcpampService.getPrice());
    }

    @GetMapping
    @RequestMapping("min-price")
    public BigInteger getMinimumPrice(@RequestParam("fromDate") String fromdate) {
        return goldPriceRepository.getMinimumPrice(LocalDate.parse(fromdate));
    }

}
