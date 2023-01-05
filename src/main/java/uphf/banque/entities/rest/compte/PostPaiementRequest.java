package uphf.banque.entities.rest.compte;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostPaiementRequest {
    private float montant;

    private String dateCreation;
}
