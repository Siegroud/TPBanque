package uphf.banque.entities.rest.client;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PutClientResponse {
    private int id;

    private String prenom;

    private String nom;

    private String dateNaissance;

    private String telephone;

    private String adressePostale;

    private String dateModification;

}
