package com.tech.entrepriseService.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "entreprises")
public class Entreprise {

    @Id
    private  String id;
    private  String name;
    private  String location;
    private  String about;
}
