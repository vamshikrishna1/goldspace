package org.vamshi.goldspacesvc.service;

import lombok.extern.apachecommons.CommonsLog;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.vamshi.goldspacesvc.models.GoldPrice;

import java.math.BigInteger;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CommonsLog
@Service
public class MMTCPAMPService {

    @Value("${mmtc.pamp.url}")
    private String mmtcpampurl;

    public GoldPrice getPrice() {
        GoldPrice goldPrice = new GoldPrice();

        try {
            URL url = new URL(mmtcpampurl);

            try (PDDocument doc = PDDocument.load(url.openStream())) {
                PDFTextStripper stripper = new PDFTextStripper();
                String text = stripper.getText(doc);
                Pattern pricePattern = Pattern.compile("24K 999.9 Fine Gold 1gm [0-9](.?)([0-9]{0,10})");
                Pattern datePattern = Pattern.compile("\\d{1,2} [A-za-z]{3,9} \\d{4}");
                Pattern extractPricePattern = Pattern.compile("\\d{1}.(\\d{2,3})");
                Matcher dateMatcher = datePattern.matcher(text);
                Matcher priceMatcher = pricePattern.matcher(text);
                String priceString = null;
                String dateString = null;

                while (dateMatcher.find()) {

                    dateString = dateMatcher.group();
                }


                java.util.Date date = new SimpleDateFormat("dd MMM yyyy").parse(dateString);
                LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                goldPrice.setDate(localDate);

                while (priceMatcher.find()) {

                    priceString = priceMatcher.group();

                }

                Matcher extractPriceMatcher = extractPricePattern.matcher(priceString);

                while (extractPriceMatcher.find()) {
                    String temp = extractPriceMatcher.group().replace(",", "");
                    goldPrice.setPrice(new BigInteger(temp));
                }

            }

            log.info("Entity Pulled From MMTC-PAMP " + goldPrice);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return goldPrice;
    }


}
