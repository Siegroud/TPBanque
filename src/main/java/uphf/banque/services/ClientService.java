package uphf.banque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uphf.banque.entities.Client;
import uphf.banque.exceptions.ProcessException;
import uphf.banque.repositories.ClientRepository;
import uphf.banque.services.dto.client.GetClientResponse;

@Service
public class ClientService {

    public static final String CLIENT_NON_TROUVE = "Le client n'a pas été trouvé.";
    @Autowired
    private ClientRepository clientRepository;

    public GetClientResponse getClientByNomAndPrenom(String nom, String prenom)throws ProcessException {
        Client cli = (Client) clientRepository.findClientByPrenomAndNom(nom,prenom);
        if (cli == null) throw new ProcessException(String.format(CLIENT_NON_TROUVE, nom));
        GetClientResponse getClientResponse = GetClientResponse.builder()
                .id(cli.getId())
                .prenom(cli.getPrenom())
                .nom(cli.getNom())
                .dateNaissance(cli.getDateNaissance())
                .telephone(cli.getTelephone())
                .adressePostale(cli.getAdressePostale())
                .dateCreation(cli.getDateCreation())
                .build();
        return getClientResponse;
    }

}
