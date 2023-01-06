package uphf.banque.services.dto.carte;

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
