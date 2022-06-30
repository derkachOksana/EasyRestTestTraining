package utility;

import java.util.Date;

public class RegistrationData {
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final Date birthDate;
    private final String password;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public RegistrationData(RegDataBuilder builder)   {
        name = builder.getName();
        email = builder.getEmail();
        phoneNumber = builder.getPhoneNumber();
        birthDate = builder.getBirthDate();
        password = builder.getPassword();
    }
}
