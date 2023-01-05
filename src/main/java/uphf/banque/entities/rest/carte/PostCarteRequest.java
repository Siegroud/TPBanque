package uphf.banque.entities.rest.carte;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCarteRequest {
    private String titulaireCarte;

    private int code;
}
