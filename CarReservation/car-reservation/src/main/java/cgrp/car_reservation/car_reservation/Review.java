package cgrp.car_reservation.car_reservation;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor

// Object class of car matching object to be defined in MongoDB
public class Review {

    @Id
    private ObjectId id;

    private String body;

    public Review(String body) {
        this.body = body;
    }
}