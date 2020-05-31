package org.vamshi.goldspacesvc.models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.OffsetDateTime;

@Entity
@Table(name = "gold_transaction")
@Data
public class GoldTransaction {

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(generator = "gold_transaction_id")
    @SequenceGenerator(name = "gold_transaction_id", sequenceName = "gold_transaction_transaction_id_seq", allocationSize = 1)
    private Long transactionId;

    @Basic
    @Column(name = "transaction_type")
    private String transactionType;

    @Basic
    @Column(name = "gold_in_milligrams")
    private BigInteger goldInMilligrams;

    @Basic
    @Column(name = "amount")
    private BigInteger amount;

    @Basic
    @Column(name = "unit_price")
    private BigInteger unitPrice;

    @Basic
    @Column(name = "transaction_date")
    private OffsetDateTime transactionDate;

}
