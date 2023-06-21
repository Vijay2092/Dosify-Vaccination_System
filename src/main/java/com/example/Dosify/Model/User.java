package com.example.Dosify.Model;

import com.example.Dosify.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.intellij.lang.annotations.Pattern;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "user")
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name")
    String name;

    @Column(name = "age")
    int age;

    @Column(name = "email_id",unique = true, nullable = false)
    String emailId;

    @Column(name = "mob_no" ,unique = true, nullable = false)
    String mobNo;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    Gender gender;

    @Column(name = "is_dose1_taken")
    boolean isDose1Taken;

    @Column(name = "is_dose2-taken")
    boolean isDose2Taken;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)// at intially dont have appointment it is empty list
    List<Appointment> appointments = new ArrayList<>();  // for connection one to many you need to use list

    @OneToOne(mappedBy =  "user", cascade = CascadeType.ALL)
    Dose1 dose1;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    Dose2 dose2;


}
