import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        List<Product> productsToBeOrdered = new ArrayList<>();

        productsToBeOrdered.add(new Product(UUID.randomUUID().toString(), "Kaki"));
        Order order = new Order(UUID.randomUUID().toString(), productsToBeOrdered, OrderStatus.PROCESSING);
    }
}
