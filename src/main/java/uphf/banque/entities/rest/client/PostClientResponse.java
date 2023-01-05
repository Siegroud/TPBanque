package uphf.banque.entities.rest.client;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uphf.banque.entities.beans.Client;

@Getter
@Setter
@Builder
public class PostClientResponse {

    private int id;

    private String prenom;

    private String nom;

    private String dateNaissance;

    private String telephone;
    private String adressePostale;

    private String dateCreation;

}