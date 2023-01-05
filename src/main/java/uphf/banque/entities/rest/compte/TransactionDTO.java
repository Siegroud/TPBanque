package uphf.banque.entities.rest.compte;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uphf.banque.entities.TypeSource;
import uphf.banque.entities.TypeTransaction;
@Getter
@Setter
@Builder
public class TransactionDTO {
    private String id;

    private double montant;

    private TypeTransaction typeTransaction;

    private TypeSource typeSource;

    private String idSource;
}
