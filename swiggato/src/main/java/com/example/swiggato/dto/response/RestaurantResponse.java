package com.example.swiggato.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantResponse {
     String name;
     String contactNumber;
     boolean opened;
     String location;
     List<MenuResponse>menu;
}
