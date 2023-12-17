package com.tech.enterpriseService.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "enterprises")
public class Enterprise {

    @Id
    private  String id;
    private  String name;
    private  String location;
    private  String about;
}
