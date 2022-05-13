package sfu.cmpt213.as2;

import java.io.File;

/*
 * Main class where the user input comes through the main function.
 * The main class also calls the main functions responsible for
 * gathering the json data and generating a csv.
 * */

public class Main {

    public static void main(String[] args) {

        if (args.length != 2) {
            ErrorHandling.Error("Error: Only 2 paths are permitted. First path is your Json file path and " +
                    "the second is the location where a CSV will be generated");
        }

        File jsonInput = new File(args[0]);
        File outputLocation = new File(args[1]);
        File csvOutput = new File(outputLocation + "/group_feedback.csv");

        boolean isValidDirectory = (!jsonInput.isDirectory()) || (!outputLocation.isDirectory());
        if (isValidDirectory) {
            ErrorHandling.Error("Error: Directory is not valid");
        }

        // Recursively search json
        ProcessJson json = new ProcessJson();
        json.searchForJsonFiles(jsonInput);
        json.addToGroup();

        // Generate Csv
        json.startGeneratingCsv(csvOutput);
    }
}
