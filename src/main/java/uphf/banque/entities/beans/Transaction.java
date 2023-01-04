package uphf.banque.entities.beans;

import lombok.*;
import uphf.banque.entities.TypeTransaction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private float montant;
    private TypeTransaction typeTransaction;
    private Date dateCreation;
    private String idSource;


}
