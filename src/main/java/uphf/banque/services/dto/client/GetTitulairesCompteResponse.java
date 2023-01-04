package uphf.banque.services.dto.client;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetTitulairesCompteResponse {
    private String idClient;
}
