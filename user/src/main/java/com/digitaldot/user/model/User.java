package com.digitaldot.user.model;

import com.digitaldot.employer.model.Employer;
import com.digitaldot.validator.ValidField;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import org.springframework.data.annotation.TypeAlias;

import javax.persistence.*;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    @ValidField(message = "Digite um email válido", type = ValidField.TypeValid.EMAIL)
    private String email;
    private String password;
    private boolean acceptedTerms;
    private boolean isActive;
    @OneToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAcceptedTerms() {
        return acceptedTerms;
    }

    public void setAcceptedTerms(boolean acceptedTerms) {
        this.acceptedTerms = acceptedTerms;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }
}
