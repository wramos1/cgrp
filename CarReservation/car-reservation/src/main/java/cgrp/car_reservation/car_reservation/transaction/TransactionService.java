package cgrp.car_reservation.car_reservation.transaction;


import cgrp.car_reservation.car_reservation.reservation.Reservation;
import cgrp.car_reservation.car_reservation.payment_card.paymentCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;


    public void createNewRentalTransaction(Reservation transactionReservation,paymentCard cardOnTransaction)
    {
        NewReservationTransaction newRentalTransaction = new NewReservationTransaction("New Vehicle Rental", LocalDateTime.now(), transactionReservation, cardOnTransaction); // creates a new rental transaction

        transactionRepository.save(newRentalTransaction);

    }


    // this is for transaction such as modify or cancel that does not involve a payment card
    public Transaction createNewTransaction(Reservation transactionReservation, String transactionType)
    {
        Transaction newTransaction = new Transaction(null, transactionReservation);

        if(transactionType.equals("modify"))
            newTransaction.setTransactionNote("Modified Existing Vehicle Rental");
        else if (transactionType.equals("cancel"))
            newTransaction.setTransactionNote("Canceled Vehicle Rental");
        else {
            newTransaction.setTransactionNote("Miscellaneous Transaction");
        }

        return transactionRepository.save(newTransaction);

    }



}
