package uphf.banque.services.dto.compte;

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
