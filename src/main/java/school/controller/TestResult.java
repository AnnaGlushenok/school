package school.controller;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.model.Student;
import school.model.Test;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "test_result")
public class TestResult {
    @Id
    @GeneratedValue
    private int id;

//    @ManyToOne
//    @JoinColumn(name = "student_id")
//    private Student student;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    private int mark;
    private String date;
    private String startTime, endTime;
}
