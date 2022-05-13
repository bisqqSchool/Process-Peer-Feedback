package sfu.cmpt213.as2;

public class ErrorHandling {
    private static final int FAILURE = -1;

    public static void Error(String err) {
        System.out.println(err);
        System.exit(FAILURE);
    }
}
