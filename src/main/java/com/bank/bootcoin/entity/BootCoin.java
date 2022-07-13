package com.bank.bootcoin.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "bootcoins")
public class BootCoin {
    @Id
    private int id;
    private String documentNumber;
    private String phoneNumber;
    private String email;
    private Integer bootcoins;


}
