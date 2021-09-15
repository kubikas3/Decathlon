package main.com.ovi;

import main.com.ovi.Models.Athlete;
import main.com.ovi.Models.Decathlon;
import main.com.ovi.Models.Events.Event;
import main.com.ovi.Models.Exceptions.DecathlonException;
import main.com.ovi.Models.Exceptions.OptionsException;
import main.com.ovi.Models.Exceptions.ReaderException;
import main.com.ovi.Models.Exceptions.WriterException;
import main.com.ovi.Models.Options;
import main.com.ovi.Models.Rank;
import main.com.ovi.Readers.Athlete.CSVAthleteReader;
import main.com.ovi.Readers.Event.CSVEventReader;
import main.com.ovi.Writers.XMLAthleteWriter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        try {
            Options options = new Options(Arrays.asList(args));

            CSVEventReader eventReader = new CSVEventReader("events.txt", ";");
            List<Event> events = eventReader.readAll();
            eventReader.close();

            Decathlon decathlon = new Decathlon(events);

            CSVAthleteReader reader = new CSVAthleteReader(options.getInputFileName(), ";");
            List<Athlete> athletes = reader.readAll();
            reader.close();

            for (Athlete a : athletes) {
                a.setMark(decathlon.getMark(a.getPerformance()));
            }

            athletes.sort(Comparator.comparing(Athlete::getMark));

            for (Athlete a : athletes) {
                int start = athletes.indexOf(a) + 1;
                int end = athletes.lastIndexOf(a) + 1;

                Rank rank = new Rank(start, end);
                a.setRank(rank);
            }

            XMLAthleteWriter writer = new XMLAthleteWriter(options.getOutputFileName(), true);
            writer.write(athletes);
            System.out.println("Done");
        } catch (OptionsException e) {
            System.err.println(e.getMessage());
            System.out.println("Please specify input and output filenames.");
            System.out.println("Usage: -i input.txt -o output.xml");
        } catch (DecathlonException | WriterException | ReaderException e) {
            System.err.println(e.getMessage());
        }
    }
}
