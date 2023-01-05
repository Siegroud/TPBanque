package uphf.banque.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uphf.banque.entities.beans.Transaction;
import uphf.banque.entities.rest.compte.TransactionDTO;
import uphf.banque.services.TransactionService;

@RestController
@RequestMapping("paiement")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @GetMapping
    public TransactionDTO getTransactionDTO(@RequestBody Transaction transaction){
        return transactionService.getTransactionDTO(transaction);
    }



}
