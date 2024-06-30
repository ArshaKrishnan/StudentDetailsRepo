package com.demo.TransactionService.controller;

import com.demo.TransactionService.entity.FeeTransaction;
import com.demo.TransactionService.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /** For saving fee details to Student Details **/
    @PutMapping("/transactions/update")
    public ResponseEntity<String> saveFeeTransaction(@RequestBody FeeTransaction request) {
        try{

            ResponseEntity<?> fee=transactionService.updateTransaction(request);
            return ResponseEntity.status(HttpStatus.OK).body("Fee details Updated in student Table ");
             }
        catch(Exception e){
            log.error("Error occurred while adding student fee details : {}", e.getMessage(), e);
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while creating student ");

        }

    }


}


