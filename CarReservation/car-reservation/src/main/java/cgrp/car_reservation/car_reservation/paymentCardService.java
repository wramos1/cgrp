package cgrp.car_reservation.car_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

// will have all of the neccesary logic needed on the object if needed
@Service
public class paymentCardService {


    @Autowired
    private paymentCardRepository cardRepository;

    public paymentCard createNewPaymentCard(paymentCard card)
    {
        cardRepository.save(card);

        return card;
    }

    public paymentCard createCardFromDTO(paymentCardDTO cardDTO)
    {
        LocalDate expirationDate = LocalDate.of(cardDTO.getYear(), cardDTO.getMonth(), cardDTO.getDay());
        paymentCard newCard = new paymentCard(cardDTO.getNameOnCard(), cardDTO.getCvv(), expirationDate, cardDTO.getCardNumber());

        cardRepository.save(newCard); // saves the card created for the user in the database

        return newCard;
    }



}