package uphf.banque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uphf.banque.entities.Client;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {
    List<Client> findClientByPrenomAndNom(String prenom, String nom);
}
