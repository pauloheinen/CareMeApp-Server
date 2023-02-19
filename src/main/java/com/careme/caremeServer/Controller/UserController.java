package com.careme.caremeServer.Controller;

import com.careme.caremeServer.Exception.UserServiceException;
import com.careme.caremeServer.Model.Request.UserInterfaceDetailsRequestModel;
import com.careme.caremeServer.Model.Response.OperationStatusModel;
import com.careme.caremeServer.Model.ui.UserRest;
import com.careme.caremeServer.Service.UserService;
import com.careme.caremeServer.Utils.ErrorMessages;
import com.careme.caremeServer.Utils.RequestOperationName;
import com.careme.caremeServer.Utils.RequestOperationResult;
import com.careme.caremeServer.Utils.UserUtils;
import com.careme.caremeServer.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping( value = "/api/v1" )
public class UserController
{
    private final UserService userService;

    public UserController( UserService userService )
    {
        this.userService = userService;
    }

    @PostMapping( path = "/add/user",
                  consumes = { MediaType.APPLICATION_JSON_VALUE },
                  produces = { MediaType.APPLICATION_JSON_VALUE } )
    public UserRest createUser( @RequestBody UserInterfaceDetailsRequestModel userDetails )
    {
        if ( ! UserUtils.validateFields( userDetails ) )
        {
            throw new UserServiceException( ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage() );
        }

        if ( ! UserUtils.validateUser( userDetails ) )
        {
            throw new UserServiceException( ErrorMessages.RECORD_ALREADY_EXIST.getErrorMessage() );
        }

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties( userDetails, userDTO );

        UserRest userRest = new UserRest();
        UserDTO createdUserDTO = userService.createUser( userDTO );
        BeanUtils.copyProperties( createdUserDTO, userRest );

        return userRest;
    }

    @PutMapping( path = "/update/user/{id}",
                 consumes = { MediaType.APPLICATION_JSON_VALUE },
                 produces = { MediaType.APPLICATION_JSON_VALUE } )
    public void updateUser( @PathVariable( "id" ) String userId,
                            @RequestBody UserInterfaceDetailsRequestModel userDetails )
    {
        if ( ! UserUtils.validateFields( userDetails ) )
        {
            throw new UserServiceException( ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage() );
        }

        if ( UserUtils.validateUser( userDetails ) )
        {
            throw new UserServiceException( ErrorMessages.RECORD_NOT_FOUND.getErrorMessage() );
        }

        UserDTO userDTO = new UserDTO();

        BeanUtils.copyProperties( userDetails, userDTO );

        userService.updateUser( userId, userDTO );
    }

    @DeleteMapping( path = "/delete/user/{id}" )
    public OperationStatusModel deleteUser( @PathVariable( "id" ) String userId )
    {
        OperationStatusModel operationStatusModel = new OperationStatusModel();
        operationStatusModel.setOperationName( RequestOperationName.DELETE.name() );

        userService.deleteUser(userId);

        operationStatusModel.setOperationResult( RequestOperationResult.SUCCESS.name() );
        return operationStatusModel;
    }

    @GetMapping( path = "/get/user/id/{id}", produces = { MediaType.APPLICATION_JSON_VALUE } )
    public UserRest getUserById( @PathVariable( "id" ) String userId )
    {
        UserRest userRest = new UserRest();

        UserDTO userDTO = userService.getUserById( userId );

        BeanUtils.copyProperties( userDTO, userRest );

        return userRest;
    }

    @GetMapping( path = "/get/user/username/{username}", produces = { MediaType.APPLICATION_JSON_VALUE } )
    public UserRest getUserByUsername( @PathVariable( "username" ) String username )
    {
        UserRest userRest = new UserRest();

        UserDTO userDTO = userService.getUserByUsername( username );

        BeanUtils.copyProperties( userDTO, userRest );

        return userRest;
    }

    @GetMapping( path = "/get/users", produces = { MediaType.APPLICATION_JSON_VALUE } )
    public List<UserRest> getUsers()
    {
        List<UserRest> userRestList = new ArrayList<>();

        List<UserDTO> users = userService.getUsers();

        for ( UserDTO userDTO : users )
        {
            UserRest userRest = new UserRest();
            BeanUtils.copyProperties( userDTO, userRest );
            userRestList.add( userRest );
        }

        return userRestList;
    }

    @PostMapping( path = "/login",
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE } )
    public UserRest loginUser( @RequestBody UserInterfaceDetailsRequestModel userDetails )
    {
        UserDTO userDTO = userService.getUserByUsername( userDetails.getUsername() );

        if ( ! UserUtils.passwordMatch( userDTO, userDetails.getPassword() ) )
        {
            throw new UserServiceException( "User not found" );
        }

        UserRest userRest = new UserRest();
        BeanUtils.copyProperties( userDTO, userRest );

        return userRest;
    }
}
