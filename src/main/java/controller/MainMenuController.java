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
        runOptions.get(selection).run();
    }

    public void ifasfds() {
        final List<Table> tables = TableRepository.tables();
        OutputView.printTables(tables);

        final int tableNumber = InputView.inputTableNumber();

        final List<Menu> menus = MenuRepository.menus();
        OutputView.printMenus(menus);
    }

    private void initControllers() {
        runOptions.put(MainOption.ORDER, MainMenuController::order);
        runOptions.put(MainOption.PAYMENT, MainMenuController::pay);
        runOptions.put(MainOption.QUIT, MainMenuController::quit);
    }

    private static void order() {

    }

    private static void pay() {

    }

    private static void quit() {}
}
