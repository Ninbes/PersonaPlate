package inf.unideb.hu.personaPlate.service;

import inf.unideb.hu.personaPlate.service.dto.LoginDto;
import inf.unideb.hu.personaPlate.service.dto.RegistrationDto;

public interface AuthenticationService {
    String login(LoginDto dto);

    String registration(RegistrationDto dto);
}
