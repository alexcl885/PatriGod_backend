package com.patrigod.monumento.entity.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.patrigod.article.infraestructure.repository.jpa.entity.ArticleJpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

@OnDelete(action = OnDeleteAction.CASCADE)
@PrimaryKeyJoinColumn(name = "id")
@JsonTypeName("monument")
@Table(name = "monument")
public class MonumentoJpa extends ArticleJpa {

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
