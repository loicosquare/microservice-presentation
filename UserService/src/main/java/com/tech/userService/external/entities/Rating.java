package com.tech.userService.entities.externalEntities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {
    private String ratingId;
    private String userId;
    private String EntrepriseId;
    private  int rating;
    private  String feedback;
    private Entreprise entreprise;
}
