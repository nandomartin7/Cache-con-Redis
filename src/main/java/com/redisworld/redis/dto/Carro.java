package com.redisworld.redis.dto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Carro implements Serializable {
    private static final  long serialVersionUID = 7156526077883281623L;

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "SEQ_Carro", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    private Long id;
    private String marca;
    private String modelo;
    private long precio;

    public Carro(){
    }

    public Carro(String marca, String modelo, long precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return String.format("Carro{id=%d, marca='%s', modelo='%s', precio=%d}", id,marca,modelo,precio);
    }
}
