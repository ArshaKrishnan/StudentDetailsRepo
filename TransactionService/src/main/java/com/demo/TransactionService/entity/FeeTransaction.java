package com.demo.TransactionService.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Entity to take Fee Transaction details
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "student")
public class FeeTransaction {
                @Id
                private Long id;
                private double fee;
                private String term;





    // constructors, getters, and setters
        }

