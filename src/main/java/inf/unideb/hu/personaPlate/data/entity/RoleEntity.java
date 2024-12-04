package inf.unideb.hu.personaPlate.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Role")
public class RoleEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String name;

    public RoleEntity() {}

    public RoleEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
