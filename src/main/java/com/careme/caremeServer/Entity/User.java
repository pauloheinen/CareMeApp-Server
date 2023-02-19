package com.careme.caremeServer.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity( name = "users" )
public class User
    implements
        Serializable,
        UserInterface
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id" )
    private Long id;

    @Column( name = "username", nullable = false, length = 20 )
    private String username;

    @Column( name = "name", nullable = false, length = 160 )
    private String name;

    @Column( name = "password", nullable = false, length = 65 )
    private String password;

    @Column( name = "email", nullable = false, length = 40, unique = true )
    private String email;

    @Column( name = "cellphone", nullable = false, length = 11, unique = true )
    private String cellphone;

    @Column( name = "about", length = 4000 )
    private String about;

    @Column( name = "is_caregiver", nullable = false )
    private boolean is_caregiver;

    @Column( name = "image" )
    private String image;

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername( String username )
    {
        this.username = username;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getCellphone()
    {
        return cellphone;
    }

    public void setCellphone( String cellphone )
    {
        this.cellphone = cellphone;
    }

    public String getAbout()
    {
        return about;
    }

    public void setAbout( String about )
    {
        this.about = about;
    }

    public boolean isCaregiver()
    {
        return is_caregiver;
    }

    public void setCaregiver( boolean is_caregiver )
    {
        this.is_caregiver = is_caregiver;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage( String image )
    {
        this.image = image;
    }
}
