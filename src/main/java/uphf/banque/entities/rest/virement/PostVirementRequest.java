package uphf.banque.entities.rest.virement;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostVirementRequest {
    private String ibanCompteEmetteur;

    private String ibanCompteBeneficiaire;

    private float montant;

    private String libelleVirement;
}
