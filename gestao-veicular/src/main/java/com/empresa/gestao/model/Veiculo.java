package com.empresa.gestao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 8)
    @NotBlank(message = "A placa é obrigatória")
    @Size(min = 7, max = 8, message = "A placa deve ter entre 7 e 8 caracteres")
    private String placa;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "A marca é obrigatória")
    private String marca;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "O modelo é obrigatório")
    private String modelo;

    @Column(nullable = false)
    @NotNull(message = "O ano é obrigatório")
    @Min(value = 1900, message = "O ano deve ser maior que 1900")
    @Max(value = 2100, message = "O ano deve ser menor que 2100")
    private Integer ano;

    public Veiculo() {}

    public Veiculo(Long id, String placa, String marca, String modelo, Integer ano) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
    }

    public Long getId() { return id; }
    public String getPlaca() { return placa; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public Integer getAno() { return ano; }

    public void setId(Long id) { this.id = id; }
    public void setPlaca(String placa) { this.placa = placa; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setAno(Integer ano) { this.ano = ano; }
}
