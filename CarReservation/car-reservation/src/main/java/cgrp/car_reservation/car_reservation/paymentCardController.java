package cgrp.car_reservation.car_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// will be handleing all of the REST api requests
@RestController
@RequestMapping("/card")
public class paymentCardController {

    @Autowired
    private paymentCardService cardService;

    @PostMapping("/newcard")
    public paymentCard createCard(@RequestBody paymentCard card)
    {
        cardService.createNewPaymentCard(card);

        return card;
    }

    @PostMapping("/testcard")
    public paymentCard createNewCard(@RequestBody paymentCardDTO cardDTO)
    {
        return cardService.createCardFromDTO(cardDTO);
    }


    // this works fine
    @GetMapping
    public String welcomePage()
    {
        return "Welcome to the card page!";
    }

}