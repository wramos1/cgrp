package cgrp.car_reservation.car_reservation.payment_card;

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
        System.out.println("Hello card");
        return cardService.createCardFromDTO(cardDTO);
    }

    @GetMapping("/gettestcard")
    public paymentCard getTestCard()
    {
        paymentCardDTO tempcard = new paymentCardDTO();

        tempcard.setNameOnCard("Arthur");
        tempcard.setCardNumber(15422485);
        tempcard.setCvv(454);
        tempcard.setMonth(12);
        tempcard.setDay(11);
        tempcard.setYear(2024);

        return cardService.createCardFromDTO(tempcard);

    }


    // this works fine
    @GetMapping
    public String welcomePage()
    {
        return "Welcome to the card page!";
    }

}