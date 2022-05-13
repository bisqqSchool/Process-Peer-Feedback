package sfu.cmpt213.as2;

import java.util.ArrayList;
import java.util.List;

/*
 * Group class that stores feedbacks into groups. Class contains a list
 * containing the assigned feedbacks. It also has methods that check to see
 * if the student feedback shows up in a certain group.
 * */

public class Group {

    private final List<Feedbacks> STUDENTS_GROUP;

    public Group() {

        this.STUDENTS_GROUP = new ArrayList<>();
    }

    public void addToGroup(Feedbacks student) {
        STUDENTS_GROUP.add(student);
        STUDENTS_GROUP.sort((o1, o2) -> o1.getEmail().compareToIgnoreCase(o2.getEmail()));
    }

    public boolean isStudentReferenced(Feedbacks studentFeedback) {
        for (Feedbacks feedback : STUDENTS_GROUP) {

            String getComment = feedback.getComment();
            boolean hasNameInComment = getComment.contains(studentFeedback.getName());

            if (hasNameInComment) {
                return true;
            }
        }
        return false;
    }

    public boolean hasStudent(String name) {
        for (Feedbacks feedback : STUDENTS_GROUP) {

            boolean areNamesEqual = feedback.getName().equalsIgnoreCase(name);
            if (areNamesEqual) {
                return true;
            }
        }
        return false;
    }

    public List<Feedbacks> getFeedbackGroup() {

        return STUDENTS_GROUP;
    }
}
