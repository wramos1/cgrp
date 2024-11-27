package cgrp.car_reservation.car_reservation.transaction;


import cgrp.car_reservation.car_reservation.payment_card.paymentCardService;
import cgrp.car_reservation.car_reservation.reservation.Reservation;
import cgrp.car_reservation.car_reservation.payment_card.paymentCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Class Name: TransactionService <br>
 * Date of Code: November 14, 2024 <br>
 * Programmer's Name: Arthur <br>
 *
 * Description: Provides the logic necessary for all needed functionality of the transaction class. <br>
 *
 * Important Functions: <br>
 *  -createNewRentalTransaction: inputted reservation and payment card, creates new vehicle rental transaction in database <br>
 *  -createNewTransaction: inputted reservation and transaction type, creates a new transaction in database based on transaction type <br>
 *
 *  Data Structures: N/A <br>
 *
 *  Algorithms: N/A <br>
 *
 */
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private paymentCardService paymentCardService;

    /**
     * Creates a new rental transaction and properly saves the transaction in the database.<br>
     *
     * @param transactionReservation Reservation that is a part of the transaction<br>
     * @param cardOnTransaction PaymentCard used as a part of the transaction<br>
     */
    public void createNewRentalTransaction(Reservation transactionReservation,paymentCard cardOnTransaction)
    {
        paymentCard paymentCard = paymentCardService.createNewPaymentCard(cardOnTransaction); // this will now be the payment card with the object id so that @DocumentReference documentation can be used with it

        NewReservationTransaction newRentalTransaction = new NewReservationTransaction("New Vehicle Rental", LocalDateTime.now(), transactionReservation, paymentCard); // creates a new rental transaction

        transactionRepository.save(newRentalTransaction);

    }


    // this is for transaction such as modify or cancel that does not involve a payment card

    /**
     * Creates a new transaction based on the type of the transaction and saves the transaction in the database.<br>
     *
     * @param transactionReservation Resevation that is a part of the transaction.<br>
     * @param transactionType Type of transaction (Modify, Cancel, Other)<br>
     * @return the transaction that is saved<br>
     */
    public Transaction createNewTransaction(Reservation transactionReservation, String transactionType)
    {
        Transaction newTransaction = new Transaction(null, transactionReservation);

        if(transactionType.equals("modify"))
            newTransaction.setTransactionNote("Modified Existing Vehicle Rental");
        else if (transactionType.equals("cancel"))
            newTransaction.setTransactionNote("Canceled Vehicle Rental");
        else if (transactionType.equals("checkin"))
        {
            newTransaction.setTransactionNote("Checked Vehicle Back In");
            newTransaction.setReservationInvolvedInTransaction(null);
        }
        else {
            newTransaction.setTransactionNote("Miscellaneous Transaction");
        }

        return transactionRepository.save(newTransaction);

    }



}
