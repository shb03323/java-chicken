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

    private void validateMenuCount(Menu menu, int count) {
        if (tableOrders.get(menu) + count > MAX_ORDER_COUNT) {
            throw new IllegalArgumentException(ERROR_PREFIX + "한 메뉴를 100개 이상 주문할 수 없습니다.");
        }
    }
}
