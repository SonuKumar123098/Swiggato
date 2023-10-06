package com.example.swiggato.dto.request;

import com.example.swiggato.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRequest {
    String name;
    Gender gender;
    String email;
    String mobileNo;
    String address;
}
