package com.myorganisation.nexify.controller;

import com.myorganisation.nexify.dto.request.UserRequestDto;
import com.myorganisation.nexify.dto.response.GenericResponseDto;
import com.myorganisation.nexify.dto.response.UserResponseDto;
import com.myorganisation.nexify.enums.Gender;
import com.myorganisation.nexify.model.User;
import com.myorganisation.nexify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRequestDto userRequestDto) {
        return new ResponseEntity<>(userService.registerUser(userRequestDto), HttpStatusCode.valueOf(201));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatusCode.valueOf(200));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatusCode.valueOf(200));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {
        return new ResponseEntity<>(userService.updateUser(id, userRequestDto), HttpStatusCode.valueOf(200));
    }

    @DeleteMapping
    public ResponseEntity<GenericResponseDto> removeUser(@RequestParam Long id) {
        return new ResponseEntity<>(userService.removeUser(id), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/search/username/{username}")
    public ResponseEntity<UserResponseDto> searchUserByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userService.searchUserByUsername(username), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/search/name/{name}")
    public ResponseEntity<List<UserResponseDto>> searchUserByName(@PathVariable String name) {
        return  new ResponseEntity<>(userService.getUsersByName(name), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/search/global")
    public ResponseEntity<List<UserResponseDto>> searchGlobalUser(@RequestParam String q) {
        return new ResponseEntity<>(userService.searchByUsernameContaining(q), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/search/name/{name}/gender/{gender}")
    public ResponseEntity<List<UserResponseDto>> searchUserByNameAndGender(@PathVariable String name, @PathVariable Gender gender, @RequestParam String type) {
        return new ResponseEntity<>(userService.searchByNameAndGender(name, gender, type), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<UserResponseDto>> getUserPage(
            @RequestParam(defaultValue = "0") Integer pageIndex,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        return new ResponseEntity<>(userService.getUserPage(pageIndex, pageSize, sortBy, sortOrder), HttpStatusCode.valueOf(200));
    }

}
