package org.vamshi.goldspacesvc.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.vamshi.goldspacesvc.models.GoldPrice;
import org.vamshi.goldspacesvc.rest.client.MMTCPAMPClient;
import org.vamshi.goldspacesvc.service.GoldPriceService;

@Component
public class GoldPriceScheduler {

    @Autowired
    GoldPriceService goldPriceService;

    @Scheduled(cron = "0 0 * * * *")
    public void getGoldPriceFromMMTC() {
        GoldPrice goldPrice = MMTCPAMPClient.getPrice();
        goldPriceService.registerGoldPrice(goldPrice);
    }

}
