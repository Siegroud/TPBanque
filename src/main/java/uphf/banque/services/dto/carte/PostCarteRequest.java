package uphf.banque.services.dto.carte;

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
