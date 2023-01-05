package uphf.banque.entities.rest.client;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uphf.banque.entities.beans.Client;

@Getter
@Setter
@Builder
public class PostClientResponse {

    private Client client;

}