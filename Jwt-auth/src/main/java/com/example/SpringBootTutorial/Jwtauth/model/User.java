package com.example.SpringBootTutorial.Jwtauth.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements UserDetails {
    //Learn:
    //  Provides core user information.
    //  Implementations are not used directly by Spring Security for security purposes.
    //  They simply store user information which is later encapsulated into Authentication objects.
    //  This allows non-security related user information (such as email addresses, telephone numbers etc) to be stored
    //  in a convenient location.
    //  Concrete implementations must take particular care to ensure the non-null contract detailed for each method
    //  is enforced. See User for a reference implementation (which you might like to extend or use in your code).

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
     String name;
     String username;
     String password;

    boolean accountNonExpired;
    boolean isEnabled;
    boolean accountNonLocked;
    boolean credentialsNonExpired;

    //Learn:
    // Used when you want to model a collection of simple (non-entity) elements associated with an entity
    // relationships such as OneToMany is used when target class is entity
    // Typically used for basic types (e.g., strings, numbers, enums) or embeddable objects (we don't need build an entity for this)
    // Creates a separate table (e.g., "authorities") to store the collection elements.
    // The owning entity (User in this case) has a foreign key reference to the collection table.
    @ElementCollection(targetClass = Role.class , fetch = FetchType.EAGER)
    @JoinTable(name = "authorities",joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", nullable = false) // every user ("user_id") must be reference role
    @Enumerated(EnumType.STRING)
    Set<Role> authorities;

    //Learn: @Data is done following methods
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return accountNonExpired;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return accountNonLocked;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return credentialsNonExpired;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return isEnabled;
//    }
}
