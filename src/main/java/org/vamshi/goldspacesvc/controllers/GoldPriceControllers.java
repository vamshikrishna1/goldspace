package org.vamshi.goldspacesvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vamshi.goldspacesvc.rest.client.MMTCPAMPClient;
import org.vamshi.goldspacesvc.service.GoldPriceService;

@RestController
@RequestMapping("/goldPrices")
public class GoldPriceControllers {

    @Autowired
    GoldPriceService goldPriceService;

    @PutMapping("/mmtc-pamp")
    public void resgiterEntityFromMMTC() {
        goldPriceService.registerGoldPrice(MMTCPAMPClient.getPrice());
    }

}
