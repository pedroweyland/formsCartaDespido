package com.forms.cartadespido.repository;

import com.forms.cartadespido.model.Amount;
import com.forms.cartadespido.model.Details;
import com.forms.cartadespido.model.Quotes;
import com.forms.cartadespido.model.Worker;

public class CsvMappers {

    public Worker mapToWorker(String[] row) {
        return Worker.builder()
                .RutTrab(Long.parseLong(row[0]))
                .RutEmpleador(Long.parseLong(row[1]))
                .Nom(row[2])
                .ApePat(row[3])
                .ApeMat(row[4])
                .SelDom(row[5])
                .CodCom(Integer.parseInt(row[6]))
                .Sex(Integer.parseInt(row[7]))
                .Nac(Integer.parseInt(row[8]))
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
