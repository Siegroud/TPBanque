package uphf.banque.entities.rest.compte;

import lombok.*;
import uphf.banque.entities.TypeSource;
import uphf.banque.entities.TypeTransaction;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDTO {
    private String id;

    private double montant;

    private TypeTransaction typeTransaction;

    private TypeSource typeSource;

    private String idSource;
}
