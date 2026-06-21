package com.gabriel.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Roles")
public class RolesEntity implements GrantedAuthority {
    @Id
    private Long id;
    private String name;
    @Override
    public String getAuthority() {
        return name;
    }
}

