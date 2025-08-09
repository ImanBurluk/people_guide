package ru.iman_burlyq.chatgpt.basic_сollections.people_guide;

import java.util.Scanner;

public class Main {

    static public void getMenu() {
        System.out.println("""
                    ================= МЕНЮ =================
                    1. Добавить человека
                    2. Удалить человека по ID
                    3. Изменить человека по ID
                    4. Найти по имени (частичное совпадение)
                    5. Показать весь список
                    0. Выход
                    ========================================
                          Выберите с помощью клавиш цифр
                                 нужное действие!
                    """);
    }

    public static void main(String[] args) {
        DirectoryService directoryService = new DirectoryService();
        System.out.println("""
                           Добро пожаловать в справочник людей!
                ✅Выберите с помощью клавиши цифр интересующий Вас функционал:
                                             🙏
                """);
        getMenu();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String choice = sc.nextLine();
            switch (choice) {
                case "1": {
                    System.out.println(directoryService.addPerson());
                    getMenu();
                    continue;
                }
                case "2": {
                    System.out.println(directoryService.deletePerson());
                    getMenu();
                    continue;
                }
                case "3": {
                    System.out.println(directoryService.updatePerson());
                    getMenu();
                    continue;
                }
                case "4": {
                    System.out.println(directoryService.searchPerson());
                    getMenu();
                    continue;
                }
                case "5":{
                    directoryService.getAllPerson();
                    getMenu();
                    continue;
                }
                case "6":{
                    System.out.println(directoryService.saveCsv());;
                    getMenu();
                    continue;
                }
                case "7":{
                    System.out.println(directoryService.loadFromCsv());;
                    getMenu();
                    continue;
                }

                case "0": break;
                default: {
                    System.out.println("""
                                    ⚠️Неизвестное значение
                            Пожалуйста, введите значение от 0 до 7!
                                              🎯
                            """);
                    continue;
                }
            }
        }
    }
}
