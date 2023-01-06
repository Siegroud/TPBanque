package uphf.banque.controllers.errors;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HttpErrorResponse {
    private String message;
}
