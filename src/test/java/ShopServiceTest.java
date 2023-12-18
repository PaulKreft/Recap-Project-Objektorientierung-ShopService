import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void addOrderTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")));
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());
    }

    @Test
    void addOrderTest_whenInvalidProductId_expectNull() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("hallo world");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        assertNull(actual);
    }

    @Test
    void getOrdersByStatusTest_WhenFilterByProcessing_ReturnOneOrder() {
        // GIVEN
        ShopService shopService = new ShopService();

        List<String> productIds = List.of("1", "2");
        shopService.addOrder(productIds);

        // WHEN
        int actual = shopService.getOrdersByStatus(OrderStatus.PROCESSING).size();

        // THEN
        assertEquals(1, actual);
    }

    @Test
    void getOrdersByStatusTest_WhenFilterByCompleted_ReturnEmptyList() {
        // GIVEN
        ShopService shopService = new ShopService();

        List<String> productIds = List.of("1", "2");
        shopService.addOrder(productIds);

        // WHEN
        List<Order> actual = shopService.getOrdersByStatus(OrderStatus.COMPLETED);

        // THEN
        assertTrue(actual.isEmpty());
    }
}
