package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static constant.SystemConstant.ERROR_PREFIX;

public class TableRepository {
    private static final List<Table> tables = new ArrayList<>();

    static {
        tables.add(new Table(1));
        tables.add(new Table(2));
        tables.add(new Table(3));
        tables.add(new Table(5));
        tables.add(new Table(6));
        tables.add(new Table(8));
    }

    public static List<Table> tables() {
        return Collections.unmodifiableList(tables);
    }

    public static void validateTableExist(int tableNumber) {
        boolean isExist = tables.stream()
                .anyMatch(table -> table.isSameNumber(tableNumber));
        if (!isExist) {
            throw new IllegalArgumentException(ERROR_PREFIX + "존재하지 않는 테이블 번호입니다.");
        }
    }

    public static Table findByNumber(int number) {
        return tables.stream()
                .filter(table -> table.isSameNumber(number))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_PREFIX + "존재하지 않는 테이블 번호입니다."));
    }
}
