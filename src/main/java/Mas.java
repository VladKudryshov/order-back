import java.awt.*;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class Mas {
    public static void main(String[] args) {
        AtomicInteger a = new AtomicInteger(0);
        int i = Optional.ofNullable(a)
                .orElse(new AtomicInteger()).get();
    }
}
