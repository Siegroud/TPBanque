package uphf.banque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uphf.banque.entities.TypeCompte;
import uphf.banque.entities.beans.Client;
import uphf.banque.entities.beans.Compte;
import uphf.banque.entities.beans.Transaction;
import uphf.banque.entities.rest.compte.*;
import uphf.banque.repositories.ClientRepository;
import uphf.banque.repositories.CompteRepository;
import uphf.banque.repositories.TransactionRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompteService extends ExceptionService{
    @Autowired
    CompteRepository compteRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionService transactionService;
    private static final String COMPTE_NON_TROUVE = "Le compte n'a pas été trouvé.";

    public GetComptesResponse getComptesByIdClient(int id) {
        Client cli = clientRepository.findClientById(id);

        List<Compte> listcom = compteRepository.findComptesByClient(cli);

        List<CompteDTO> listcomDTO = new ArrayList<>();

        for (Compte com : listcom) {
            List<String> listId = new ArrayList<>();

            List<TransactionDTO> listDTO = new ArrayList<>();

            List<Transaction> listtran = transactionRepository.findTransactionsByCompte(com);

            for (Transaction transaction: listtran) {
                listDTO.add(transactionService.getTransactionDTO(transaction));
            }

            for(Client titulaire : com.getTitulairesCompte()){
                listId.add(Integer.toString(titulaire.getId()));
            }


            CompteDTO comDTO = CompteDTO.builder()
                    .iban(com.getIban())
                    .solde(com.getSolde())
                    .intituleCompte(com.getIntituleCompte())
                    .typecompte(com.getTypeCompte())
                    .titulairesCompte(listId)
                    .transactions(listDTO)
                    .build();

            listcomDTO.add(comDTO);
        }


        return GetComptesResponse.builder().comptes(listcomDTO).build();
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
                .solde("0")
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
