package uphf.banque.entities.rest.compte;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uphf.banque.entities.TypeCompte;
import uphf.banque.entities.beans.Client;
import uphf.banque.entities.beans.Compte;
import uphf.banque.entities.beans.Transaction;

import java.util.List;

@Getter
@Setter
@Builder

public class GetComptesResponse {

    private List<CompteDTO> comptes;


}
