package uphf.banque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uphf.banque.entities.Client;
import uphf.banque.entities.Compte;

import java.util.List;

@Repository
public interface CompteRepository extends JpaRepository<Compte,Integer> {

    Compte findCompteByIban(String iban); //Récupération compte avec iban

    List<Compte> findComptesByTitulairesCompteContaining(Client client);


}
