package com.forms.cartadespido;

import com.forms.cartadespido.form.FillForms;
import com.forms.cartadespido.model.Amount;
import com.forms.cartadespido.model.Details;
import com.forms.cartadespido.model.Quotes;
import com.forms.cartadespido.model.Worker;
import com.forms.cartadespido.repository.CsvMappers;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.forms.cartadespido.repository.CsvReader.readCsv;

public class App {

    public static void main( String[] args ) {
        CsvMappers csvMappers = new CsvMappers();
        FillForms fillForms = new FillForms();

        List<Worker> workers = readCsv("src/main/resources/FakeDataWorkers.csv", csvMappers::mapToWorker);
        List<Details> details = readCsv("src/main/resources/FakeDataDetails.csv", csvMappers::mapToDetails);
        List<Amount> amounts = readCsv("src/main/resources/FakeDataAmount.csv", csvMappers::mapToAmount);
        List<Quotes> quotes = readCsv("src/main/resources/FakeDataQuotes.csv", csvMappers::mapToQuotes);

        for (Worker worker : workers) {
            System.out.println("Processing worker: " + worker.getNom() + " - RUN: " + worker.getRutTrab());

            Details detail = getDetail(worker, details);
            Amount amount = getAmount(worker, amounts);
            Quotes quote = getQuotes(worker, quotes);

            if (detail != null && amount != null && quote != null) {
                fillForms.fillForm(worker, detail, amount, quote);
            } else {
                System.out.println("Missing data for worker: " + worker.getNom() + " - RUN: " + worker.getRutTrab());
            }

        }
    }

    @Nullable
    private static Amount getAmount(Worker worker, List<Amount> amounts) {
        return amounts.stream()
                .filter(a -> a.getRutTrab().equals(worker.getRutTrab()))
                .findFirst()
                .orElse(null);
    }

    @Nullable
    private static Details getDetail(Worker worker, List<Details> details) {
        return details.stream()
                .filter(d -> d.getRutTrab().equals(worker.getRutTrab()))
                .findFirst()
                .orElse(null);
    }

    @Nullable
    private static Quotes getQuotes(Worker worker, List<Quotes> quotes) {
        return quotes.stream()
                .filter(q -> q.getRutTrab().equals(worker.getRutTrab()))
                .findFirst()
                .orElse(null);
    }

}
