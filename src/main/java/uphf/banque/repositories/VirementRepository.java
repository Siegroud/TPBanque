package uphf.banque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uphf.banque.entities.Virement;

@Repository
public interface VirementRepository extends JpaRepository<Virement,Integer> {

}
