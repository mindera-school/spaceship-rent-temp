package com.mindera.school.spaceshiprent.converter;

import com.mindera.school.spaceshiprent.dto.auth.ValidLoginDto;
import com.mindera.school.spaceshiprent.dto.user.CreateOrUpdateUserDto;
import com.mindera.school.spaceshiprent.dto.user.UserDetailsDto;
import com.mindera.school.spaceshiprent.persistence.entity.UserEntity;
import com.mindera.school.spaceshiprent.security.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverter {

    private final BCryptPasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    public UserEntity convertToEntity(CreateOrUpdateUserDto dto) {
        return UserEntity.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .age(dto.getAge())
                .licenseNumber(dto.getLicenseNumber())
                .ssn(dto.getSsn())
                .planet(dto.getPlanet())
                .password(passwordEncoder.encode(dto.getPassword()))
                .userType(dto.getUserType())
                .build();
    }

    public UserDetailsDto convertToUserDetailsDto(UserEntity entity) {
        return UserDetailsDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .age(entity.getAge())
                .licenseNumber(entity.getLicenseNumber())
                .ssn(entity.getSsn())
                .planet(entity.getPlanet())
                .userType(entity.getUserType())
                .build();
    }

    public ValidLoginDto convertToValidLoginDto(UserEntity userEntity) {
        return ValidLoginDto.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .userType(userEntity.getUserType())
                .token(jwtUtil.generateToken(userEntity))
                .build();
    }
}
