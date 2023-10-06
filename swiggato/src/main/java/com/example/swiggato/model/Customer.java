package com.example.swiggato.model;

import com.example.swiggato.Enum.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Validated
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
//    @Size(min =2, max = 40)
    @Size(min = 2, message = "{validation.name.size.too_short}")
    @Size(max = 40, message = "{validation.name.size.too_long}")
    String name;
    @Enumerated(EnumType.STRING)
    Gender gender;
    @Email
    @Column(unique = true,nullable = false)
    String email;
    @Column(nullable = false,unique = true)
    @Size(min = 10, max = 10)
    String mobileNo;

    String address;

    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    Cart cart;
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<OrderEntity>orderEntityList=new ArrayList<>();


}
