package com.bank.bootcoin.events;

import com.bank.bootcoin.entity.BootCoin;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BootCoinCreatedEvent extends Event<BootCoin> {

}
