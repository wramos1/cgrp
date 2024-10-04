package cgrp;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "cars")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Id
    private ObjectId id;

    private String make;

    private String model;

    private String year;

    @DocumentReference
    private List<Review> reviewIds;
}
