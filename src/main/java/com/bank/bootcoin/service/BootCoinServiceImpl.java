package com.bank.bootcoin.service;


import com.bank.bootcoin.entity.BootCoin;
import com.bank.bootcoin.reoisitory.BootCoinRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class BootCoinServiceImpl implements BootCoinService {
    @Autowired
    BootCoinRepository bootCoinRepository;

    @Autowired
    BootCoinEventService bootCoinEventService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public Flux<BootCoin> getBootCoinByDocumentNumber(String documentNumber) {
        String key = "BootCoin_" + documentNumber;
        ValueOperations<String, BootCoin> operations = redisTemplate.opsForValue();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            BootCoin bootCoin = operations.get(key);
            log.info("getByID :: customer encontrado con id: " + documentNumber);
            return Flux.just(bootCoin);
        }
        return bootCoinRepository.findAll()
                .filter(bootCoin -> bootCoin.getDocumentNumber().equals(documentNumber))
                ;
    }

    @Override
    public Mono<BootCoin> saveBootCoin(BootCoin bootCoin) {
        kafkaSave(bootCoin);
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

    private void kafkaSave(BootCoin bootCoin) {
        System.out.println("Received " + bootCoin);
        bootCoinEventService.publish(bootCoin);
    }
}
