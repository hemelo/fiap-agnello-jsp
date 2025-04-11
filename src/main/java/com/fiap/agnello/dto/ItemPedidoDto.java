package com.fiap.agnello.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemPedidoDto{
    @NotNull
    private Long produtoId;

    @Min(1)
    private Integer quantidade;
}

