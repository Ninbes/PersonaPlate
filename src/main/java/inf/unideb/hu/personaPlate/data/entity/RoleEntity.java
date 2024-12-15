package inf.unideb.hu.personaPlate.data.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Role")
public class RoleEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users;

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

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
