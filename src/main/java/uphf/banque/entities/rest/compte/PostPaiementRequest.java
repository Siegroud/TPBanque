package uphf.banque.entities.rest.compte;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostPaiementRequest {
    private float montant;

    private String dateCreation;
}
