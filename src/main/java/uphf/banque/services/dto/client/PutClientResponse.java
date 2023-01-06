package uphf.banque.services.dto.client;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
