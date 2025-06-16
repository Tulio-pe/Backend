package com.acme.tallerazo.iam.domain.model.aggregates;

import com.acme.tallerazo.iam.domain.model.entities.Role;
import com.acme.tallerazo.iam.domain.model.valueobjects.EmailAddress;
import com.acme.tallerazo.iam.domain.model.valueobjects.Password;
import com.acme.tallerazo.iam.domain.model.valueobjects.PersonName;
import com.acme.tallerazo.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class User extends AuditableAbstractAggregateRoot<User> {
    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String username;
@Embedded
    @AttributeOverrides({@AttributeOverride(name="firstName",column = @Column(name = "first_name_manager")),
     @AttributeOverride(name="lastName",column = @Column(name="last_name_manager"))})
     private PersonName personName;
@Embedded
@AttributeOverrides({ @AttributeOverride(name="value",column = @Column(name="email_address_manager",unique = true))})
    private EmailAddress emailAddress;
    @Embedded
    @AttributeOverrides({@AttributeOverride(name="password", column = @Column(name="password_manager"))})
    private Password password;
@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="user_roles", joinColumns = @JoinColumn(name="user_id"),
    inverseJoinColumns =  @JoinColumn(name="roles_id"))
    private Set<Role> roles;
    public User() {
        this.roles = new HashSet<>();
    }

    public User(String username,String firstName, String lastName, String emailAddress, String password, List<Role> roles) {
    this.personName=new PersonName(firstName,lastName);
    this.emailAddress= new EmailAddress(emailAddress);
    this.password= new Password(password);
    this.username=username;
        this.roles = new HashSet<>();

    }

    public User addRoles(Role role){
    this.roles.add(role);
    return this;
    }
    public User addRoles(List<Role> roles) {
        var validatedRoleSet = Role.validateRoleSet(roles);
        this.roles.addAll(validatedRoleSet);
        return this;
    }

    public Password getPassword() {
        return password;
    }
}
