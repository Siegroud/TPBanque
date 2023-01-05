package uphf.banque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uphf.banque.entities.TypeSource;
import uphf.banque.entities.TypeTransaction;
import uphf.banque.entities.beans.Carte;
import uphf.banque.entities.beans.Compte;
import uphf.banque.entities.beans.Transaction;
import uphf.banque.entities.beans.Virement;
import uphf.banque.entities.rest.compte.PostPaiementRequest;
import uphf.banque.entities.rest.compte.PostPaiementResponse;
import uphf.banque.entities.rest.compte.TransactionDTO;
import uphf.banque.entities.rest.virement.PostVirementRequest;
import uphf.banque.entities.rest.virement.PostVirementResponse;
import uphf.banque.repositories.CompteRepository;
import uphf.banque.repositories.VirementRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VirementService {

    @Autowired
    CompteRepository compteRepository;

    @Autowired
    TransactionService transactionService;

    public PostVirementResponse createVirement(PostVirementRequest postVirementRequest){

        Compte compte = compteRepository.findCompteByIban(postVirementRequest.getIbanCompteBeneficiaire());

        List<Transaction> listTransaction = compte.getTransactions();
        List<TransactionDTO> listTransactionDTO = new ArrayList<>();

        for (Transaction transaction:listTransaction) {
             listTransactionDTO.add(transactionService.getTransactionDTO(transaction));
        }

        Virement virement = Virement.builder()
                .dateCreation((LocalDateTime.now()).toString())
                .ibanCompteEmetteur(postVirementRequest.getIbanCompteEmetteur())
                .ibanCompteBeneficiaire(postVirementRequest.getIbanCompteBeneficiaire())
                .montant(postVirementRequest.getMontant())
                .transactions(listTransaction)
                .build();

        PostVirementResponse postVirementResponse = PostVirementResponse.builder()
                .idVirement(Integer.toString(virement.getIdVirement()))
                .dateCreation(virement.getDateCreation())
                .transactions(listTransactionDTO)
                .build();
        return postVirementResponse;
    }

}
