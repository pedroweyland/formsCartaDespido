package com.forms.cartadespido.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Details {

    private Long RutTrab;
    private Integer Forma;
    private String FecIniCon;
    private String FecTerCon;
    private String FecComDes;
    private String Causal;
    private String Motivo;
}
