package uphf.banque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uphf.banque.entities.beans.Carte;
import uphf.banque.entities.rest.compte.GetComptesResponse;
import uphf.banque.exceptions.ProcessException;
import uphf.banque.repositories.CarteRepository;
import uphf.banque.repositories.CompteRepository;
import uphf.banque.entities.rest.carte.GetCarteResponse;


@Service
public class CarteService {

    @Autowired
    CarteRepository carteRepository;

    @Autowired
    CompteRepository compteRepository;

    public static final String CARTE_NON_TROUVEE = "Carte non trouvée.";

    public GetCarteResponse getCarteResponse(String iban) throws ProcessException{

        Carte carte = carteRepository.findCarteByCompte(iban);

        GetComptesResponse getComptesResponse = compteRepository.

        if (carte == null) throw new ProcessException(String.format((CARTE_NON_TROUVEE + "%s"), iban));
        GetCarteResponse getCarteResponse = GetCarteResponse.builder()
                .numeroCarte(carte.getNumeroCarte())
                .dateExpiration(carte.getDateExpiration())
                .titulaireCarte(idClient)
                .build();

        return getCarteResponse;
    }
}
