package csc394.artisanshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "{csc394.artisanshop.notnull.username.message}")
    @Column(name = "username")
    private String userName;

    @NotNull(message = "{csc394.artisanshop.notnull.password.message}")
    @Column(name = "password")
    private String password;

    @Email(message = "{csc394.artisanshop.email.message}")
    @Column(name = "email")
    private String eMail;

    @Column(name = "createDate")
    private Date userCreateDate;

    @Column(name = "notificationPermission")
    private boolean notificationPermission = true;

    @OneToMany
    private List<SellerComment> sellerComment;

    @OneToMany
    private List<ProductComment> productComment;

    // @ManyToOne
    // private CreditCard creditCard;

    @OneToMany
    private List<Address> address;

    public User(String userName, String password, String eMail, Date userCreateDate, boolean notificationPermission) {
        this.userName = userName;
        this.password = password;
        this.eMail = eMail;
        this.userCreateDate = userCreateDate;
        this.notificationPermission = notificationPermission;
    }

}