package com.bank.bootcoin.controller;

import com.bank.bootcoin.entity.BootCoin;
import com.bank.bootcoin.service.BootCoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bootcoin")
public class BootCoinController {
    @Autowired
    BootCoinService bootCoinService;

    /**
     * Método encargado de traer todos los bootcoin que tiene un cliente por su dni
     * @param documentNumber
     * @return
     */
    @GetMapping("/{documentNumber}")
    public Flux<BootCoin> getBootCoinByDocumentNumber(@PathVariable String documentNumber){
        return  bootCoinService.getBootCoinByDocumentNumber(documentNumber);
    }

    /**
     * Método que se encarga de guardar a los nuevos clientes que adquieren bootcoin
     * @param bootCoin
     * @return
     */
    @PostMapping
    public Mono<BootCoin> saveBootCoin(@RequestBody BootCoin bootCoin) {
        return  bootCoinService.saveBootCoin(bootCoin);
    }

    /**
     * Método encargado de actualizar los cambios en las cantidades de los bootcoin
     * @param bootCoin
     * @return
     */
    @PutMapping
    public Mono<BootCoin> updateBootCoin(@RequestBody BootCoin bootCoin) {
        return bootCoinService.update(bootCoin);
    }


}
