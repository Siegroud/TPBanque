package uphf.banque.entities;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    @GeneratedValue
    private int id;

    private String prenom;
    private String nom;
    private Date dateNaissance;
    private String telephone;

    private String adressePostale;
    private Date dateCreation;
    private int codeBanque;
    private int codeGuichet;

    @OneToMany
    private List<Compte> comptes;

}
