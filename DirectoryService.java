package ru.iman_burlyq.chatgpt.basic_сollections.people_guide;

import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

@NoArgsConstructor
public class DirectoryService {
    Logger logger = Logger.getLogger(DirectoryService.class.getName());
    Map<Integer, Person> people = new HashMap<>();
    Integer id = 0;

    public String addPersone() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Для добавления пользователя необходимо ввести следующие данные:");
        System.out.println("Введите имя:");
        String name = sc.nextLine();
        System.out.println("Введите отчество:");
        String secondName = sc.nextLine();
        System.out.println("Введите фамилию:");
        String surname = sc.nextLine();
        System.out.println("Укажите номер телефона:");
        String phone = sc.nextLine();
        System.out.println("Укажите email:");
        String email = sc.nextLine();
        return createPerson(name, secondName, surname, phone, email);

    }

    public String savePerson(Person person) {
        if (this.people.containsValue(person)) {
            return "Такой пользователь не может быть добавлен, т.к. уже существует в системе";
        }
        ;

        this.people.put(person.getId(), person);
        return "Персона\n" + this.people.get(person.getId()).toString() + "\n        была успешно добавлена в справочник!\n\n";
    }

    public String createPerson(String name, String secondName, String surname, String phone, String email) {
        if (name.isEmpty() || secondName.isEmpty() || surname.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            return "Вы ввели не все требуемые данные для создания пользователя";
        }

        try {
            Person newPerson = new Person(name, secondName, surname, ++id, phone, email);
            return savePerson(newPerson);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String deletePerson() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Чтобы удалить персону, укажите его идентификатор:");
        Integer idDeletePerson = Integer.parseInt(sc.nextLine());
        if (this.people.containsKey(idDeletePerson)) {
            return this.people.remove(idDeletePerson).toString();
        }
        return "Пользователь с ID" + idDeletePerson + "не найден";
    }

    public void getAllPerson() {
        System.out.println("Список всех персон содержащихся в справочнике:");
        this.people.forEach((key, value) -> {
            System.out.println(value.toString());
        });
        System.out.println("===============================================\n");
    }

    public String updatePerson() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Для изменения данных персоны необходимо указать его ID:");
        try {
            Integer idUpdatePerson = Integer.parseInt(sc.nextLine());
            if (this.people.containsKey(idUpdatePerson)) {

                Person currentPerson = this.people.get(idUpdatePerson);

                System.out.println("Введите обновленное имя (или пропустите шаг нажатием ENTER):");
                String input = sc.nextLine();
                String name = input.isEmpty() ? currentPerson.getName() : input;

                System.out.println("Введите обновленное отчество (или пропустите шаг нажатием ENTER):");
                input = sc.nextLine();
                String secondName = input.isEmpty() ? currentPerson.getSecondName() : input;
                System.out.println("Введите обновленную фамилию (или пропустите шаг нажатием ENTER):");
                input = sc.nextLine();
                String surname = input.isEmpty() ? currentPerson.getSurname() : input;

                System.out.println("Укажите обновленный номер телефона (или пропустите шаг нажатием ENTER):");
                input = sc.nextLine();
                String phone = input.isEmpty() ? currentPerson.getPhone() : input;

                System.out.println("Укажите обновленный email (или пропустите шаг нажатием ENTER):");
                input = sc.nextLine();
                String email = input.isEmpty() ? currentPerson.getEmail() : input;

                // Создаем обновленного человека
                Person updatePerson = new Person(name, secondName, surname, idUpdatePerson, phone, email);
                this.people.put(idUpdatePerson, updatePerson);

                return "Данные были обновлены:\n" + updatePerson.toString();
            }
            return "Персоны с ID " + idUpdatePerson + " в справочнике не найдена!";
        } catch (NumberFormatException e) {
            return "Неверный формат ID!";
        }
    }

    public String searchPerson() {
        return "Найти по имени (частичное совпадение)";
    }
}
