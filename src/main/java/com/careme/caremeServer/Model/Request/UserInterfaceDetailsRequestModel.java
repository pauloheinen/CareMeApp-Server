package com.careme.caremeServer.Model.Request;

import com.careme.caremeServer.Entity.UserInterface;

public class UserInterfaceDetailsRequestModel
        implements
            UserInterface
{
    private String username;
    private String name;
    private String password;
    private String email;
    private String cellphone;
    private String about;
    private boolean is_caregiver;
    private String image;

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

    public void setCaregiver( boolean caregiver )
    {
        is_caregiver = caregiver;
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