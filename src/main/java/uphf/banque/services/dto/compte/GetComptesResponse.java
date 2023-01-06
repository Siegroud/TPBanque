package uphf.banque.services.dto.compte;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class GetComptesResponse {

    private List<CompteDTO> comptes;


}
