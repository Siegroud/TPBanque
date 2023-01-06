package uphf.banque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uphf.banque.entities.Carte;
import uphf.banque.entities.Compte;
import uphf.banque.entities.Transaction;
import uphf.banque.entities.Virement;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    List<Transaction> findTransactionsByCarteSource(String carteid);

    List<Transaction> findTransactionsByVirementSource(String virementid);
}
