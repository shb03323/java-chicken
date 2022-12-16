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
            int command = InputView.selectOption();
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
        runOptions.put(MainOption.ORDER, MainMenuController::order);
        runOptions.put(MainOption.PAYMENT, MainMenuController::pay);
        runOptions.put(MainOption.QUIT, MainMenuController::quit);
    }

    private static void order() {
        int tableNumber = selectTable();
        Table table = TableRepository.findByNumber(tableNumber);
        int menuNumber = selectMenu();
        Menu menu = MenuRepository.findByNumber(menuNumber);
        int menuCount = inputMenuCount();
        OrderRepository.order(table, menu, menuCount);
    }

    private static void pay() {

    }

    private static void quit() {}

    private static int selectTable() {
        final List<Table> tables = TableRepository.tables();
        OutputView.printTables(tables);
        return InputView.inputTableNumber();
    }

    private static int selectMenu() {
        final List<Menu> menus = MenuRepository.menus();
        OutputView.printMenus(menus);
        return InputView.inputMenuNumber();
    }

    private static int inputMenuCount() {
        return InputView.inputMenuCount();
    }
}
