package uphf.banque.entities.rest.virement;

import lombok.*;
import uphf.banque.entities.rest.compte.TransactionDTO;

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
