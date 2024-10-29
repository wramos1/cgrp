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

    // creates a card object in the way the model is stored in db from the minimal information that was needed from the user in the front end
    public paymentCard createCardFromDTO(paymentCardDTO cardDTO)
    {
        LocalDate expirationDate = LocalDate.of(cardDTO.getYear(), cardDTO.getMonth(), cardDTO.getDay());
        paymentCard newCard = new paymentCard(cardDTO.getNameOnCard(), cardDTO.getCvv(), expirationDate, cardDTO.getCardNumber());

        cardRepository.save(newCard); // saves the card created for the user in the database

        return newCard;
    }

    // will use Luhns Algorithm to verify if the card is a valid card through its card number

    public Boolean verifyCard(paymentCard currentCard)
    {
        String cardNumber = Integer.toString(currentCard.getCardNumber()); // converts the card number into a string so that it can be traversed and algorithm can be done

        int sum = 0; // sum is used in Luhn Algorithm which is a modulo algorithm to check if a card is valid

        int currentDigit = 0;

        // traverse through the card number to perform the algorithm
       for(int i = cardNumber.length()-2; i >= 0; i -= 2) // the update is two go every other digit starting from the right side
       {
           currentDigit = cardNumber.charAt(i) - '0'; // the character ASCII minus the ASCII of 0 will give the numerical value of that digit

           currentDigit *= 2; // multiply the current digit times 2

           if(currentDigit >= 10) // it is a double digit number, then need to add the two digits of the number together to make it a single digit number as per algorihtm requirements
           {
               String currentDigitString = Integer.toString(currentDigit); // convert it to a string so we can go through digit by digit and add it

               int currentDigitSum = 0;

               for(int j = 0; j < currentDigitString.length(); j++) // traverses through that two digit number
               {
                  currentDigitSum += (currentDigitString.charAt(j) - '0'); // adds that single digit, then will add the next digit to it
               }

               sum += currentDigitSum; // add the sum of that two digit number to the current sum, per the Luhn Algorithm

           }
           else // if the current digit after being multiplied by two is a single digit number
           {
               sum += currentDigit; // add the current digit to the sum
           }

       }

       // does the secondary part of the algorithm which is for the other digits
       for(int i = cardNumber.length()-1; i >= 0; i = i-2)
       {
           sum += (cardNumber.charAt(i) - '0'); // adds that secondary digits
       }


        return (sum % 10 == 0) ? true : false; // ternary operator to decide if the mod 10 of the sum is true or false will allow for it to be validated or invalidated card
    }


}