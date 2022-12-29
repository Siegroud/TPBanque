package uphf.banque.services.dto.client;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetCarteResponse {
    private String titulaireCarte;

    private String numeroCarte;

    private String dateExpiration;

}
