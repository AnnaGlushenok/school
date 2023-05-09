package school.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.controller.TestResult;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    //    @Pattern(regexp = "^([1-9]|1[0-1])$")
    private int classId;

    //    @NotBlank(message = "Surname is required")
//    @Pattern(regexp = "^[А-Яа-я]+$")
    private String surname;

    //    @NotBlank(message = "Name is required")
//    @Pattern(regexp = "^[А-Яа-я]+$")
    private String name;

    //    @NotBlank(message = "Name is required")
//    @Pattern(regexp = "^[А-Яа-я]+$")
    private String patronymic;

    //    @Email
    private String email;

    //    @NotBlank(message = "Name is required")
//    @Pattern(regexp = "^[A-Za-z(\\d+)?]{4,}")
    private String login;

    //    @NotBlank(message = "Name is required")
//    @Pattern(regexp = "^[A-Za-z(\\d+!@#$%^&*:;)?]{6,}")
    private String password;

    //   @Enumerated(EnumType.STRING)
    private String role;

//    @OneToMany(mappedBy = "student")
//    private Set<TestResult> test;
}
