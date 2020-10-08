package com.example.demo.payment;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "payment")
class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "account_holder")
    private String accountHolder;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "amount")
    private double amount;

}
