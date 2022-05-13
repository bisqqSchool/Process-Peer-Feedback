package sfu.cmpt213.as2;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
* Class to process directories and json files. Includes grouping of similar data
* */

public class ProcessJson {

    public final List<Group> allGroups = new ArrayList<>();
    private final List<File> DIRECTORIES = new ArrayList<>();
    private boolean hasDirectoriesBeenFound = false;
    private final FeedbackManager MANAGER = new FeedbackManager();


    public void startGeneratingCsv(File output) {
        GenerateCsv csv = new GenerateCsv();

        try {
            csv.createCsvFile(allGroups, output, MANAGER);

        } catch (IOException e) {
            e.printStackTrace();
            ErrorHandling.Error(e.getMessage());
        }
    }

    public void addToGroup() {

        for (Feedbacks feedback : FeedbackManager.ALL_FEEDBACKS_LIST) {
            boolean isPlace = false;

            for (Group group : allGroups) {

                if (group.isStudentReferenced(feedback)) {
                    group.addToGroup(feedback);
                    isPlace = true;

                } else if (group.hasStudent(feedback.getName())) {
                    ErrorHandling.Error("Error: Duplicate student found");

                }
            }

            if (!isPlace) {
                Group newGroup = new Group();
                newGroup.addToGroup(feedback);
                allGroups.add(newGroup);
            }

        }
    }

    private void readJsonData(File file) {

        try {

            JsonElement fileElement = JsonParser.parseReader(new FileReader(file));
            JsonObject fileObject = fileElement.getAsJsonObject();

            String confidentialComments = fileObject.get("confidential_comments").getAsString();
            JsonArray jsonGroup = fileObject.get("group").getAsJsonArray();

            for (JsonElement groupElement : jsonGroup) {

                JsonObject groupObject = groupElement.getAsJsonObject();
                String name = groupObject.get("name").getAsString();
                String sfuEmail = groupObject.get("sfu_email").getAsString();

                JsonObject contributionObject = groupObject.get("contribution").getAsJsonObject();
                Double score = contributionObject.get("score").getAsDouble();
                String comment = contributionObject.get("comment").getAsString();

                Feedbacks studentFeedbacks = new Feedbacks(name, sfuEmail, score, comment, confidentialComments);

                MANAGER.addToList(studentFeedbacks);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            ErrorHandling.Error(e.getMessage());
        }
    }

    public void searchForJsonFiles(File filePath) {

        FileFilter jsonfilter = pathname -> pathname.getName().endsWith(".json");

        if (!hasDirectoriesBeenFound) {
            getFolderDirectoriesRecursively(filePath);
            hasDirectoriesBeenFound = true;
        }

        for (File files : DIRECTORIES) {

            File[] fileList = files.listFiles(jsonfilter);

            if (fileList != null) {
                for (File jsonFile : fileList) {
                    readJsonData(jsonFile);
                }
            }
        }
    }

    private void getFolderDirectoriesRecursively(File filePath) {

        File[] paths = filePath.listFiles(File::isDirectory);

        if (!filePath.isDirectory()) {
            ErrorHandling.Error("Error: directory does not exist " + filePath);
        }

        if (paths != null) {
            for (File folders : paths) {
                getFolderDirectoriesRecursively(folders);
            }
        }

        DIRECTORIES.add(filePath);
    }

}
