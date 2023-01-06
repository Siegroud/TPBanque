package uphf.banque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uphf.banque.entities.Carte;
import uphf.banque.entities.Compte;

import java.util.List;

@Repository
public interface CarteRepository extends JpaRepository<Carte,Integer> {
    Carte findCarteByNumeroCarte(String numeroCarte);

    Carte findCarteByCompte(Compte compte);

    List<Carte> findCartesByCompte(Compte compte);
}
