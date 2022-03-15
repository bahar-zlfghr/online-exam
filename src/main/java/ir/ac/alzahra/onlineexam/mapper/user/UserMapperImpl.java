package ir.ac.alzahra.onlineexam.mapper.user;

import ir.ac.alzahra.onlineexam.dto.UserDto;
import ir.ac.alzahra.onlineexam.model.data.Role;
import ir.ac.alzahra.onlineexam.model.data.User;
import ir.ac.alzahra.onlineexam.model.repository.RoleRepository;

import java.util.Optional;

/**
 * @author : Bahar Zolfaghari
 **/
public class UserMapperImpl implements UserMapper {
    private final RoleRepository roleRepository;

    public UserMapperImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public User toUSer(UserDto userDto) {
        Optional<Role> role = roleRepository.findById(userDto.getRole());
        return new User()
                .setFirstName(userDto.getFirstName())
                .setLastName(userDto.getLastName())
                .setEmail(userDto.getEmail())
                .setPassword(userDto.getPassword())
                .addRole(role.orElse(null));
    }

    @Override
    public UserDto toUSerDto(User user) {
        return new UserDto()
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setEmail(user.getEmail())
                .setPassword(user.getPassword());
    }
}
