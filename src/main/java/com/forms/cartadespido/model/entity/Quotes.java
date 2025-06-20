package com.forms.cartadespido.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Quotes {
    private Long RutTrab;
    private String Prev;
    private Integer Doc;
}
