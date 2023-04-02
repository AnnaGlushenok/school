package school.model;

import jakarta.persistence.*;

@Entity(name = "classes")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class() {
    }

    public Class(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
