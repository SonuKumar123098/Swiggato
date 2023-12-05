package com.example.swiggato.dto.response;
import com.example.swiggato.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerResponse {

    String name;
    Gender gender;
    String email;
    String address;
    int cartId;
}
