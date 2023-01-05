package uphf.banque.entities.rest.compte;

import lombok.*;
import uphf.banque.entities.TypeTransaction;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostPaiementResponse {
    private int idTransaction;

    private float montant;

    private TypeTransaction typeTransaction;

    private String dateCreation;
}
