package com.bank.bootcoin.service;

import com.bank.bootcoin.entity.BootCoin;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BootCoinService {
    Flux<BootCoin> getBootCoinByDocumentNumber(String documentNumber);
    Mono<BootCoin> saveBootCoin(BootCoin bootCoin);
    Mono<BootCoin> update(BootCoin bootCoin);
}
