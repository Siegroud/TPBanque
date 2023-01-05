package uphf.banque.entities.rest.carte;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostCarteRequest {
    private String titulaireCarte;

    private int code;
}
