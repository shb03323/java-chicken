package domain;

import java.util.Arrays;

import static constant.SystemConstant.ERROR_PREFIX;

public enum MainOption {

    ORDER(1),
    PAYMENT(2),
    QUIT(3);

    private final int command;

    MainOption(int command) {
        this.command = command;
    }

    public static MainOption from(int command) {
        return Arrays.stream(MainOption.values())
                .filter(mainOption -> mainOption.command == command)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_PREFIX + "존재하지 않는 기능입니다."));
    }
}
