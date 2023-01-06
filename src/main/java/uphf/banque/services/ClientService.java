package uphf.banque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uphf.banque.entities.Client;
import uphf.banque.entities.Compte;
import uphf.banque.exceptions.ProcessException;
import uphf.banque.repositories.ClientRepository;
import uphf.banque.services.dto.client.*;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService extends ExceptionService {
    @Autowired
    private ClientRepository clientRepository;
    public static final String CLIENT_NON_TROUVE = "Le client n'a pas été trouvé.";


    public List<GetClientResponse> getClientsByNomAndPrenom(String nom, String prenom)throws ProcessException {
        return this.clientRepository.findClientsByPrenomAndNom(prenom,nom)
                .stream()
                .map(client -> GetClientResponse.builder()
                        .id(client.getId())
                        .nom(client.getNom())
                        .prenom(client.getPrenom())
                        .dateNaissance(client.getDateNaissance())
                        .telephone(client.getTelephone())
                        .adressePostale(client.getAdressePostale())
                        .dateCreation(client.getDateCreation())
                        .build()).collect(Collectors.toList());
    }


    public PostClientResponse createClient(PostClientRequest postClientRequest){

        List<Compte> list = new ArrayList<>();
        Client cli = Client.builder()
                .prenom(postClientRequest.getPrenom())
                .nom(postClientRequest.getNom())
                .dateNaissance(postClientRequest.getDateNaissance())
                .telephone(postClientRequest.getTelephone())
                .adressePostale(postClientRequest.getAdressePostale())
                .dateCreation((LocalDateTime.now()).toString())
                .codeBanque(30003)
                .codeGuichet(02054)
                .comptes(list)
                .dateModification(null)
                .build();

        clientRepository.save(cli); //Crée le client dans la BDD

        return PostClientResponse.builder()
                .id(cli.getId())
                .prenom(cli.getPrenom())
                .nom(cli.getNom())
                .dateNaissance(cli.getDateNaissance())
                .telephone(cli.getTelephone())
                .adressePostale(cli.getAdressePostale())
                .dateCreation(cli.getDateCreation())
                .build();
    }


    public PutClientResponse updateClient(PutClientRequest putClientRequest) throws ProcessException {
        Client cli = clientRepository.findClientById(putClientRequest.getId());

        if (cli == null) throw new ProcessException(String.format((CLIENT_NON_TROUVE + "%s"),putClientRequest.getId()));

        cli.setPrenom(putClientRequest.getPrenom());
        cli.setNom(putClientRequest.getNom());
        cli.setDateNaissance(putClientRequest.getDateNaissance());
        cli.setTelephone(putClientRequest.getTelephone());
        cli.setAdressePostale(putClientRequest.getAdressePostale());
        cli.setDateModification((LocalDateTime.now()).toString());
        clientRepository.save(cli);

        return PutClientResponse.builder()
                .prenom(putClientRequest.getPrenom())
                .nom(putClientRequest.getNom())
                .dateNaissance(putClientRequest.getDateNaissance())
                .telephone(putClientRequest.getTelephone())
                .adressePostale(putClientRequest.getAdressePostale()).build();
    }

}
