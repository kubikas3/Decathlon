package main.com.ovi.Readers.Athlete;

import main.com.ovi.Models.Athlete;
import main.com.ovi.Models.Exceptions.ReaderException;
import main.com.ovi.Utils.StringUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVAthleteReader implements AthleteReader {
    private String filename;
    private BufferedReader reader;
    private final String delimiter;

    public CSVAthleteReader(String fileName, String delimiter) throws ReaderException {
        this.filename = fileName;
        this.delimiter = delimiter;
        try {
            this.reader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            throw new ReaderException("Failed to read athlete input file.", e);
        }
    }

    @Override
    public Athlete read() throws ReaderException {
        String line;
        try {
            line = reader.readLine();

            if (line != null) {
                String[] values = line.trim().split("\\s*" + delimiter + "\\s*");
                String fullName = values[0];
                List<Double> performance = new ArrayList<>();
                int count = values.length - 1;

                for (int i = 0; i < count; i++) {
                    performance.add(validateAndParseValue(values[i + 1]));
                }

                return new Athlete(fullName, performance);
            }
        } catch (IOException e) {
            throw new ReaderException("Failed to read athlete from input file.", e);
        }
        return null;
    }

    @Override
    public List<Athlete> readAll() throws ReaderException {
        ArrayList<Athlete> result = new ArrayList<>();
        Athlete athlete;

        while ((athlete = read()) != null) {
            result.add(athlete);
        }

        return result;
    }

    @Override
    public void close() throws ReaderException {
        try {
            reader.close();
        } catch (IOException e) {
            throw new ReaderException("Failed to close athlete input file.", e);
        }
    }

    private double validateAndParseValue(String value) throws ReaderException {
        String regex = "\\d+(.\\d+){1,2}";

        if (!value.matches(regex)) {
            throw new ReaderException("Performance result value is invalid.");
        }

        long count = StringUtils.countChars(value, '.');

        if (count == 0 || count == 1) {
            return Double.parseDouble(value);
        }

        int index = value.indexOf('.');
        int minute = Integer.parseInt(value.substring(0, index));
        double second = Double.parseDouble(value.substring(index + 1));

        return minute * 60 + second;
    }
}
