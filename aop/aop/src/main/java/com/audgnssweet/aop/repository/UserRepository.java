package com.audgnssweet.aop.repository;

import com.audgnssweet.aop.dto.UserJoinDto;
import com.audgnssweet.aop.dto.UserUpdateDto;
import com.audgnssweet.aop.model.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "jeong", "123", "010-1111-1111"));
        users.add(new User(2, "kim", "123", "010-1111-2222"));
        users.add(new User(3, "park", "123", "010-1111-3333"));
        return users;
    }

    public User findById(Integer id) {
        return new User(id, "new", "123", "010-new-new");
    }

    public User save(UserJoinDto userJoinDto) {
        return User.builder()
            .id(10)
            .username(userJoinDto.getUsername())
            .password(userJoinDto.getPassword())
            .phone(userJoinDto.getPhone())
            .build();
    }

    public String deleteById(Integer id) {
        System.out.println("DB에 삭제요청");
        return "OK";
    }

    public String updateById(Integer id, UserUpdateDto userUpdateDto) {
        System.out.println("DB에 업데이트 요청");
        return "OK";
    }
}
