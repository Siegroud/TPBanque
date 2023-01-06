package uphf.banque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uphf.banque.entities.*;
import uphf.banque.repositories.TransactionRepository;
import uphf.banque.services.dto.compte.TransactionDTO;
import uphf.banque.services.dto.virement.PostVirementRequest;
import uphf.banque.services.dto.virement.PostVirementResponse;
import uphf.banque.repositories.CompteRepository;
import uphf.banque.repositories.VirementRepository;

import javax.swing.text.TabableView;
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
    private TransactionRepository transactionRepository;

    @Autowired
    private CompteService compteService;

    public PostVirementResponse createVirement(PostVirementRequest postVirementRequest){



        Compte compte = compteRepository.findCompteByIban(postVirementRequest.getIbanCompteBeneficiaire());

        Compte emet = compteRepository.findCompteByIban(postVirementRequest.getIbanCompteEmetteur());

        if (compte == null || emet == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Compte(s) non trouvé(s)");
        }
        if(Float.parseFloat(emet.getSolde())<postVirementRequest.getMontant()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Solde insuffisant.");
        }


        List<TransactionDTO> listTransactionDTOEmet = new ArrayList<>();

        List<TransactionDTO> listTransactionDTOBenef = new ArrayList<>();


        Transaction transactionBenef = Transaction.builder()
                .montant(postVirementRequest.getMontant())
                .typeTransaction(TypeTransaction.CREDIT)
                .typeSource(TypeSource.VIREMENT)
                .dateCreation((LocalDateTime.now()).toString())
                .virementSource(postVirementRequest.getIbanCompteEmetteur())
                .build();

        Transaction transactionEmet = Transaction.builder()
                .montant(postVirementRequest.getMontant())
                .typeTransaction(TypeTransaction.DEBIT)
                .typeSource(TypeSource.VIREMENT)
                .dateCreation((LocalDateTime.now()).toString())
                .virementSource(postVirementRequest.getIbanCompteEmetteur())
                .build();

        transactionRepository.save(transactionEmet);
        compte.getTransactions().add(transactionBenef);
        emet.getTransactions().add(transactionEmet);

        List<Transaction> listTransactionEmet = new ArrayList<>(emet.getTransactions());
        for (Transaction trans:listTransactionEmet) {
            listTransactionDTOEmet.add(compteService.getTransactionDTO(trans));
        }


        List<Transaction> listTransactionBenef = new ArrayList<>(compte.getTransactions());

        for (Transaction trans:listTransactionBenef) {
            listTransactionDTOBenef.add(compteService.getTransactionDTO(trans));
        }


        Virement virementEmet = Virement.builder()
                .dateCreation(transactionEmet.getDateCreation())
                .ibanCompteEmetteur(postVirementRequest.getIbanCompteEmetteur())
                .ibanCompteBeneficiaire(postVirementRequest.getIbanCompteBeneficiaire())
                .montant(postVirementRequest.getMontant())
                .transactions(listTransactionEmet)
                .build();

        Virement virementBenef = Virement.builder()
                .dateCreation(transactionEmet.getDateCreation())
                .ibanCompteEmetteur(postVirementRequest.getIbanCompteEmetteur())
                .ibanCompteBeneficiaire(postVirementRequest.getIbanCompteBeneficiaire())
                .montant(postVirementRequest.getMontant())
                .transactions(listTransactionBenef)
                .build();


        virementRepository.save(virementEmet);
        virementRepository.save(virementBenef);

        emet.setSolde(Float.toString(Float.parseFloat(emet.getSolde())- virementEmet.getMontant()));
        compte.setSolde(Float.toString(Float.parseFloat(compte.getSolde())+ virementEmet.getMontant()));

        compteRepository.save(compte);
        compteRepository.save(emet);

        PostVirementResponse postVirementResponse = PostVirementResponse.builder()
                .idVirement(Integer.toString(virementEmet.getIdVirement()))
                .dateCreation(virementEmet.getDateCreation())
                .transactions(listTransactionDTOEmet)
                .build();
        return postVirementResponse;
    }


}
