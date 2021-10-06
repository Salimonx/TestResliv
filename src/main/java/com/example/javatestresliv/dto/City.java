package com.example.javatestresliv.dto;

import javax.persistence.*;

@Entity
@Table(name = "city", schema = "public")
public class City {

    @Id
    @SequenceGenerator( name = "jpaSequence", sequenceName = "JPA_SEQUENCE", allocationSize = 1, initialValue = 4 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
    private Long id;
    @Column(unique = true)
    private String name;
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
