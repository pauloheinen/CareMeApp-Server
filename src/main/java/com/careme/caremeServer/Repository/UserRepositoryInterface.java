package com.careme.caremeServer.Repository;

import com.careme.caremeServer.Entity.User;
import com.careme.caremeServer.Model.Request.UserInterfaceDetailsRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryInterface
        extends
            JpaRepository<User, Long>
{
    @Query( value = "select * from users where email = :email", nativeQuery = true )
    User findByEmail( String email );

    @Query( value = "select * from users where id = :id", nativeQuery = true )
    User findById( String id );

    @Query( value = " select * from users where email = :#{#user.email}" +
                    " or cellphone = :#{#user.cellphone}" +
                    " or username = :#{#user.username}", nativeQuery = true )
    User findUserFields( UserInterfaceDetailsRequestModel user );

    @Query( value = " select * from users where username = :username", nativeQuery = true )
    User findByUsername( String username );

    @Query( value = " select * from users where id <> :id", nativeQuery = true )
    List<User> findAllButNot( String id );
}
