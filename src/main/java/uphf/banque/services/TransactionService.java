package uphf.banque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uphf.banque.entities.beans.Compte;
import uphf.banque.entities.beans.Transaction;
import uphf.banque.entities.rest.compte.TransactionDTO;
import uphf.banque.repositories.TransactionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    public List<Transaction> getTransactionsByCompte(Compte compte){
        List<Transaction> list = transactionRepository.findTransactionsByCompte(compte);
        return list;
    }
    public TransactionDTO getTransactionDTO(Transaction transaction){

        TransactionDTO tran = TransactionDTO.builder()
                .id(Integer.toString(transaction.getId()))
                .montant(transaction.getMontant())
                .typeTransaction(transaction.getTypeTransaction())
                .typeSource(transaction.getTypeSource())
                .idSource(transaction.getIdSource())
                .build();

        return tran;
    }
}
