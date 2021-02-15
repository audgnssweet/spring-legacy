package com.audgnssweet.service;

import com.audgnssweet.dto.User;
import com.audgnssweet.dto.UserRole;
import java.util.List;

public interface UserDbService {

    User getUser(String username);

    List<UserRole> getUserRoles(String username);

}
