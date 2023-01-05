package uphf.banque.entities.rest.client;

import lombok.*;
import uphf.banque.entities.beans.Client;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetClientResponse {

    private List<Client> clients;

}
