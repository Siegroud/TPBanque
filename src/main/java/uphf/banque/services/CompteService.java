package uphf.banque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uphf.banque.entities.beans.Client;
import uphf.banque.entities.beans.Compte;
import uphf.banque.exceptions.ProcessException;
import uphf.banque.repositories.ClientRepository;
import uphf.banque.repositories.CompteRepository;
import uphf.banque.entities.rest.GetComptesResponse;
import uphf.banque.entities.rest.GetTitulairesCompteResponse;

import java.util.List;

@Service
public class CompteService extends ExceptionService{
    @Autowired
    CompteRepository compteRepository;

    @Autowired
    ClientRepository clientRepository;

    private static final String COMPTE_NON_TROUVE = "Le compte n'a pas été trouvé.";

    public GetTitulairesCompteResponse getClientsByCompte(Compte compte) throws ProcessException{
        Client cli = clientRepository.findClientsByCompte(compte);
        if (cli == null) throw new ProcessException(String.format((COMPTE_NON_TROUVE + "%s"), compte));

        return GetTitulairesCompteResponse.builder()
                .idClient(Integer.toString(cli.getId())).build();
    }

    public GetComptesResponse getComptesByIdClient(int id) {
        Client cli = clientRepository.findClientById(id);

        List<Compte> listcom = compteRepository.findComptesByClient(cli);


        return new GetComptesResponse(listcom);
    }
}
