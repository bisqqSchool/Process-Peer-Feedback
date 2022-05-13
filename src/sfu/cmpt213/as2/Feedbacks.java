package sfu.cmpt213.as2;

/*
 * A generic class to store all Feedbacks. The class also contains
 * a list to store all feedbacks found.
 *
 * */

public class Feedbacks {
    private final String NAME;
    private final String EMAIL;
    private final double SCORE;
    private final String COMMENT;
    private final String CONFIDENTIAL_COMMENT;

    public Feedbacks(String name, String email, Double score, String comment, String confidentialComment) {
        this.NAME = name;
        this.EMAIL = email.trim();
        this.SCORE = score;
        this.COMMENT = comment;
        this.CONFIDENTIAL_COMMENT = confidentialComment;
    }

    public String getName() {

        return NAME;
    }

    public String getEmail() {

        return EMAIL;
    }

    public double getScore() {

        return SCORE;
    }

    public String getComment() {

        return COMMENT;
    }

    public String getConfidentialComment() {

        return CONFIDENTIAL_COMMENT;
    }

    @Override
    public String toString() {
        return "Groups{" +
                "name='" + NAME + '\'' +
                ", email='" + EMAIL + '\'' +
                ", score=" + SCORE +
                ", comment='" + COMMENT + '\'' +
                ", confidentialComment='" + CONFIDENTIAL_COMMENT + '\'' +
                '}';
    }
}
