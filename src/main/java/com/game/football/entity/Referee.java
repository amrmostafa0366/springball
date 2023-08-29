package com.game.football.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "referees")
public class Referee extends Person{
}
