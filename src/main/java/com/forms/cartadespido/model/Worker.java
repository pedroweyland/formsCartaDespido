package com.forms.cartadespido.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Worker {

    private Long RutTrab;
    private String Nom;
    private String ApePat;
    private String ApeMat;
    private String SelDom;
    private Integer CodCom;
    private Integer Sex;
    private Integer Nac;
    private Details details;
}
