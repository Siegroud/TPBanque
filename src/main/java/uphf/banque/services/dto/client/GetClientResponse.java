package uphf.banque.services.dto.client;

import lombok.*;
import uphf.banque.entities.Client;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetClientResponse {

    private int id;

    private String nom;

    private String prenom;

    private String dateNaissance;

    private String telephone;

    private String adressePostale;

    private String dateCreation;

}
