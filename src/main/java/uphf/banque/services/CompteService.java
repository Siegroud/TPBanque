package uphf.banque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uphf.banque.entities.TypeCompte;
import uphf.banque.entities.beans.Client;
import uphf.banque.entities.beans.Compte;
import uphf.banque.entities.rest.compte.PostCompteRequest;
import uphf.banque.entities.rest.compte.PostCompteResponse;
import uphf.banque.repositories.ClientRepository;
import uphf.banque.repositories.CompteRepository;
import uphf.banque.entities.rest.compte.GetComptesResponse;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompteService extends ExceptionService{
    @Autowired
    CompteRepository compteRepository;

    @Autowired
    ClientRepository clientRepository;

    private static final String COMPTE_NON_TROUVE = "Le compte n'a pas été trouvé.";


    public GetComptesResponse getComptesByIdClient(int id) {
        Client cli = clientRepository.findClientById(id);

        List<Compte> listcom = compteRepository.findComptesByClient(cli);


        return new GetComptesResponse(listcom);
    }

    public String calculNumeroCompte(){
        Long leftLimit = 10000000000L;
        Long rightLimit = 99999999999L;
        long generatedLong = (long) (Math.random() * (rightLimit - leftLimit));
        return Long.toString(generatedLong);
    }

    public PostCompteResponse createCompte(PostCompteRequest postCompteRequest) {
        int codeBanque = postCompteRequest.getTitulairesCompte().get(0).getCodeBanque();
        int codeGuichet = postCompteRequest.getTitulairesCompte().get(0).getCodeGuichet();



        int numeroCompte = Integer.parseInt(calculNumeroCompte());
        int calcIban = 97 - (89 * codeBanque + 15 * codeGuichet + 3*numeroCompte)%97;
        String iban = "FR76"+ Integer.toString(codeBanque)+Integer.toString(codeGuichet) + Integer.toString(numeroCompte)+ Integer.toString(calcIban);
        Compte com = Compte.builder()
                .intituleCompte(postCompteRequest.getIntituleCompte())
                .typeCompte(postCompteRequest.getTypeCompte())
                .titulairesCompte(postCompteRequest.getTitulairesCompte())
                .iban(iban)
                .dateCreation((LocalDateTime.now()).toString())
                .solde(0)
                .build();
        compteRepository.save(com);

        return PostCompteResponse.builder()
                .intituleCompte(postCompteRequest.getIntituleCompte())
                .typeCompte(postCompteRequest.getTypeCompte())
                .titulairesCompte(postCompteRequest.getTitulairesCompte())
                .iban(iban)
                .dateCreation(com.getDateCreation())
                .build();
    }
}
