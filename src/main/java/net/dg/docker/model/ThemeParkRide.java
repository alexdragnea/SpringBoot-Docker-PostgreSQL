package net.dg.docker.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ThemeParkRide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    private int thrillFactor;

    public ThemeParkRide(String name, String description, int thrillFactor) {
        this.name = name;
        this.description = description;
        this.thrillFactor = thrillFactor;
    }

}