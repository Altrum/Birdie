package io.hoth.birdie.Entities;


import io.hoth.birdie.CustomAnnotation.PasswordMatches;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@PasswordMatches
@Document(collection = "Users")
public class UserPrincipal implements org.springframework.security.core.userdetails.UserDetails{

    @Id
    private String id;

    @NotNull
    @NotEmpty(message = "Please enter your first name")
    private String firstName;

    @NotNull
    @NotEmpty(message = "Please enter your last name")
    private String lastName;

    @NotNull
    @NotEmpty(message = "Please enter your phone Number")
    private String phoneNumber;

    @NotNull
    @Email
    @NotEmpty(message = "Please enter an email address")
    private String email;

    @NotNull
    @NotEmpty(message = "Please enter a password")
    private String password;
    private String matchingPassword;

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String role;

    private String apiKey;
    private String secret;

    private String watchListId;

    private boolean isAccountNonLocked = true;
    private boolean isAccountNonExpired = true;
    private boolean isCredentialNonExpired = true;
    private boolean isEnabled = true;

    private Collection<? extends GrantedAuthority> authorities;


    // Default constructor needed for when post requests are made so that it can instantiate a new UserPrincipal object
    public UserPrincipal() {

    }

    // TODO: Possibly not needed
    public UserPrincipal(@NotNull @NotEmpty(message = "Please enter your first name") String firstName,
                         @NotNull @NotEmpty(message = "Please enter your last name") String lastName,
                         @NotNull @NotEmpty(message = "Please enter your phone Number") String phoneNumber,
                         @NotNull @Email(message = "Please enter a valid email address") @NotEmpty(message = "Please enter an email address") String email,
                         @NotNull @NotEmpty(message = "Please enter a password") @Length(min = 8) String password,
                         @NotNull @NotEmpty String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.username = email.substring(0,email.indexOf("@")-1); // TODO: care for edge cases
        this.role = role;
    }

    // TODO: might be wrong, could screw up CustomUserDetailsService
    public static UserPrincipal create(UserDetails user) {

        return new UserPrincipal(
                "Dummy",
                "Dumdum",
                "2061231234",
                "dummy@dumdum.com",
                "testing",
                "USER"
        );
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWatchListId(){
        return this.watchListId;
    }

    public void setWatchListId(String watchListId){
        this.watchListId = watchListId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMatchingPassword() { return matchingPassword; }

    public void setMatchingPassword(String matchingPassword) { this.matchingPassword = matchingPassword; }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }



    // Implementing Interface from here
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }



}
