package uphf.banque.entities.rest.carte;

import lombok.*;

import java.util.List;

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
