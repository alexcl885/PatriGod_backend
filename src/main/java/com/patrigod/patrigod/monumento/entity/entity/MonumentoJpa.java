package com.patrigod.patrigod.monumento.entity.entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.patrigod.patrigod.articulo.entity.entity.ArticuloJpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@JsonTypeName("monumento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "monumento")
@SuperBuilder
public class MonumentoJpa extends ArticuloJpa {

    @Column(length = 255)
    private String imagen;

    private String estiloArquitectonico;     

    private String epocaConstruccion;        

    private String ubicacion;                

    private String horarioVisitas;           

    private String precioEntrada;            

    private String declaracionUnesco;        

    private Double altura;                   

    private String materialesPrincipales;    

    @Column(length = 1000)
    private String curiosidades;             
}
