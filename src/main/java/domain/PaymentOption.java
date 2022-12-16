package domain;

import java.util.Arrays;

import static constant.SystemConstant.ERROR_PREFIX;

public enum PaymentOption {

    CREDIT_CARD(1),
    CASH(2);

    private final int command;

    PaymentOption(int command) {
        this.command = command;
    }

    public static PaymentOption from(int command) {
        return Arrays.stream(PaymentOption.values())
                .filter(paymentOption -> paymentOption.command == command)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_PREFIX + "존재하지 않는 기능입니다."));
    }
}
