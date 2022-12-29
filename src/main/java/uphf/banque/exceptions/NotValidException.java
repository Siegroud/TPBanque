package uphf.banque.exceptions;

import java.util.ArrayList;
import java.util.List;

public class NotValidException extends RuntimeException{

    private List<String> message;

    public NotValidException(){this.message = new ArrayList<>();}
}
