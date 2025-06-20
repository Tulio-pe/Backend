package com.acme.tallerazo.iam.domain.model.aggregates;


import com.acme.tallerazo.iam.domain.model.valueobjects.EmailAddress;
import com.acme.tallerazo.iam.domain.model.valueobjects.Password;
import com.acme.tallerazo.iam.domain.model.valueobjects.PersonName;
import com.acme.tallerazo.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;



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
public String getStringEmail(){
    return  emailAddress.value();
}
public User(){}
    public User(String username,String firstName, String lastName, String emailAddress, String password) {
    this.personName=new PersonName(firstName,lastName);
    this.emailAddress= new EmailAddress(emailAddress);
    this.password= new Password(password);
    this.username=username;

    }



    public Password getPassword() {
        return password;
    }
}
