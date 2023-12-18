import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    @Test
    void orderTest_whenCreated_ThenStatusProcessing() {
        // GIVEN
        List<Product> products = new ArrayList<>();
        products.add(new Product("1", "Product"));

        // WHEN
        Order newOrder = new Order("1", products);

        // THEN
        assertEquals(OrderStatus.PROCESSING, newOrder.status());
    }
    @Test
    void orderTest_whenCreated_ThenStatusNotInDelivery() {
        // GIVEN
        List<Product> products = new ArrayList<>();
        products.add(new Product("1", "Product"));

        // WHEN
        Order newOrder = new Order("1", products);

        // THEN
        assertNotEquals(OrderStatus.IN_DELIVERY, newOrder.status());
    }

}