package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OrderRepository {

    private static final Map<Table, Order> orders = new LinkedHashMap<>();

    static {
        TableRepository.tables()
                .forEach(table -> orders.put(table, new Order()));
    }

    public static List<String> getOrderMenus(Table table) {
        return orders.get(table).getTableOrders();
    }

    public static void order(Table table, Menu menu, int count) {
        orders.get(table).orderMenu(menu, count);
    }

    public static int getResult(Table table, PaymentOption paymentOption) {
        Order order = orders.get(table);
        int totalAmount = order.getTotalAmount();
        if (paymentOption.equals(PaymentOption.CASH)) {
            totalAmount *= 0.95;
        }
        totalAmount -= order.getDiscount();
        return totalAmount;
    }
}
