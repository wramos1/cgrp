package cgrp.car_reservation.car_reservation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cards")
public class paymentCardController {

   @PostMapping("/newcard")
    public paymentCard createCard(@RequestBody paymentCard card)
   {

       return card;
   }
}
