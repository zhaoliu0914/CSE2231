import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Zhao Liu
 *
 */
public final class Homework2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Homework2() {
    }

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     * @param s2
     *            the resulting sequence
     * @replaces s2
     * @requires |s1| >= 1
     * @ensures <pre>
     * |s2| = |s1| - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * <i> * <j> * b)
     *    (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        s2 = c * <(i+j)/2> * d))
     * </pre>
     */
    public static void smooth(Sequence<Integer> s1, Sequence<Integer> s2) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s2 != null : "Violation of: s2 is not null";
        assert s1.length() >= 1 : "Violation of: the length of s1 "
                + "is greater or equal than 1";

        s2.clear();

        for (int i = 1; i < s1.length(); i++) {
            int firstIndex = i - 1;
            int secondIndex = i;
            Integer first = s1.entry(firstIndex);
            Integer second = s1.entry(secondIndex);

            Integer average = (first + second) / 2;

            s2.add(firstIndex, average);
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        Sequence<Integer> s1 = new Sequence1L<>();
        s1.add(0, 7);
        s1.add(1, 23);
        s1.add(2, 2);
        s1.add(3, 6);
        Sequence<Integer> s2 = new Sequence1L<>();
        s2.add(0, 1);
        s2.add(1, 2);
        s2.add(2, 3);

        smooth(s1, s2);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
