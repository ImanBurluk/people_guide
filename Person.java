package ru.iman_burlyq.chatgpt.basic_сollections.people_guide;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Person {
    public final String name;
    public final String secondName;
    public final String surname;
    public final Long id;
    public final short phone;
    public final String email;

}
