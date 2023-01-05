package uphf.banque.entities.rest.compte;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uphf.banque.entities.TypeTransaction;

@Getter
@Setter
@Builder
public class PostPaiementResponse {
    private int idTransaction;

    private float montant;

    private TypeTransaction typeTransaction;

    private String dateCreation;
}
