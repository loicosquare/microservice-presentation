package com.tech.categoryService.external.entities;

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
    private String gameId;
    private  int rating;
    private  String feedback;
}

