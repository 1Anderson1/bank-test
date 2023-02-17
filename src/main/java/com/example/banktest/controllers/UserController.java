package com.example.banktest.controllers;

import com.example.banktest.entity.User;
import com.example.banktest.mappers.EntityMapper;
import com.example.banktest.models.responses.UserResponseDto;
import com.example.banktest.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "User info management")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final EntityMapper entityMapper;

    @GetMapping()
    public List<UserResponseDto> findUsers(Pageable pageable, @RequestParam("searchString") String searchString) {
        List<User> usersPage = userService.findUsersBySearchString(searchString, pageable);
        return entityMapper.toUserResponseDtoList(usersPage);
    }

}
