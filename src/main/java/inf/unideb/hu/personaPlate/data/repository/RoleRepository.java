package inf.unideb.hu.personaPlate.data.repository;

import inf.unideb.hu.personaPlate.data.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(String name);
}
