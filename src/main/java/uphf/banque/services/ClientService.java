package uphf.banque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import uphf.banque.entities.beans.Client;
import uphf.banque.entities.beans.Compte;
import uphf.banque.entities.rest.client.PostClientResponse;
import uphf.banque.entities.rest.client.PutClientRequest;
import uphf.banque.entities.rest.client.PutClientResponse;
import uphf.banque.exceptions.ProcessException;
import uphf.banque.repositories.ClientRepository;
import uphf.banque.entities.rest.client.GetClientResponse;
import java.util.Date;

import java.util.List;

@Service
public class ClientService {

    public static final String CLIENT_NON_TROUVE = "Le client n'a pas été trouvé.";
    @Autowired
    private ClientRepository clientRepository;


    public GetClientResponse getClientsByNomAndPrenom(String nom, String prenom)throws ProcessException {
        List<Client> listcli = clientRepository.findClientsByPrenomAndNom(nom,prenom);
        if (listcli == null) throw new ProcessException(String.format((CLIENT_NON_TROUVE + "%s"), nom));

        return GetClientResponse.builder()
                .clients(listcli)
                .build();
    }



    public PostClientResponse createClient(String prenom, String nom, String dateNaissance, String telephone, String adressepostale, List<Compte> compteList){

        Client cli = Client.builder()
                .prenom(prenom)
                .nom(nom)
                .dateNaissance(dateNaissance)
                .telephone(telephone)
                .adressePostale(adressepostale)
                .comptes(compteList)
                .build();

        clientRepository.save(cli); //Crée le client dans la BDD

        return PostClientResponse.builder()
                .client(cli)
                .build();
    }


    public PutClientRequest updateClientRequest(@RequestParam("id") int id,@RequestParam("prenom") String prenom,@RequestParam("nom") String nom,
                                                @RequestParam("dateNaissance") String dateNaissance, @RequestParam("telephone") String telephone,
                                                @RequestParam("adressePostale") String adressePostale) throws ProcessException{
        Client cli = clientRepository.findClientById(id);

        if (cli == null) throw new ProcessException(String.format((CLIENT_NON_TROUVE + "%s"),id));

        cli.setPrenom(prenom);
        cli.setNom(nom);
        cli.setDateNaissance(dateNaissance);
        cli.setTelephone(telephone);
        cli.setAdressePostale(adressePostale);
        clientRepository.save(cli);


    }
    public PutClientResponse updateClientResponse(String nom,String prenom) throws ProcessException{

    }

}
