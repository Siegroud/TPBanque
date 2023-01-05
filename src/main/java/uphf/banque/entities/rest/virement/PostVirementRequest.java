package uphf.banque.entities.rest.virement;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostVirementRequest {
    private String ibanCompteEmetteur;

    private String ibanCompteBeneficiaire;

    private float montant;

    private String libelleVirement;
}
