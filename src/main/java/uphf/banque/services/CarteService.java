package uphf.banque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uphf.banque.entities.Carte;
import uphf.banque.entities.Client;
import uphf.banque.entities.Compte;
import uphf.banque.exceptions.ProcessException;
import uphf.banque.repositories.CarteRepository;
import uphf.banque.repositories.CompteRepository;
import uphf.banque.services.dto.client.GetCarteResponse;
import uphf.banque.services.dto.client.GetClientResponse;
import uphf.banque.services.dto.client.GetComptesResponse;

import java.util.ArrayList;
import java.util.List;


@Service
public class CarteService {

    @Autowired
    CarteRepository carteRepository;

    @Autowired
    CompteRepository compteRepository;

    public static final String CARTE_NON_TROUVEE = "Carte non trouvée.";

    public GetCarteResponse getCarteResponse(String iban) throws ProcessException{

        Carte carte = (Carte) carteRepository.findCarteByIban(iban);

        String idClient = Integer.toString(carte.getTitulaireCarte().getId());

        if (carte == null) throw new ProcessException(String.format(CARTE_NON_TROUVEE, iban));
        GetCarteResponse getCarteResponse = GetCarteResponse.builder()
                .numeroCarte(carte.getNumeroCarte())
                .dateExpiration(carte.getDateExpiration())
                .titulaireCarte(idClient)
                .build();

        return getCarteResponse;
    }
}
