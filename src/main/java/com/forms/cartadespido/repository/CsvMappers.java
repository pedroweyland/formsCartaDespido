package com.forms.cartadespido.repository;

import com.forms.cartadespido.model.entity.Amount;
import com.forms.cartadespido.model.entity.Details;
import com.forms.cartadespido.model.entity.Quotes;
import com.forms.cartadespido.model.entity.Worker;

public class CsvMappers {

    public Worker mapToWorker(String[] row) {
        return Worker.builder()
                .RutTrab(Long.parseLong(row[0]))
                .Nom(row[1])
                .ApePat(row[2])
                .ApeMat(row[3])
                .SelDom(row[4])
                .CodCom(Integer.parseInt(row[5]))
                .Sex(Integer.parseInt(row[6]))
                .Nac(Integer.parseInt(row[7]))
                .build();
    }

    public Details mapToDetails(String[] row) {
        return Details.builder()
                .RutTrab(Long.parseLong(row[0]))
                .Forma(Integer.parseInt(row[1]))
                .FecIniCon(row[2])
                .FecTerCon(row[3])
                .FecComDes(row[4])
                .Causal(row[5])
                .Motivo(row[6])
                .build();
    }

    public Amount mapToAmount(String[] row) {
        return Amount.builder()
                .RutTrab(Long.parseLong(row[0]))
                .AnoSer(Long.parseLong(row[1]))
                .Aviso(Long.parseLong(row[2]))
                .build();
    }

    public Quotes mapToQuotes(String[] row) {
        return Quotes.builder()
                .RutTrab(Long.parseLong(row[0]))
                .Prev(row[1])
                .Doc(Integer.parseInt(row[2]))
                .build();
    }
}
