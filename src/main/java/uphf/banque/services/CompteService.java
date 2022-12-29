package uphf.banque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uphf.banque.controllers.CompteController;
import uphf.banque.entities.Client;
import uphf.banque.entities.Compte;
import uphf.banque.exceptions.ProcessException;
import uphf.banque.repositories.CompteRepository;
import uphf.banque.services.dto.client.GetComptesResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompteService extends ExceptionService{
    @Autowired
    CompteRepository compteRepository;



    private static final String COMPTE_NON_TROUVE = "Le compte n'a pas été trouvé.";

    public GetComptesResponse getComptesResponse(Integer id) throws ProcessException {
        Compte compte = (Compte) compteRepository.findCompteById(id);

        List<Integer> list = new ArrayList<>();
        for (Client i : compte.getTitulaireCompte()){
            list.add(i.getId());
        }

        if (compte == null) throw new ProcessException(String.format(COMPTE_NON_TROUVE,id));
        GetComptesResponse com = GetComptesResponse.builder()
                .iban(compte.getIban())
                .solde(compte.getSolde())
                .intituleCompte(compte.getIntituleCompte())
                .typeCompte(compte.getTypeCompte())
                .titulairesCompte(list)
                .transacBenef(compte.getTransacBenef())
                .transacEmet(compte.getTransacEmet())
                .build();

        return com;
    }
}
