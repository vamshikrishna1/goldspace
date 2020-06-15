package org.vamshi.goldspacesvc.models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name = "gold_price")
@Data
public class GoldPrice{

    @Id
    @Column(name = "price_id")
    @GeneratedValue(generator = "gold_price_id")
    @SequenceGenerator(name = "gold_price_id", sequenceName = "gold_price_price_id_seq", allocationSize = 1)
    private Long priceId;

    @Basic
    @Column(name = "price")
    private BigInteger price;

    @Basic
    @Column(name = "date")
    private LocalDate date;

}
