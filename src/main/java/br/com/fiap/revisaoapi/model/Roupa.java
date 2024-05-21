package br.com.fiap.revisaoapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Tamanho;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "tb_roupas")
public class Roupa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O tipo é obrigatório")
    @Column(name = "tipo")
    private String tipo;

    @Size(max=3, message = "o tamanho deve ter no máximo 3 caracteres")
    @Column(name = "tamanho")
    private String tamanho;

    @NotBlank(message = "A cor é obrigatória")
    @Column(name = "cor")
    private String cor;

    @NotBlank(message = "A marca é obrigatória")
    @Column(name ="marca")
    private String marca;

    @Min(message= "o preço deve ter no mínimo 2 caracteres")
    @Column(name ="preco")
    private double preco;

    public Roupa() {
    }

    public Roupa(Long id, String tipo, String tamanho, String cor, String marca, String preco) {
        this.id = id;
        this.tipo = tipo;
        this.tamanho = tamanho;
        this.cor = cor;
        this.marca= marca;
        this.preco= preco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
}
