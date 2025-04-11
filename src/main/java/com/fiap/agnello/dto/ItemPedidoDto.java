package com.fiap.agnello.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ItemPedidoDto(
        @NotNull Long produtoId,
        @Min(1) Integer quantidade
) {}

