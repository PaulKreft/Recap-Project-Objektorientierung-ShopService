import lombok.With;

import java.time.ZonedDateTime;
import java.util.List;

@With
public record Order(
        String id,
        List<Product> products,
        OrderStatus status,
        ZonedDateTime timestamp
) {

    public Order(String id, List<Product> products, ZonedDateTime timestamp) {
        this(id, products, OrderStatus.PROCESSING, timestamp);
    }
}
