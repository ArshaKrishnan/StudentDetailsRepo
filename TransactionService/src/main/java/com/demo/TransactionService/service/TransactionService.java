package com.demo.TransactionService.service;
import com.demo.TransactionService.entity.FeeTransaction;
import com.demo.TransactionService.repository.TransactionRepository;
import com.demo.TransactionService.util.FeeStructure;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private FeeStructure feeStructure;

    /**
     * Method to calculate Fee
     * @param transactionDetails
     * @return
     */

    public ResponseEntity<?> updateTransaction(FeeTransaction transactionDetails) {


        BigDecimal fee = calculateFeeForStudent(transactionDetails);
        log.info(String.valueOf(fee.doubleValue()));
        if (fee != null) {
            transactionDetails.setFee(fee.doubleValue());
        }
         transactionRepository.save(transactionDetails);

        return ResponseEntity.ok().body(transactionDetails);
    }

    public BigDecimal calculateFeeForStudent(FeeTransaction transaction) {
        int gradeLevel = Integer.parseInt(transaction.getTerm());
        BigDecimal fee = feeStructure.getFeeForGrade(gradeLevel);
        return fee;
    }


}

