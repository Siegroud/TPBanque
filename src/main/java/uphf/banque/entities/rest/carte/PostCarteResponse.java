package uphf.banque.entities.rest.carte;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCarteResponse {
    private String titulaireCarte;

    private String numeroCarte;

    private String dateExpiration;
}
