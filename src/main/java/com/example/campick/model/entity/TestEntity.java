package com.example.campick.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@Entity
@Table(name = "test")
@AllArgsConstructor
@NoArgsConstructor
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long testId;
}