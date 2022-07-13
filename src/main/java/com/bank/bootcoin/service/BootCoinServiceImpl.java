package com.bank.bootcoin.service;


import com.bank.bootcoin.entity.BootCoin;
import com.bank.bootcoin.reoisitory.BootCoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BootCoinServiceImpl implements BootCoinService {
    @Autowired
    BootCoinRepository bootCoinRepository;

    @Override
    public Flux<BootCoin> getBootCoinByDocumentNumber(String documentNumber) {
        return bootCoinRepository.findAll()
                .filter(bootCoin -> bootCoin.getDocumentNumber().equals(documentNumber));
    }

    @Override
    public Mono<BootCoin> saveBootCoin(BootCoin bootCoin) {
        return  bootCoinRepository.save(bootCoin);
    }

    @Override
    public Mono<BootCoin> update(BootCoin bootCoin) {
        return bootCoinRepository.findById(bootCoin.getId())
                .flatMap(newBootCoin->{
                    newBootCoin.setBootcoins(bootCoin.getBootcoins());
                    newBootCoin.setDocumentNumber(bootCoin.getDocumentNumber());
                    newBootCoin.setEmail(bootCoin.getEmail());
                    newBootCoin.setPhoneNumber(bootCoin.getPhoneNumber());
                    return bootCoinRepository.save(newBootCoin);
                });
    }
}
