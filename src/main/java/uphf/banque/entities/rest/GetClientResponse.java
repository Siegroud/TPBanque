package uphf.banque.entities.rest;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

@Getter
@Setter
@Builder
public class GetClientResponse {
    private int id;
    private String prenom;
    private String nom;
    private Date dateNaissance;
    private String telephone;
    private String adressePostale;
    private Date dateCreation;


}
