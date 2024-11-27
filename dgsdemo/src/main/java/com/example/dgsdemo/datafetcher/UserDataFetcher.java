package com.example.dgsdemo.datafetcher;

import com.example.dgsdemo.entity.User;
import com.example.dgsdemo.repository.UserRepository;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.example.dgsdemo.response.MutationResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DgsComponent
public class UserDataFetcher {

    private final UserRepository userRepository;

    public UserDataFetcher(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @DgsQuery
    public List<User> users() {
        return userRepository.findAll();
    }

    @DgsQuery
    public User userById(@InputArgument int id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);  // Return null if user is not found
    }

    @DgsMutation
    public MutationResponse addUser(
            @InputArgument String name,
            @InputArgument String email,
            @InputArgument Integer age,
            @InputArgument String phoneNumber,
            @InputArgument String address,
            @InputArgument String dateOfBirth,
            @InputArgument String profilePictureUrl
    ) {
        LocalDate dateOfBirthNew = LocalDate.parse(dateOfBirth);
        User user = new User(name, email, age, phoneNumber, address, dateOfBirthNew, profilePictureUrl);
        userRepository.save(user);
        return new MutationResponse(true, "User added successfully.", 200);
    }

    @DgsMutation
    public MutationResponse deleteUser(@InputArgument Integer id) {
        try {
            // Check if the user exists
            User user = userRepository.findById(id).orElse(null);
            if (user == null) {
                return new MutationResponse(false, "User not found.", 404);
            }
            // Delete the user
            userRepository.delete(user);
            return new MutationResponse(true, "User deleted successfully.", 200);
        } catch (Exception e) {
            return new MutationResponse(false, "Error deleting user: " + e.getMessage(), 500);
        }
    }
}
