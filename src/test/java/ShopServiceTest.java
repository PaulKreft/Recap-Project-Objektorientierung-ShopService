import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void addOrderTest() {
        //GIVEN
        ZonedDateTime now = ZonedDateTime.now();

        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")), now);
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());
    }

    @Test
    void addOrderTest_whenInvalidProductId_thenExpectNoSuchElementException() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("hallo world");


        assertThrows(
                // THEN
                NoSuchElementException.class,
                //WHEN
                () -> shopService.addOrder(productsIds)
        );
    }

    @Test
    void getOrdersByStatusTest_whenFilterByProcessing_thenReturnOneOrder() {
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
    void getOrdersByStatusTest_whenFilterByCompleted_thenReturnEmptyList() {
        // GIVEN
        ShopService shopService = new ShopService();

        List<String> productIds = List.of("1", "2");
        shopService.addOrder(productIds);

        // WHEN
        List<Order> actual = shopService.getOrdersByStatus(OrderStatus.COMPLETED);

        // THEN
        assertTrue(actual.isEmpty());
    }

    @Test
    void updateOrderTest_whenUpdateOrderToCompleted_thenShowOrderAsCompleted() {
        // GIVEN
        ShopService shopService = new ShopService();

        List<String> productIds = List.of("1", "2");
        Order orderToUpdate = shopService.addOrder(productIds);

        // WHEN
        shopService.updateOrder(orderToUpdate.id(), OrderStatus.COMPLETED);

        Order updatedOrder = shopService.getOrderById(orderToUpdate.id());

        // THEN
        assertSame(updatedOrder.status(), OrderStatus.COMPLETED);
    }

    @Test
    void updateOrderTest_whenUpdateOrderWithInvalidIdToCompleted_thenReturnNull() {
        // GIVEN
        ShopService shopService = new ShopService();

        List<String> productIds = List.of("1", "2");
        shopService.addOrder(productIds);

        // WHEN
        Order updatedOrder = shopService.updateOrder("orderToUpdate.id()", OrderStatus.COMPLETED);

        // THEN
        assertNull(updatedOrder);
    }

    @Test
    void getOrderByIdTest_whenValidId_thenReturnOrder() {
        // GIVEN
        ShopService shopService = new ShopService();

        List<String> productIds = List.of("1", "2");
        Order orderToUpdate = shopService.addOrder(productIds);

        // WHEN
        Order order = shopService.getOrderById(orderToUpdate.id());

        // THEN
        assertEquals(orderToUpdate, order);
    }

    @Test
    void getOrderByIdTest_whenInvalidId_thenReturnNull() {
        // GIVEN
        ShopService shopService = new ShopService();

        // WHEN
        Order order = shopService.updateOrder("orderToUpdate.id()", OrderStatus.COMPLETED);

        // THEN
        assertNull(order);
    }
}