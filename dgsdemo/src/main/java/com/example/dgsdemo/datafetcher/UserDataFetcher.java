package com.example.dgsdemo.datafetcher;

import com.example.dgsdemo.entity.Users;
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
    public List<Users> users() {
        return userRepository.findAll();
    }

    @DgsQuery
    public Users userById(@InputArgument int id) {
        Optional<Users> user = userRepository.findById(id);
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
        Users users = new Users(name, email, age, phoneNumber, address, dateOfBirthNew, profilePictureUrl);
        userRepository.save(users);
        return new MutationResponse(true, "Users added successfully.", 200);
    }

    @DgsMutation
    public MutationResponse deleteUser(@InputArgument Integer id) {
        try {
            // Check if the users exists
            Users users = userRepository.findById(id).orElse(null);
            if (users == null) {
                return new MutationResponse(false, "Users not found.", 404);
            }
            // Delete the users
            userRepository.delete(users);
            return new MutationResponse(true, "Users deleted successfully.", 200);
        } catch (Exception e) {
            return new MutationResponse(false, "Error deleting user: " + e.getMessage(), 500);
        }
    }
}
