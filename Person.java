package ru.iman_burlyq.chatgpt.basic_сollections.people_guide;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
public class Person {
    public final String name;
    public final String secondName;
    public final String surname;
    public final Integer id;
    public final String phone;
    public final String email;

    public Person(String name, String secondName, String surname, Integer id, String phone, String email) {
        this.name = Objects.requireNonNull(name.toLowerCase(),"Не указано имя персоны") ;
        this.secondName = Objects.requireNonNull(secondName.toLowerCase(),"Не указано отчество персоны") ;
        this.surname = Objects.requireNonNull(surname.toLowerCase(),"Не указана фамилия персоны") ;
        this.id = id;
        this.phone = Objects.requireNonNull(phone.replaceAll("\\D","").replaceAll("^7","8"),"Не указано телефон персоны");
        this.email = Objects.requireNonNull(email.matches("^[\\w-.]+@[\\w-]+(\\.[\\w-]+)*\\.[a-z]{2,}$") ? email : null,"Не указан email персоны");
//        this.email = Objects.requireNonNull(email,"requireNonNull");
    }

    public String fullName(){
        return surname.substring(0, 1).toUpperCase() + surname.substring(1)
                + " " +
                name.substring(0, 1).toUpperCase() + name.substring(1)
                + " " +
                secondName.substring(0, 1).toUpperCase() + secondName.substring(1);
    }

    @Override
    public String toString(){
        return " 🌐ID-" + id +
                "; 😎ФИО: " + fullName()
                + " [номер телефона: " + phone
                + "; email: " + email + "]";
    }

}
