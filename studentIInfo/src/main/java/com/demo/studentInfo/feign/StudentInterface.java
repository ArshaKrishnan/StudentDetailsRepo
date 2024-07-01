package com.demo.studentInfo.feign;


import com.demo.studentInfo.entity.TransactionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Interface used to call other microservice and define the Transaction api's
 */
@FeignClient("TRANSACTIONSERVICE")
public interface StudentInterface {
    @PutMapping("/transactions/update")
    public ResponseEntity<String> addTransaction(@RequestBody TransactionRequest transaction);

    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getTransactionsForStudent(@PathVariable Long studentId);

}
