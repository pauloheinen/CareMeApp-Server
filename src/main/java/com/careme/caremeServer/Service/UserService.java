package com.careme.caremeServer.Service;

import com.careme.caremeServer.Entity.UserInterface;
import com.careme.caremeServer.dto.UserDTO;

import java.util.List;

public interface UserService
{
    UserDTO createUser( UserDTO userDTO );
    void updateUser( String userId, UserInterface userInterfaceDTO );
    void deleteUser( String userId );
    UserDTO getUserById( String userId );
    UserDTO getUserByUsername( String username );
    List<UserDTO> getUsers();
    List<UserDTO> getUserButNotId( String id );
}
