package uphf.banque.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private float montant;
    private TypeTransaction typeTransaction;

    private TypeSource typeSource;
    private String dateCreation;
    private String idSource;


}
