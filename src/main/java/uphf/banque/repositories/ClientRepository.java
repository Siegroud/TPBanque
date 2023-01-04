package uphf.banque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uphf.banque.entities.beans.Client;
import uphf.banque.entities.beans.Compte;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {
    Client findClientById(int id);

    List<Client> findClientsByPrenomAndNom(String prenom, String nom);

    Client findClientsByCompte(Compte compte);


}
