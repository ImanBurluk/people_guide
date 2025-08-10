package ru.iman_burlyq.chatgpt.basic_сollections.people_guide;

import lombok.NoArgsConstructor;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@NoArgsConstructor
public class DirectoryService {
    Logger logger = Logger.getLogger(DirectoryService.class.getName());
    Map<Integer, Person> people = new HashMap<>();
    Integer id = 0;

    public String addPerson() {
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
            return "Персона " +
                    this.people.remove(idDeletePerson).toString()
                    + "\nбыла удалена из справочника!\n";
        }
        return "Пользователь с ID" + idDeletePerson + "не найден";
    }

    public void getAllPerson() {
        if (people.isEmpty()) {
            System.out.println("Справочник пуст❗\n");
            return;
        }
        System.out.println("🧧Список всех персон содержащихся в справочнике:");
        this.people.entrySet().stream()
                .map(Map.Entry::getValue)
                .sorted(new PhoneComparator())
                .forEach(person ->  System.out.println(person.toString()));
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
        Scanner sc = new Scanner(System.in);
        System.out.println("Для поиска персоны укажите часть ФИО:");
        String searchItem = sc.nextLine();

        List<Person> result = this.people.entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(person -> person.fullName().toLowerCase().contains(searchItem.toLowerCase()))
                .toList();

        if (result.isEmpty()) {
            return "В справочнике не найдена персона с такими данными!\n\n";
        } else {
            return result.toString();
        }
    }


    public String saveCsv() {
        String currentDir = System.getProperty("user.dir");
        System.out.println(currentDir);
        String fileName = "personOutput.csv";
        String fullPath = currentDir + File.separator + fileName;
        try(PrintWriter writer = new PrintWriter(new FileWriter(fullPath))) {
            writer.println("ID,Имя,Отчество,Фамилия,Телефон,Email");
            for(Person person : this.people.values()) {
                String line = String.format(
                        "%d,%s,%s,%s,%s,%s",
                        person.getId(),
                        person.getName(),
                        person.getSecondName(),
                        person.getSurname(),
                        person.getPhone(),
                        person.getEmail()
                );
                writer.println(line);
            }
        } catch (IOException e){
            System.err.println("Ошибка при сохранении в файл: " + e.getMessage());
        }
        return "Данные успешно сохранены в файл " + fileName;
    }

    public String loadFromCsv() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Укажите полный путь к файлу");
        String absolutePath = sc.nextLine();

        if(absolutePath.isEmpty()){
            System.out.println("Укажите полный путь к файлу!");
        }

        try {
            List<Person> newPerson = Files.lines(Paths.get(absolutePath))
                    .skip(1)
                    .filter(line -> !line.trim().isEmpty())
                    .map(this::lineToPerson)
                    .filter(Objects::nonNull)
                    .toList();

            for(Person person : newPerson){
                people.put(++id, person);
            }

        } catch (IOException e){
            System.err.println(e.getMessage());
        }

        return "Данные были успешно загружены!\n";
    }

    static class PhoneComparator implements Comparator<Person>{

        public int compare(Person a, Person b){

            return a.getSurname().toLowerCase().compareTo(b.getSurname().toLowerCase());
        }
    }

    private Person lineToPerson(String line){
        try {
           String[] parts = line.split(",", -1);
           return new Person(parts[1],parts[2],parts[3],
                   Integer.parseInt(parts[0]),parts[4],parts[5]);
        } catch(Exception e){
            return null;
        }
    }
}
