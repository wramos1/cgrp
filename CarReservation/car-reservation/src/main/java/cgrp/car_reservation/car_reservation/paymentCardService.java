package cgrp.car_reservation.car_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class paymentCardService {


    @Autowired
    private paymentCardRepository cardRepository;

    public paymentCard createNewPaymentCard(paymentCard card)
    {
        cardRepository.save(card);

        return card;
    }




}
