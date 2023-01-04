package uphf.banque.services.dto.client;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uphf.banque.entities.Transaction;
import uphf.banque.entities.TypeSource;
import uphf.banque.entities.TypeTransaction;

@Getter
@Setter
@Builder
public class GetTransactionsCompteResponse {
    private int id;

    private double montant;

    private TypeTransaction typeTransaction;

    private TypeSource typeSource;

    private String idSource;
}
