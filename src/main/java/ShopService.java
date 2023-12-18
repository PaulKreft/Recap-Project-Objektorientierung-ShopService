import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public class ShopService {
    private final ProductRepo productRepo = new ProductRepo();
    private final OrderRepo orderRepo = new OrderMapRepo();

    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Optional<Product> productToOrder = productRepo.getProductById(productId);
            if (productToOrder.isEmpty()) {
                System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
                throw new NoSuchElementException();
            }
            products.add(productToOrder.get());
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products);

        return orderRepo.addOrder(newOrder);
    }

    public List<Order> getOrdersByStatus(OrderStatus status) {
        List<Order> orders = orderRepo.getOrders();

        return orders.stream().filter(order -> order.status() == status).toList();
    }

    public Order getOrderById(String id) {
        return orderRepo.getOrderById(id);
    }

    public Order updateOrder(String id, OrderStatus status) {
        Order order = orderRepo.getOrderById(id);

        if(order == null) {
            System.out.println("Order with id " + id + " could not be updated.");
            return null;
        }

        Order updatedOrder = order.withStatus(status);

        orderRepo.addOrder(updatedOrder);
        return updatedOrder;
    }
}
