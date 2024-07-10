package entity;

import java.util.UUID;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;
    public String name;   
    public String email;
    public String phone;
    public String document;

    // public enum area {
    //     teacher,
    //     technician,
    //     engineer
    // }

    // public enum organization {
    //     SESI,
    //     FIEMS,
    //     IEL,
    //     SENAI
    // }

}
