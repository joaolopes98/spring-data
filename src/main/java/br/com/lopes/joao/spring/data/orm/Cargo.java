package br.com.lopes.joao.spring.data.orm;

import javax.persistence.*;

@Entity
@Table(name = "cargos")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    public Cargo() {
    }

    public Cargo(String description) {
        this.description = description;
    }

    public Cargo(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
