package uphf.banque.services.dto.carte;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCarteResponse {

    private String numeroCarte;

    private String dateExpiration;

    private String titulaireCarte;

}
