package uphf.banque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uphf.banque.entities.beans.Carte;
import uphf.banque.entities.beans.Compte;

@Repository
public interface CarteRepository extends JpaRepository<Carte,Integer> {
    Carte findCarteByCompte(Compte compte);
}
