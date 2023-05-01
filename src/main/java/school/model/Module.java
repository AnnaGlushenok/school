package school.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "modules")
public class Module {
    @Id
    @GeneratedValue
    private int id;
    private int subjectId;
    private int classId;
    private int testId;
    private String name;
    private String lessons;
//    private String сontrol;
//    private String tasks;
}

