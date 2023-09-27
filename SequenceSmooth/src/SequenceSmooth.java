import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Implements method to smooth a {@code Sequence<Integer>}.
 *
 * @author Zhao Liu
 *
 */
public final class SequenceSmooth {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SequenceSmooth() {
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
        assert s1 != s2 : "Violation of: s1 is not s2";
        assert s1.length() >= 1 : "Violation of: |s1| >= 1";

        s2.clear();

        for (int i = 1; i < s1.length(); i++) {
            int firstIndex = i - 1;
            int secondIndex = i;
            Integer first = s1.entry(firstIndex);
            Integer second = s1.entry(secondIndex);

            Integer firstHalf = first / 2;
            Integer secondHalf = second / 2;

            Integer correction = 0;
            if (first > 0 && second > 0 && first % 2 != 0 && second % 2 != 0) {
                correction = 1;
            }
            if (first < 0 && second < 0 && first % 2 != 0 && second % 2 != 0) {
                correction = -1;
            }

            Integer average = firstHalf + secondHalf + correction;

            s2.add(firstIndex, average);
        }
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
    public static void smoothWithRecursion(Sequence<Integer> s1,
            Sequence<Integer> s2) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s2 != null : "Violation of: s2 is not null";
        assert s1 != s2 : "Violation of: s1 is not s2";
        assert s1.length() >= 1 : "Violation of: |s1| >= 1";

        s2.clear();

        if (s1.length() > 1) {
            Integer first = s1.entry(0);
            Integer second = s1.entry(1);

            Integer firstHalf = first / 2;
            Integer secondHalf = second / 2;

            Integer correction = 0;
            if (first > 0 && second > 0 && first % 2 != 0 && second % 2 != 0) {
                correction = 1;
            }
            if (first < 0 && second < 0 && first % 2 != 0 && second % 2 != 0) {
                correction = -1;
            }

            Integer average = firstHalf + secondHalf + correction;

            s1.remove(0);

            // call recursive.
            smoothWithRecursion(s1, s2);

            s2.add(0, average);
            // restore s1
            s1.add(0, first);
        }
    }

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     * @return the sequence {@code Sequence<Integer>}
     * @requires |s1| >= 1
     * @ensures <pre>
     * |smoothWithRecursion| = |s1| - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * <i> * <j> * b)
     *    (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        smoothWithRecursion = c * <(i+j)/2> * d))
     * </pre>
     */
    public static Sequence<Integer> smoothWithRecursion(Sequence<Integer> s1) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s1.length() >= 1 : "Violation of: the length of s1"
                + " is greater or equal than 1";

        Sequence<Integer> s2;

        if (s1.length() > 1) {
            Integer first = s1.entry(0);
            Integer second = s1.entry(1);

            Integer firstHalf = first / 2;
            Integer secondHalf = second / 2;

            Integer correction = 0;
            if (first > 0 && second > 0 && first % 2 != 0 && second % 2 != 0) {
                correction = 1;
            }
            if (first < 0 && second < 0 && first % 2 != 0 && second % 2 != 0) {
                correction = -1;
            }

            Integer average = firstHalf + secondHalf + correction;

            s1.remove(0);

            // call recursive.
            s2 = smoothWithRecursion(s1);

            s2.add(0, average);
            // restore s1
            s1.add(0, first);
        } else {
            s2 = new Sequence1L<>();
        }

        return s2;
    }

}
