package uphf.banque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uphf.banque.entities.beans.Compte;
import uphf.banque.entities.beans.Virement;

@Repository
public interface VirementRepository extends JpaRepository<Virement,Integer> {

}
