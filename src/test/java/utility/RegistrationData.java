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

    public static class Builder {
        private String name = "";
        private String email = "";
        private String phoneNumber = "";
        private Date birthDate;
        private String password;

        public Builder()    {

        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder email(String val) {
            email = val;
            return this;
        }

        public Builder phoneNumber(String val) {
            phoneNumber = val;
            return this;
        }

        public Builder birthDate(Date val) {
            birthDate = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public RegistrationData build() {
            return new RegistrationData(this);
        }
    }

        private RegistrationData(Builder builder)   {
            name = builder.name;
            email = builder.email;
            phoneNumber = builder.phoneNumber;
            birthDate = builder.birthDate;
            password = builder.password;
    }
}
