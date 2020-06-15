package org.vamshi.goldspacesvc.service;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.vamshi.goldspacesvc.models.GoldPrice;
import org.vamshi.goldspacesvc.models.SlackMessage;
import org.vamshi.goldspacesvc.respositories.GoldPriceRepository;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public BigInteger getMinimumPriceFromDate(LocalDate fromDate) {
        return goldPriceRepository.getMinimumPrice(fromDate);
    }


    public BigInteger getMaxPriceFromDate(LocalDate fromDate) {
        return goldPriceRepository.getMAXPrice(fromDate);
    }


    public void verifyForExtremitiesAndSendMessage(BigInteger currentPrice) {
        LocalDate fromPastThreeMonths = LocalDate.now().minusMonths(3);
        BigInteger maxPrice = getMaxPriceFromDate(fromPastThreeMonths);
        BigInteger minPrice = getMinimumPriceFromDate(fromPastThreeMonths);
        String message = "%s Gold Price Recorded in the Last 3 Months: %f";
        Optional<String> slackMessage = null;
        if (currentPrice.compareTo(maxPrice) > 0) {
            slackMessage = Optional.of(String.format(message, "Highest", currentPrice));

        } else if (currentPrice.compareTo(minPrice) < 0) {
            slackMessage = Optional.of(String.format(message, "Lowest", currentPrice));
        }

        slackMessage.ifPresent((text) -> {
            new SlackMessage(text).sendMessage();
        });


    }


}
