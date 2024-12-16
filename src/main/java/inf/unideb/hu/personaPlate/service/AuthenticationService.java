package inf.unideb.hu.personaPlate.service;

import inf.unideb.hu.personaPlate.service.dto.LoginDto;
import inf.unideb.hu.personaPlate.service.dto.RegistrationDto;
import inf.unideb.hu.personaPlate.service.dto.UpdateUserDto;

public interface AuthenticationService {
    String login(LoginDto dto);

    void updateUserAccount(Long userId, UpdateUserDto dto, String currentEmail);

    String registration(RegistrationDto dto);

    void deleteUser(Long userId);
}
