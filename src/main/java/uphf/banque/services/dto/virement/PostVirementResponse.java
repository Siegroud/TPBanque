package uphf.banque.services.dto.virement;

import lombok.*;
import uphf.banque.services.dto.compte.TransactionDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostVirementResponse {
    private String idVirement;

    private String dateCreation;

    private List<TransactionDTO> transactions;
}
