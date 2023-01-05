package uphf.banque.entities.rest.compte;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uphf.banque.entities.TypeCompte;

import java.util.List;
@Getter
@Setter
@Builder
public class CompteDTO {
    private String iban;

    private String solde;

    private String intituleCompte;

    private TypeCompte typecompte;

    private List<String> titulairesCompte;
}
