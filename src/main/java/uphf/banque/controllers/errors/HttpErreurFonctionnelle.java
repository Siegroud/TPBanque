package uphf.banque.controllers.errors;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HttpErreurFonctionnelle {
    private String message;
}
