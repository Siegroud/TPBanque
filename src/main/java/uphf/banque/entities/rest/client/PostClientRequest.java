package uphf.banque.entities.rest.client;

import lombok.*;
import uphf.banque.entities.beans.Client;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostClientRequest {
    private String prenom;

    private String nom;

    private String dateNaissance;

    private String telephone;

    private String adressePostale;
}
