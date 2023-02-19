package com.careme.caremeServer.Entity;

public interface UserInterface
{
    public String getUsername();

    public void setUsername( String username );

    public String getName();

    public void setName( String name );

    public String getPassword();

    public void setPassword( String password );

    public String getEmail();

    public void setEmail( String email );

    public String getCellphone();

    public void setCellphone( String cellphone );

    public String getAbout();

    public void setAbout( String about );

    public boolean isCaregiver();

    public void setCaregiver( boolean is_caregiver );

    public String getImage();

    public void setImage( String image );
}
