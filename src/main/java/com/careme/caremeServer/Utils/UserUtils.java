package com.careme.caremeServer.Utils;

import com.careme.caremeServer.Entity.UserInterface;
import com.careme.caremeServer.Model.Request.UserInterfaceDetailsRequestModel;
import com.careme.caremeServer.Repository.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserUtils
{
    private static UserRepositoryInterface repository;

    @Autowired
    private UserUtils( UserRepositoryInterface repository )
    {
        UserUtils.repository = repository;
    }

    public static boolean validateUser( UserInterfaceDetailsRequestModel user )
    {
        return repository.findUserFields( user ) == null;
    }

    public static boolean validateFields( UserInterface userInterface )
    {
        if ( userInterface.getEmail().strip().isEmpty() )
        {
            return false;
        }
        if ( userInterface.getUsername().strip().isEmpty() )
        {
            return false;
        }
        if ( userInterface.getPassword().strip().isEmpty() )
        {
            return false;
        }
        if ( userInterface.getEmail().strip().isEmpty() )
        {
            return false;
        }
        if ( userInterface.getCellphone().strip().isEmpty() )
        {
            return false;
        }
        if ( userInterface.getName().strip().isEmpty() )
        {
            return false;
        }

        return true;
    }

    public static void encryptPassword( UserInterface userInterfaceEntity, String password )
    {
        String encryptPass = "<".concat( new BCryptPasswordEncoder().encode( password ) ).concat( ">" );

        userInterfaceEntity.setPassword( encryptPass );
    }

    public static boolean passwordMatch( UserInterface user, String password )
    {
        String userPass = user.getPassword();

        String pass = userPass.substring( 1, userPass.length()-1 );

        return new BCryptPasswordEncoder().matches( password, pass );
    }
}
