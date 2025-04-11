package com.fiap.agnello.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssinaturaPlano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome; // Ex: "Essencial"

    @NotBlank
    private String descricaoCurta;

    @Column(columnDefinition = "TEXT")
    private String beneficios;

    @DecimalMin("0.01")
    private Double precoMensal;

    @Column(unique = true)
    private String slug; // Ex: "essencial", usado para URLs e lookup

    private Boolean ativo = true;

    @CreationTimestamp
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    private LocalDateTime atualizadoEm;

    @OneToMany(mappedBy = "plano", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Assinatura> assinaturas;

    private boolean destaque;
}
