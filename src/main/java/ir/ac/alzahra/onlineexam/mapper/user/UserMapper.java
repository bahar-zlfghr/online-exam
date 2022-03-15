package ir.ac.alzahra.onlineexam.mapper.user;

import ir.ac.alzahra.onlineexam.dto.UserDto;
import ir.ac.alzahra.onlineexam.model.data.User;

/**
 * @author : Bahar Zolfaghari
 **/
public interface UserMapper {

    User toUSer(UserDto userDto);
    UserDto toUSerDto(User user);
}
