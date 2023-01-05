package uphf.banque.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uphf.banque.entities.beans.Transaction;
import uphf.banque.entities.rest.compte.TransactionDTO;
import uphf.banque.services.TransactionService;

@RestController
@RequestMapping("paiement")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @GetMapping
    public TransactionDTO getTransactionDTO(@RequestBody Transaction transaction){
        return transactionService.getTransactionDTO(transaction);
    }
}
