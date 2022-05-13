package sfu.cmpt213.as2;

import java.util.ArrayList;
import java.util.List;

/*
 * Manager class for managing the feedbacks collected through json.
 * Also includes a function to retrieve data from a selected feedback.
 * */

public class FeedbackManager {

    public static final List<Feedbacks> ALL_FEEDBACKS_LIST = new ArrayList<>();

    public void addToList(Feedbacks feedback) {
        ALL_FEEDBACKS_LIST.add(feedback);
        ALL_FEEDBACKS_LIST.sort((o1, o2) -> o1.getEmail().compareToIgnoreCase(o2.getEmail()));
    }

    public Feedbacks getFeedbackFromList() {
        for (Feedbacks feedback : ALL_FEEDBACKS_LIST) {

            String getComment = feedback.getComment();
            boolean hasNameInComment = getComment.contains(feedback.getName());

            if (hasNameInComment) {
                return feedback;
            }
        }
        return null;
    }
}


