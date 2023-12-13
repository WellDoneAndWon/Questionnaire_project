package com.ildan.testing.opencode.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
public class StartStopResult {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private Boolean result;
}
