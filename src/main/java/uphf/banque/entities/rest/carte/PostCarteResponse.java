package uphf.banque.entities.rest.carte;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostCarteResponse {
    private String titulaireCarte;

    private String numeroCarte;

    private String dateExpiration;
}
