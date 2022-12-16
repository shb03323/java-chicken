package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class MainMenuController {

    private final Map<MainOption, Runnable> runOptions;

    public MainMenuController() {
        this.runOptions = new EnumMap<>(MainOption.class);
        initControllers();
    }

    public void run() {
        OutputView.printMainMenu();
        MainOption selection = selectOption();
        while (!selection.equals(MainOption.QUIT)) {
            progress(selection);
            OutputView.printMainMenu();
            selection = selectOption();
        }
    }

    private MainOption selectOption() {
        try {
            final int command = InputView.selectOption();
            return MainOption.from(command);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return selectOption();
        }
    }

    private void progress(MainOption selection) {
        try {
            runOptions.get(selection).run();
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            progress(selection);
        }

    }

    private void initControllers() {
        runOptions.put(MainOption.ORDER, this::order);
        runOptions.put(MainOption.PAYMENT, this::pay);
        runOptions.put(MainOption.QUIT, this::quit);
    }

    private void order() {
        final Table table = selectTable();
        final Menu menu = selectMenu();
        final int menuCount = inputMenuCount();
        OrderRepository.order(table, menu, menuCount);
    }

    private void pay() {
        final Table table = selectTable();
        showOrders(table);
        PaymentOption paymentOption = selectPaymentOption();
        int totalAmount = OrderRepository.getResult(table, paymentOption);
        OutputView.printTotalAmount(totalAmount);
    }

    private void quit() {}

    private Table selectTable() {
        final List<Table> tables = TableRepository.tables();
        OutputView.printTables(tables);
        final int tableNumber = InputView.inputTableNumber();
        return TableRepository.findByNumber(tableNumber);
    }

    private Menu selectMenu() {
        final List<Menu> menus = MenuRepository.menus();
        OutputView.printMenus(menus);
        final int menuNumber = InputView.inputMenuNumber();
        return MenuRepository.findByNumber(menuNumber);
    }

    private int inputMenuCount() {
        return InputView.inputMenuCount();
    }

    private void showOrders(Table table) {
        final List<String> orders = OrderRepository.getOrderMenus(table);
        OutputView.printOrders(orders);
    }

    private PaymentOption selectPaymentOption() {
        try {
            final int command = InputView.inputPaymentOption();
            return PaymentOption.from(command);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return selectPaymentOption();
        }
    }
}
