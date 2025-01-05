package kz.muradaliev.charm.back.mapper;

import kz.muradaliev.charm.back.dto.RegistrationDto;
import kz.muradaliev.charm.back.model.Profile;
import kz.muradaliev.charm.back.model.Status;

public class RegistrationDtoToProfileMapper implements Mapper<RegistrationDto, Profile>{

    private static final RegistrationDtoToProfileMapper INSTANCE = new RegistrationDtoToProfileMapper();

    private RegistrationDtoToProfileMapper() {
    }

    public static RegistrationDtoToProfileMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Profile map(RegistrationDto dto) {
        return map(dto, new Profile());
    }

    @Override
    public Profile map(RegistrationDto dto, Profile profile) {
        profile.setEmail(dto.getEmail());
        profile.setPassword(dto.getPassword());
        profile.setStatus(Status.INACTIVE);
        return profile;
    }

}