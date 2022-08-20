package utility;

import java.util.Date;

public class RegDataBuilder {
    private String name;
    private String email;
    private String phoneNumber;
    private Date birthDate;
    private String password;

    private String address;

    public String getName() {
        return name;
    }
    public String getEmail()    {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress()  {return address;}

    public RegDataBuilder name(String val) {
        name = val;
        return this;
    }

    public RegDataBuilder email(String val) {
        email = val;
        return this;
    }

    public RegDataBuilder phoneNumber(String val) {
        phoneNumber = val;
        return this;
    }

    public RegDataBuilder birthDate(Date val) {
        birthDate = val;
        return this;
    }

    public RegDataBuilder password(String val) {
        password = val;
        return this;
    }

    public RegDataBuilder address(String val)   {
        address = val;
        return this;
    }

    public RegistrationData build() {
        return new RegistrationData(this);
    }
}
