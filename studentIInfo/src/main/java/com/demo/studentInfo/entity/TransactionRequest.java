package com.demo.studentInfo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest {

    private Long id;
    private Integer fee;
    private String term;

    // Constructors, getters, setters
}