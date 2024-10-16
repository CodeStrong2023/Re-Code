package com.utn.recode.tiendalibros.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idLibro;
    String nombreLibro;
    String autor;
    Double precio;
    Integer existencias;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}