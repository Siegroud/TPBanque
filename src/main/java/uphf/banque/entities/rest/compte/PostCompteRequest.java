package uphf.banque.entities.rest.compte;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uphf.banque.entities.TypeCompte;
import uphf.banque.entities.beans.Client;

import java.util.List;
@Getter
@Setter
@Builder
public class PostCompteRequest {
    private String intituleCompte;

    private TypeCompte typeCompte;

    private List<Client> titulairesCompte;

}
