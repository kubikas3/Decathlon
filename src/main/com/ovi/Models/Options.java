package main.com.ovi.Models;

import main.com.ovi.Models.Exceptions.OptionsException;
import main.com.ovi.Utils.PathUtils;

import java.util.List;

public class Options {
    private String inputFileName;
    private String outputFileName;

    public Options(List<String> args) throws OptionsException {
        if (args.size() < 4) {
            throw new OptionsException("One or more arguments are missing.");
        }

        int i = args.indexOf("-i");
        if (i < 0) {
            throw new OptionsException("Input file name is missing.");
        }

        inputFileName = args.get(i + 1);
        if (!PathUtils.isValidPath(inputFileName)) {
            throw new OptionsException("Input file name is invalid.");
        }

        int o = args.indexOf("-o");
        if (o < 0) {
            throw new OptionsException("Output file name is missing.");
        }

        outputFileName = args.get(o + 1);
        if (!PathUtils.isValidPath(outputFileName)) {
            throw new OptionsException("Output file name is invalid.");
        }
    }

    public String getInputFileName() {
        return inputFileName;
    }

    public String getOutputFileName() {
        return outputFileName;
    }
}
