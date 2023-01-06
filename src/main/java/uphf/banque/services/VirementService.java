package uphf.banque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uphf.banque.entities.Compte;
import uphf.banque.entities.Transaction;
import uphf.banque.entities.Virement;
import uphf.banque.services.dto.compte.TransactionDTO;
import uphf.banque.services.dto.virement.PostVirementRequest;
import uphf.banque.services.dto.virement.PostVirementResponse;
import uphf.banque.repositories.CompteRepository;
import uphf.banque.repositories.VirementRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VirementService {

    @Autowired
    private VirementRepository virementRepository;
    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private CompteService compteService;

    public PostVirementResponse createVirement(PostVirementRequest postVirementRequest){

        Compte compte = compteRepository.findCompteByIban(postVirementRequest.getIbanCompteBeneficiaire());

        List<Transaction> listTransaction = compte.getTransactions();
        List<TransactionDTO> listTransactionDTO = new ArrayList<>();

        for (Transaction transaction:listTransaction) {
             listTransactionDTO.add(compteService.getTransactionDTO(transaction));
        }

        Virement virement = Virement.builder()
                .dateCreation((LocalDateTime.now()).toString())
                .ibanCompteEmetteur(postVirementRequest.getIbanCompteEmetteur())
                .ibanCompteBeneficiaire(postVirementRequest.getIbanCompteBeneficiaire())
                .montant(postVirementRequest.getMontant())
                .transactions(listTransaction)
                .build();

        virementRepository.save(virement);

        PostVirementResponse postVirementResponse = PostVirementResponse.builder()
                .idVirement(Integer.toString(virement.getIdVirement()))
                .dateCreation(virement.getDateCreation())
                .transactions(listTransactionDTO)
                .build();
        return postVirementResponse;
    }

}
