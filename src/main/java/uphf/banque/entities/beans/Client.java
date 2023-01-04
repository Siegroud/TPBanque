package uphf.banque.entities.beans;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String prenom;
    private String nom;
    private String dateNaissance;
    private String telephone;

    private String adressePostale;
    private String dateCreation;
    private int codeBanque;
    private int codeGuichet;

    @ManyToMany
    private List<Compte> comptes;

    public Client(String prenom, String nom, String dateNaissance, String telephone, String adressePostale, String dateCreation, List<Compte> comptes) {
        this.prenom = prenom;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.telephone = telephone;
        this.adressePostale = adressePostale;
        this.dateCreation = dateCreation;
        this.codeBanque = 30003;
        this.codeGuichet = 02054;
        this.comptes = comptes;
    }

    //TODO SIEGRID GENERE UN CONSTRUCTEUR AVEC TOUS LES ATTRIBUTS SAUF ID STP

}
