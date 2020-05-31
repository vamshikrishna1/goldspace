package org.vamshi.goldspacesvc.service;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.vamshi.goldspacesvc.respositories.GoldPriceRepository;
import org.vamshi.goldspacesvc.models.GoldPrice;

import java.util.List;

@CommonsLog
@Service
public class GoldPriceService {

    @Autowired
    GoldPriceRepository goldPriceRepository;

    public void registerGoldPrice(GoldPrice goldPriceEntity) {

        List<GoldPrice> prices = goldPriceRepository.findAll(Example.of(goldPriceEntity));
        if (prices.isEmpty()) {
            log.info("RESGISTERING THE ENTITY " + goldPriceEntity);
            goldPriceRepository.saveAndFlush(goldPriceEntity);
            log.info("ENTITY REGISTERED SUCCESSFULLY " + goldPriceEntity);
        } else {
            log.info("FOUND SIMILAR ENTITIES:");
            prices.forEach(e -> log.info(e.toString()));

        }


    }


}
