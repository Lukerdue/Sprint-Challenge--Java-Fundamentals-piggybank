package com.lambdaschool.piggybank.controllers;

import com.lambdaschool.piggybank.models.Coin;
import com.lambdaschool.piggybank.repostories.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CoinController
{
    @Autowired
    CoinRepository coinrepo;

    @GetMapping(value="/total",  produces={"application/json"})
    public ResponseEntity<?> getBankTotal()
    {
        List<Coin> piggyBank = new ArrayList<>();
        coinrepo.findAll().iterator().forEachRemaining(piggyBank::add);

        double total = 0;
        for(int i = 0; i < piggyBank.size(); i++)
        {
            System.out.println(piggyBank.get(i));
            total = total + (piggyBank.get(i).getQuantity()*piggyBank.get(i).getValue());
        }
        String message;
        message = "The Piggy bank holds " + total;
        System.out.println(message);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
