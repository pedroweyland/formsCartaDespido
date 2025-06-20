package com.forms.cartadespido.repository;

import com.forms.cartadespido.model.entity.Amount;
import com.forms.cartadespido.model.entity.Details;
import com.forms.cartadespido.model.entity.Quotes;
import com.forms.cartadespido.model.entity.Worker;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CsvReader {

    public static <T> List<T> readCsv(String pathCsv, Function<String[], T> mapper) {
        List<T> result = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(pathCsv))) {
            List<String[]> rows = reader.readAll();
            boolean isHeader = true;

            for (String[] row : rows) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                T obj = mapper.apply(row);
                result.add(obj);
            }

        } catch (IOException | CsvException e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        }

        return result;
    }



    public List<Worker> leerDesdeCsvWorkers(String pathCsv) {
        List<Worker> workers = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(pathCsv))) {
            List<String[]> filas = reader.readAll();
            boolean esHeader = true;

            for (String[] fila : filas) {
                if (esHeader) {
                    esHeader = false;
                    continue;
                }

                Worker worker = Worker.builder()
                        .RutTrab(Long.parseLong(fila[0]))
                        .Nom(fila[1])
                        .ApePat(fila[2])
                        .ApeMat(fila[3])
                        .SelDom(fila[4])
                        .CodCom(Integer.parseInt(fila[5]))
                        .Sex(Integer.parseInt(fila[6]))
                        .Nac(Integer.parseInt(fila[7]))
                        .build();

                workers.add(worker);
            }

        } catch (IOException | CsvException | NumberFormatException e) {
            System.out.println("Error leyendo el CSV: " + e.getMessage());
        }

        return workers;
    }

    public List<Details> leerDesdeCsvDetails(String pathCsv) {
        List<Details> details = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(pathCsv))) {
            List<String[]> filas = reader.readAll();
            boolean esHeader = true;

            for (String[] fila : filas) {
                if (esHeader) {
                    esHeader = false;
                    continue;
                }

                Details detail = Details.builder()
                        .RutTrab(Long.parseLong(fila[0]))
                        .Forma(Integer.parseInt(fila[1]))
                        .FecIniCon(fila[2])
                        .FecTerCon(fila[3])
                        .FecComDes(fila[4])
                        .Causal(fila[5])
                        .Motivo(fila[6])
                        .build();

                details.add(detail);
            }

        } catch (IOException | CsvException | NumberFormatException e) {
            System.out.println("Error leyendo el CSV: " + e.getMessage());
        }

        return details;
    }

    public List<Amount> leerDesdeCsvAmount(String pathCsv) {
        List<Amount> amounts = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(pathCsv))) {
            List<String[]> filas = reader.readAll();
            boolean esHeader = true;

            for (String[] fila : filas) {
                if (esHeader) {
                    esHeader = false;
                    continue;
                }

                Amount amount = Amount.builder()
                        .RutTrab(Long.parseLong(fila[0]))
                        .AnoSer(Long.parseLong(fila[1]))
                        .Aviso(Long.parseLong(fila[2]))
                        .build();

                amounts.add(amount);
            }

        } catch (IOException | CsvException | NumberFormatException e) {
            System.out.println("Error leyendo el CSV: " + e.getMessage());
        }

        return amounts;
    }

    public List<Quotes> leerDesdeCsvQuotes(String pathCsv) {
        List<Quotes> quotes = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(pathCsv))) {
            List<String[]> filas = reader.readAll();
            boolean esHeader = true;

            for (String[] fila : filas) {
                if (esHeader) {
                    esHeader = false;
                    continue;
                }

                Quotes quote = Quotes.builder()
                        .RutTrab(Long.parseLong(fila[0]))
                        .Prev(fila[1])
                        .Doc(Integer.parseInt(fila[2]))
                        .build();

                quotes.add(quote);
            }

        } catch (IOException | CsvException | NumberFormatException e) {
            System.out.println("Error leyendo el CSV: " + e.getMessage());
        }

        return quotes;
    }
}
