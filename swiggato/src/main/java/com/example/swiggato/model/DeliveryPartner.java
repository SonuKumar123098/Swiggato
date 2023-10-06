package com.example.swiggato.model;

import com.example.swiggato.Enum.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "delivery_partners")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryPartner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Size(min = 2,max = 40)
    String name;
    @Column(unique = true,nullable = false)
    @Size(min = 10, max = 10)
    String mobileNo;
    @Enumerated(EnumType.STRING)
    Gender gender;

    @OneToMany(mappedBy = "deliveryPartner",cascade = CascadeType.ALL)
    List<OrderEntity>orderEntityList=new ArrayList<>();

}
