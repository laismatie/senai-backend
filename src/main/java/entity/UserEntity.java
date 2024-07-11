package entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class UserEntity extends PanacheEntity {
    @NotNull(message="Name cannot be blank")
    public String name;  
    
    @Email(message = "Invalid email address")
    @NotNull(message = "Email cannot be blank")
    public String email;

    @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}", message = "Invalid phone. Expected format: (67) 99999-9999")
    @NotNull(message = "Phone cannot be blank")
    public String phone;

    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "Invalid document. Expected format: 000.000.000-00")
    @NotNull(message = "Document cannot be blank")
    public String document;

    @NotNull(message = "area cannot be blank")
    public Area area;

    @NotNull(message = "organization cannot be blank")
    public Organization organization;

    public enum Area {
        teacher,
        technician,
        engineer
    }

    public enum Organization {
        SESI,
        FIEMS,
        IEL,
        SENAI
    }

}
