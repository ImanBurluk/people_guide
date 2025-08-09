package ru.iman_burlyq.chatgpt.basic_сollections.people_guide;

import java.util.Scanner;

public class Main {

    static public void getMenu() {
        System.out.println("""
                            ==================МЕНЮ==================
                            1. Добавить человека
                            2. Удалить человека по ID
                            3. Изменить человека по ID
                            4. Найти по имени (частичное совпадение)
                            5. Показать весь список
                            0. Выход
                            ========================================
                    """);
    }

    public static void main(String[] args) {

        DirectoryService directoryService = new DirectoryService();

        Scanner sc = new Scanner(System.in);
        getMenu();
        while (sc.hasNextLine()) {
            String choice = sc.nextLine();
            switch (choice) {
                case "1": System.out.println(directoryService.addPeople());;
                case "0": getMenu();
                default: continue;
            }
        }
    }
}
