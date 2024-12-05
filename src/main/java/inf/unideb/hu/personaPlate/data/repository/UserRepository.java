package inf.unideb.hu.personaPlate.data.repository;

import inf.unideb.hu.personaPlate.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
    boolean existsById(Long userId);
}
