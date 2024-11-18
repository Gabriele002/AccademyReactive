package academy.esercizi;

import java.util.Optional;

public class ProvaOptional {
    public static void main(String[] args) {

        Optional<Integer> op = Optional.of(9455);
        op = Optional.empty();

        // print value
        System.out.println("Optional: " + op);

        // orElse value
        System.out.println("Value by orElse" + "(100) method: " + op.orElseThrow(RuntimeException::new));
    }
}

