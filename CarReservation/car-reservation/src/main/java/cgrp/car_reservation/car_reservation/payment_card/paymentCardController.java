package cgrp.car_reservation.car_reservation.payment_card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * Module Name: paymentCardController.java<br>*
 * Date of code: 11/14/2024<br>*
 * Programmers Name: Arthur<br>*
 * Description: This class handles REST API requests for operations related to payment cards.<br>*
 * Functions:<br>
 * - createCard(paymentCard card): Creates a new payment card using the provided card information.<br>*
 * - createNewCard(paymentCardDTO cardDTO): Creates a new payment card using a DTO object.<br>*
 * - getTestCard(): Creates and returns a sample test payment card using hardcoded values.<br>*
 * - welcomePage(): Returns a welcome message for the card page.<br>*
 * Data structures: N/A<br>*
 */
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
        tempcard.setCardNumber("15422485");
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