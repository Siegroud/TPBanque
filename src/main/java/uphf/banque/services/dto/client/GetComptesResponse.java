package uphf.banque.services.dto.client;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uphf.banque.entities.Client;

import javax.persistence.OneToMany;
import javax.transaction.Transaction;
import java.util.List;

@Getter
@Setter
@Builder

public class GetComptesResponse {

    private String iban;

    private double solde;
    private String intituleCompte;

    private String typeCompte;

    private List<Integer> titulairesCompte;
    private List<Transaction> transacBenef;
    private List<Transaction> transacEmet;


}
