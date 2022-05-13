package sfu.cmpt213.as2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/*
 * Generic class to generate a csv file.
 * */

public class GenerateCsv {

    private void internalLoop(Group group, PrintWriter outputWrite, FeedbackManager manager) {
        for (Feedbacks feedback : group.getFeedbackGroup()) {

            double score = manager.getFeedbackFromList().getScore();
            String comment = manager.getFeedbackFromList().getComment();
            String email = manager.getFeedbackFromList().getEmail();

            int numberOfComments = 0;
            int scoreSum = 0;
            double avgScore = 0;

            boolean isSelf = manager.getFeedbackFromList().getComment().matches(feedback.getComment());
            if (!isSelf) {
                outputWrite.printf(",%s,%s,%.1f,\"%s\",,%n",
                        email,
                        feedback.getEmail(),
                        score,
                        comment,
                        ""
                );

                scoreSum += score;
                numberOfComments++;

                avgScore = scoreSum / numberOfComments;
            }

            outputWrite.printf(",-->,%s,%.1f,\"%s\"%n",
                    email,
                    feedback.getScore(),
                    feedback.getComment()
            );

            outputWrite.printf(",-->,%s,avg %.1f /%d,,,\"%s\"%n",
                    email,
                    avgScore,
                    numberOfComments,
                    manager.getFeedbackFromList().getConfidentialComment()
            );
        }
    }

    public void createCsvFile(List<Group> groupedStudents, File output, FeedbackManager manager) throws IOException {

        if (!output.getParentFile().exists()) {
            ErrorHandling.Error("Error: output folder does not exist");
        }

        try {

            File folder = output.getParentFile();

            PrintWriter outputWrite = new PrintWriter(output);
            outputWrite.println("Group#,Source Student,Target Student,Score,Comment,,Private");

            int groupNumber = 0;

            for (Group group : groupedStudents) {
                groupNumber++;
                outputWrite.println("Group" + groupNumber);

                internalLoop(group, outputWrite, manager);

                outputWrite.println("");
            }

            outputWrite.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            ErrorHandling.Error(e.getMessage());
        }
    }

}
