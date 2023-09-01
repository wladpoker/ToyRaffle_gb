package UI.Core;

import java.util.Scanner;

public class App {
    public static void Start_Programm() {
        Presenter presenter = new Presenter(new ConsoleView(), Config.pathDb);
        presenter.loadFromFile();

        String command;

        while (true) {

            command = prompt("""

                     1 - Добавить игрушку к розыгрышу
                     2 - Удалить игрушку из розыгрыша
                     3 - Розыгрыш игрушек
                     4 - Покажите игрушки участвующие в розыгрыше
                     5 - Очистить все записи игрушек
                     6 - Сохраните все записи игрушек в файл
                     7 - Загрузите все записи игрушек из файла
                     8 - Выход
                    Сделайте свой выбор:\s""");
            if (command.equals("8")) {
                return;
            }
            try {
                switch (command) {
                    case "1" -> presenter.putForRaffle();
                    case "2" -> presenter.deleteFromRaffle();
                    case "3" -> presenter.getRaffle();
                    case "4" -> presenter.showAll();
                    case "5" -> presenter.clearAll();
                    case "6" -> presenter.saveToFile();
                    case "7" -> presenter.loadFromFile();
                    default -> System.out.println("\n Команда не найдена!");
                }
            } catch (Exception e) {
                System.out.println("Error. " + e.getMessage());
            }
        }
    }

    private static String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private static Toy toyCreate() {
        int id = Integer.parseInt(prompt("Идентификатор игрушки: "));
        String name = prompt("Название игрушки: ");
        String weight = prompt("Вес игрушки: ");
        return (new Toy(id, name, Integer.parseInt(weight)));
    }
}

