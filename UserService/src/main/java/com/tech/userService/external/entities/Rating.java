package com.tech.userService.external.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {
    private String ratingId;
    private String userId;
    private String EnterpriseId;
    private  int rating;
    private  String feedback;
    private Enterprise enterprise;
}
