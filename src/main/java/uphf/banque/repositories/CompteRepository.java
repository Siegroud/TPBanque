package uphf.banque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uphf.banque.entities.Compte;
import uphf.banque.services.dto.client.GetComptesResponse;

import java.util.List;

@Repository
public interface CompteRepository extends JpaRepository<Compte,Integer> {

    List<Compte> findCompteById(Integer id);


}
