package com.bank.bootcoin.reoisitory;

import com.bank.bootcoin.entity.BootCoin;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BootCoinRepository extends ReactiveMongoRepository <BootCoin, Integer> {
}
