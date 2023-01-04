package uphf.banque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uphf.banque.entities.beans.Carte;

@Repository
public interface CarteRepository extends JpaRepository<Carte,Integer> {
    Carte findCarteByIban(String iban);
}
