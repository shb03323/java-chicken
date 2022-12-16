package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static constant.SystemConstant.ERROR_PREFIX;
import static constant.SystemConstant.MAX_ORDER_COUNT;

public class Order {

    private final Map<Menu, Integer> tableOrders;

    public Order() {
        tableOrders = new LinkedHashMap<>();
    }

    public void orderMenu(Menu menu, int count) {
        validateMenuCount(menu, count);
        tableOrders.put(menu, tableOrders.getOrDefault(menu, 0) + count);
    }

    public List<String> getTableOrders() {
        return tableOrders.keySet().stream()
                .map(menu -> menu.getName() + " " + tableOrders.get(menu) + " " + menu.getPrice())
                .collect(Collectors.toUnmodifiableList());
    }

    public int getTotalAmount() {
        return getEachMenuPayment().stream()
                .mapToInt(eachPayment -> eachPayment)
                .sum();
    }

    public int getDiscount() {
        return getEachMenuCount().stream()
                .mapToInt(menuCount -> menuCount / 10)
                .sum() * 10000;
    }

    private List<Integer> getEachMenuPayment() {
        return tableOrders.keySet().stream()
                .map(menu -> menu.getPrice() * tableOrders.get(menu))
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Integer> getEachMenuCount() {
        return tableOrders.keySet().stream()
                .map(tableOrders::get)
                .collect(Collectors.toUnmodifiableList());
    }

    private void validateMenuCount(Menu menu, int count) {
        if (!tableOrders.containsKey(menu)) {
            if (count > MAX_ORDER_COUNT) {
                throw new IllegalArgumentException(ERROR_PREFIX + "100개 이상 주문할 수 없습니다.");
            }
        } else if (tableOrders.get(menu) + count > MAX_ORDER_COUNT) {
            throw new IllegalArgumentException(ERROR_PREFIX + "한 메뉴는 99개까지만 주문할 수 있습니다.");
        }
    }
}
