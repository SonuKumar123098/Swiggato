package com.example.swiggato.dto.request;

import com.example.swiggato.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeliveryPartnerRequest {
    String name;
    String mobileNo;
    Gender gender;
}
