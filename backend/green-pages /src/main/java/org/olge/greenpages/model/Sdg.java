package org.olge.greenpages.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sdgs")
public class Sdg {

    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}