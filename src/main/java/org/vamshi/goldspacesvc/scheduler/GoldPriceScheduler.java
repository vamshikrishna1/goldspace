package org.vamshi.goldspacesvc.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.vamshi.goldspacesvc.models.GoldPrice;
import org.vamshi.goldspacesvc.service.GoldPriceService;
import org.vamshi.goldspacesvc.service.MMTCPAMPService;

@Component
public class GoldPriceScheduler {

    @Autowired
    GoldPriceService goldPriceService;

    @Autowired
    MMTCPAMPService mmtcpampService;

    @Scheduled(cron = "0 0 * * * *")
    public void getGoldPriceFromMMTC() {
        GoldPrice goldPrice = mmtcpampService.getPrice();
        goldPriceService.verifyForExtremitiesAndSendMessage(goldPrice.getPrice());
        goldPriceService.registerGoldPrice(goldPrice);
    }

}
