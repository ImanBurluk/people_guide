package ru.iman_burlyq.chatgpt.basic_сollections.people_guide;

import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@NoArgsConstructor
public class DirectoryService {
    Map<Integer, Person> people = new HashMap<>();
    Integer id = 0;

    public String addPeople() {
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
        if(this.people.containsValue(person)){
            return "Такой пользователь не может быть добавлен, т.к. уже существует в системе";
        };

        this.people.put(person.getId(), person);
        return "Персона\n" +
                this.people.get(person.getId()).toString() +
                "\nбыла успешно добавлена в справочник";
    }

    public String createPerson(String name, String secondName,
                                String surname, String phone,String email){
        if(name.isEmpty() || secondName.isEmpty() || surname.isEmpty()
                || email.isEmpty() || phone.isEmpty()){
            return "Вы ввели не все требуемые данные для создания пользователя";
        }

        try{
            Person newPerson = new Person(name, secondName, surname, ++id, phone, email);
            return savePerson(newPerson);
        } catch (Exception e){
            return e.getMessage();
        }
    }

    public String deletePerson(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Чтобы удалить персону, укажите его идентификатор:");
        Integer idDeletePerson = Integer.parseInt(sc.nextLine());
        if(this.people.containsKey(idDeletePerson)){
            return this.people.remove(idDeletePerson).toString();
        }
        return "Пользователь с ID" + idDeletePerson + "не найден";
    }

    public void getAllPerson() {
        System.out.println("Список всех персон содержащихся в справочнике:");
        this.people.forEach((key, value) -> {
            System.out.println(value.toString());
        });
    }

    public String updatePerson() {
        return "Изменить человека по ID";
    }

    public String searchPerson() {
        return "Найти по имени (частичное совпадение)";
    }
}
