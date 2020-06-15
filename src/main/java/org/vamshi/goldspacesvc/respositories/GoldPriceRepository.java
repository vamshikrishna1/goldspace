package org.vamshi.goldspacesvc.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.vamshi.goldspacesvc.models.GoldPrice;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public interface GoldPriceRepository extends JpaRepository<GoldPrice, Long> {


    @Query("Select MIN(price.price) from GoldPrice price where price.date>=:fromDate")
    BigInteger getMinimumPrice(@Param("fromDate") LocalDate fromdate);

    @Query("Select MAX(price.price) from GoldPrice price where price.date>=:fromDate")
    BigInteger getMAXPrice(@Param("fromDate") LocalDate fromdate);


}
