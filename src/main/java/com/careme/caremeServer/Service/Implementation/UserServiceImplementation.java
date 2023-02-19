package com.careme.caremeServer.Service.Implementation;

import com.careme.caremeServer.Entity.User;
import com.careme.caremeServer.Entity.UserInterface;
import com.careme.caremeServer.Exception.UserServiceException;
import com.careme.caremeServer.Repository.UserRepositoryInterface;
import com.careme.caremeServer.Service.UserService;
import com.careme.caremeServer.Utils.ErrorMessages;
import com.careme.caremeServer.Utils.UserUtils;
import com.careme.caremeServer.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplementation
        implements
            UserService
{
    final UserRepositoryInterface userRepository;

    public UserServiceImplementation( UserRepositoryInterface userRepository )
    {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser( UserDTO userDTO )
    {
        User user = new User();

        UserUtils.encryptPassword( userDTO, userDTO.getPassword() );
        BeanUtils.copyProperties( userDTO, user);

        User storedUser = userRepository.save( user );

        System.out.println("valor: " + storedUser.getId() + " - " + storedUser.isCaregiver());

        UserDTO returnUserDTO = new UserDTO();
        BeanUtils.copyProperties(storedUser, returnUserDTO );

        return returnUserDTO;
    }

    @Override
    public void updateUser( String userId, UserInterface userInterfaceDTO )
    {
        User user = userRepository.findById( userId );

        user.setUsername( userInterfaceDTO.getUsername() );
        user.setName( userInterfaceDTO.getName() );
        UserUtils.encryptPassword( userInterfaceDTO, userInterfaceDTO.getPassword() );
        user.setEmail( userInterfaceDTO.getEmail() );
        user.setCellphone( userInterfaceDTO.getCellphone() );
        user.setAbout( userInterfaceDTO.getAbout() );
        user.setCaregiver( userInterfaceDTO.isCaregiver() );

        userRepository.save( user );
    }

    @Override
    public void deleteUser( String userId )
    {
        User userEntityByUserId = userRepository.findById( userId );

        if ( userEntityByUserId == null )
        {
            throw new UserServiceException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());
        }

        userRepository.delete( userEntityByUserId );
    }

    @Override
    public UserDTO getUserById( String userId )
    {
        UserDTO returnUserDTO = new UserDTO();
        User userEntityByUserId = userRepository.findById( userId );

        if ( userEntityByUserId == null )
        {
            throw new UserServiceException( "Usuário não encontrado!" );
        }

        BeanUtils.copyProperties( userEntityByUserId, returnUserDTO );

        return returnUserDTO;
    }

    @Override
    public UserDTO getUserByUsername( String username )
    {
        UserDTO returnUserDTO = new UserDTO();
        User userEntityByUserId = userRepository.findByUsername( username );

        if ( userEntityByUserId == null )
        {
            throw new UserServiceException( "Usuário não encontrado!" );
        }

        BeanUtils.copyProperties( userEntityByUserId, returnUserDTO );

        return returnUserDTO;
    }

    @Override
    public List<UserDTO> getUsers()
    {
        List<UserDTO> userDTOList = new ArrayList<>();

        List<User> userList = userRepository.findAll();

        for ( User user : userList )
        {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties( user, userDTO );

            userDTOList.add(userDTO);
        }

        return userDTOList;
    }
}