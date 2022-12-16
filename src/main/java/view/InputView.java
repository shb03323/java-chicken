package view;

import java.util.Scanner;

import static constant.SystemConstant.ERROR_PREFIX;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int selectOption() {
        System.out.println("## 원하는 기능을 선택하세요.");
        String input = scanner.next();
        validateIsNumber(input);
        System.out.println();
        return Integer.parseInt(input);
    }

    public static int inputTableNumber() {
        System.out.println("## 테이블을 선택하세요.");
        return inputNumber();
    }

    public static int inputMenuNumber() {
        System.out.println("## 등록할 메뉴를 선택하세요.");
        return inputNumber();
    }

    public static int inputMenuCount() {
        System.out.println("## 메뉴의 수량을 입력하세요.");
        return inputNumber();
    }

    private static int inputNumber() {
        String input = scanner.next();
        validateIsNumber(input);
        System.out.println();
        return Integer.parseInt(input);
    }

    private static void validateIsNumber(String input) {
        if (!isNumeric(input)) {
            throw new IllegalArgumentException(ERROR_PREFIX + "문자가 아닌 숫자를 입력해주세요.");
        }
    }

    private static boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
