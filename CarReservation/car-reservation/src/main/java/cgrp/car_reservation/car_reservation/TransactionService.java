package cgrp.car_reservation.car_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    // this will help create a transaction in the db and therefore "log" a transaction
    public void logTransaction(Reservation reservation)
    {
        Transaction currentTransaction = new Transaction(LocalDateTime.now(), reservation, reservation.getChargeAmount()); // creates a transaction with the two current attributes/member variables that we have for it

        transactionRepository.save(currentTransaction); // saves the transacton to the database

    }


}
