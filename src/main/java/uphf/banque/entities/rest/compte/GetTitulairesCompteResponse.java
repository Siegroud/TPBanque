package uphf.banque.entities.rest.compte;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uphf.banque.entities.beans.Client;

import java.util.List;

@Getter
@Setter
@Builder
public class GetTitulairesCompteResponse {
    private String idClient;
}
