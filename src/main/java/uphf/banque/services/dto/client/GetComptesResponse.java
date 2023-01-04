package uphf.banque.services.dto.client;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uphf.banque.entities.Client;
import uphf.banque.entities.TypeCompte;

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

    private TypeCompte typeCompte;

    private List<GetTitulairesCompteResponse> titulairesCompte;
    private List<GetTransactionsCompteResponse> transactions;


}
