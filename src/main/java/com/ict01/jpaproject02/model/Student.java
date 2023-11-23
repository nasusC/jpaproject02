package com.ict01.jpaproject02.model;

// Entity와 DTO의 구분
// Entity(Immutable) - class, object
// DTO(Mutable)

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // IDENTITY = mysql
    // Sequence = oracle
    private int id;

    @Column(length = 30) // 행의 길이 30
    private String name;

    @Column(length = 30)
    private String email;

    @Column(length = 100)
    private String address;
}



