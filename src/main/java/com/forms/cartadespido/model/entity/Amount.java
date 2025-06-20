package com.forms.cartadespido.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Amount {
    private Long RutTrab;
    private Long AnoSer;
    private Long Aviso;
}
