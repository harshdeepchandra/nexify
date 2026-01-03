package com.myorganisation.nexify.service;

import com.myorganisation.nexify.dto.request.UserRequestDto;
import com.myorganisation.nexify.dto.response.*;
import com.myorganisation.nexify.enums.Gender;
import com.myorganisation.nexify.model.MetaData;
import com.myorganisation.nexify.model.Profile;
import com.myorganisation.nexify.model.User;
import com.myorganisation.nexify.repository.MetaDataRepository;
import com.myorganisation.nexify.repository.ProfileRepository;
import com.myorganisation.nexify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private MetaDataRepository metaDataRepository;

    @Override
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        User user = mapUserRequestDtoToUser(userRequestDto, new User());

        Profile profile = new Profile();

//        profileRepository.save(profile);
        user.setProfile(profile);
        profile.setUser(user);

        MetaData metaData = new MetaData(); // Unsaved instance
//        metaData.setUser(user);
        metaDataRepository.save(metaData); // saved instance

        user.setMetaData(metaData);
        metaData.setUser(user);

        userRepository.save(user);

//        metaData.setUser(user);
//        metaDataRepository.save(metaData);

//        profile.setUser(user);
//        profileRepository.save(profile);

        return mapUserToUserResponseDto(user);
    }

    @Override
    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user != null) {
            return mapUserToUserResponseDto(user);
        } else {
            return null;
        }
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserResponseDto> userResponseDtoList = new LinkedList<>();

        for(User user : userList) {
            userResponseDtoList.add(mapUserToUserResponseDto(user));
        }

        return userResponseDtoList;
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id).orElse(null);

        if(user != null) {
            mapUserRequestDtoToUser(userRequestDto, user);
            userRepository.save(user);
            return mapUserToUserResponseDto(user);
        } else {
            return null;
        }
    }

    @Override
    public GenericResponseDto removeUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        GenericResponseDto genericResponseDto = new GenericResponseDto();
        if(user != null) {
            String name = user.getName();
            userRepository.deleteById(id);
            genericResponseDto.setSuccess(true);
            genericResponseDto.setMessage("User name: " + name + "(" + id + ") has been removed successfully");
        } else {
            genericResponseDto.setSuccess(false);
            genericResponseDto.setMessage("User doesn't exist");
        }

        return genericResponseDto;
    }

    @Override
    public UserResponseDto searchUserByUsername(String username) {
        User user = userRepository.findByUsername(username);

        if(user != null) {
            return mapUserToUserResponseDto(user);
        } else {
            return null;
        }

    }

    @Override
    public List<UserResponseDto> searchByUsernameContaining(String username) {
        List<User> userList = userRepository.findByUsernameContaining(username);
        List<UserResponseDto> userResponseDtoList = new LinkedList<>();

        for(User user : userList) {
            userResponseDtoList.add(mapUserToUserResponseDto(user));
        }

        return userResponseDtoList;
    }

    @Override
    public List<UserResponseDto> getUsersByName(String name) {
        List<User> userList = userRepository.findByName(name);
        List<UserResponseDto> userResponseDtoList = new LinkedList<>();

        for(User user : userList) {
            userResponseDtoList.add(mapUserToUserResponseDto(user));
        }

        return userResponseDtoList;
    }

    @Override
    public List<UserResponseDto> searchByNameAndGender(String name, Gender gender, String type) {
        List<User> userList = null;

        if(type.equalsIgnoreCase("sql")) {
            userList = userRepository.searchByNameAndGenderUsingSql(name, gender.name());
        } else if(type.equalsIgnoreCase("jpql")) {
            userList = userRepository.searchByNameAndGender(name, gender);
        }

        List<UserResponseDto> userResponseDtoList = new LinkedList<>();

        for(User user : userList) {
            userResponseDtoList.add(mapUserToUserResponseDto(user));
        }

        return userResponseDtoList;
    }

    @Override
    public Page<UserResponseDto> getUserPage(Integer pageIndex, Integer pageSize, String sortBy, String sortOrder) {
        Sort sort = (sortOrder.equalsIgnoreCase("asc")) ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);

        Page<User> userPage = userRepository.findAll(pageable);

        Page<UserResponseDto> userResponseDtoPage = userPage.map(this::mapUserToUserResponseDto);

        return userResponseDtoPage;
    }

    // Helper methods

    // Map User to UserResponseDto
    private UserResponseDto mapUserToUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setGender(user.getGender());

        Profile profile = user.getProfile();
        ProfileResponseDto profileResponseDto = new ProfileResponseDto();
        profileResponseDto.setId(profile.getId());
        profileResponseDto.setBio(profile.getBio());
        profileResponseDto.setAvatarUrl(profile.getAvatarUrl());
        profileResponseDto.setDisplayName(profile.getDisplayName());
        profileResponseDto.setDob(profile.getDob());

        userResponseDto.setProfileResponseDto(profileResponseDto);

        MetaData metaData = user.getMetaData();
        MetaDataResponseDto metaDataResponseDto = new MetaDataResponseDto();
        metaDataResponseDto.setId(metaData.getId());
        metaDataResponseDto.setInterestTags(metaData.getInterestTags());

        userResponseDto.setMetaDataResponseDto(metaDataResponseDto);

        return userResponseDto;
    }

    // Map UserRequestDto to User
    private User mapUserRequestDtoToUser(UserRequestDto userRequestDto, User user) {
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setUsername(userRequestDto.getUsername());
        user.setPassword(userRequestDto.getPassword());
        user.setGender(userRequestDto.getGender());

        return user;
    }
}
