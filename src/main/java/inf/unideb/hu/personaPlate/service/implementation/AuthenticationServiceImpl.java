package inf.unideb.hu.personaPlate.service.implementation;

import inf.unideb.hu.personaPlate.data.entity.RoleEntity;
import inf.unideb.hu.personaPlate.data.entity.UserEntity;
import inf.unideb.hu.personaPlate.data.repository.RoleRepository;
import inf.unideb.hu.personaPlate.data.repository.UserRepository;
import inf.unideb.hu.personaPlate.service.AuthenticationService;
import inf.unideb.hu.personaPlate.service.JasonWebTokenService;
import inf.unideb.hu.personaPlate.service.dto.LoginDto;
import inf.unideb.hu.personaPlate.service.dto.RegistrationDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JasonWebTokenService jasonWebTokenService;


    @Override
    public String login(LoginDto dto) {
        UserEntity userEntity = modelMapper.map(dto, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        RoleEntity roleEntity = roleRepository.findByName("ROLE_USER");
        if (roleEntity != null) {
            userEntity.setRoles(Set.of(roleEntity));
        }else {
            roleEntity = new RoleEntity(null, "ROLE_USER");
            roleEntity = roleRepository.save(roleEntity);
            userEntity.setRoles(Set.of(roleEntity));
        }

        userEntity = userRepository.save(userEntity);

        return jasonWebTokenService.generateToken(userEntity);
    }

    @Override
    public String registration(RegistrationDto dto) {
        return "";
    }
}