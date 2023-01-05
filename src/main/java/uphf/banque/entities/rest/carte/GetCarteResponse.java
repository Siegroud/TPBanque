package uphf.banque.entities.rest.carte;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class GetCarteResponse {

    private String numeroCarte;

    private String dateExpiration;

    private List<String> titulaireCarte;

}
