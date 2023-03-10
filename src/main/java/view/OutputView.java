package view;

import domain.Menu;
import domain.Table;

import java.util.List;

public class OutputView {

    private static final String TOP_LINE = "┌ ─ ┐";
    private static final String TABLE_FORMAT = "| %s |";
    private static final String BOTTOM_LINE = "└ ─ ┘";

    public static void printErrorMessage(String message) {
        System.out.println(message + "\n");
    }

    public static void printMainMenu() {
        System.out.println("## 메인화면\n"
                + "1 - 주문등록\n"
                + "2 - 결제하기\n"
                + "3 - 프로그램 종료\n");
    }

    public static void printTables(final List<Table> tables) {
        System.out.println("## 테이블 목록");
        final int size = tables.size();
        printLine(TOP_LINE, size);
        printTableNumbers(tables);
        printLine(BOTTOM_LINE, size);
    }

    public static void printMenus(final List<Menu> menus) {
        for (final Menu menu : menus) {
            System.out.println(menu);
        }
    }

    public static void printOrders(List<String> orders) {
        System.out.println("## 주문 내역");
        System.out.println("메뉴 수량 금액");
        orders.forEach(System.out::println);
        System.out.println();
    }

    public static void printTotalAmount(int totalAmount) {
        System.out.println("## 최종 결제할 금액\n"
                + totalAmount + "원");
    }

    private static void printLine(final String line, final int count) {
        for (int index = 0; index < count; index++) {
            System.out.print(line);
        }
        System.out.println();
    }

    private static void printTableNumbers(final List<Table> tables) {
        for (final Table table : tables) {
            System.out.printf(TABLE_FORMAT, table);
        }
        System.out.println();
    }
}
