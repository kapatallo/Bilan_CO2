package fr.univlyon1.m1if10.bilanCo2.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO with the data we get from the server for creation of user.
 */
public class UserDto {
        private String email;
        private String mdp;

    /**
     * Basic constructor.
     */
    public UserDto() {
    }

    /**
     * Constructor with the param we need.
     * @param email
     * @param mdp
     */
    @JsonCreator
    public UserDto(@JsonProperty("email") final String email,
                   @JsonProperty("mdp") final String mdp) {
        this.email = email;
        this.mdp = mdp;
    }

        public String getemail() {
        return email;
    }

        public void setemail(final String email) {
        this.email = email;
    }

        public String getmdp() {
        return mdp;
    }

        public void setmdp(final String mdp) {
        this.mdp = mdp;
    }

}
