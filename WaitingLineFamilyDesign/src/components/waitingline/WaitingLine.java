package components.waitingline;

import java.util.Comparator;

/**
 * {@code WaitingLineKernel} enhanced with secondary methods.
 *
 * @param <T>
 *            type of {@code WaitingLine} entries
 * @mathdefinitions <pre>
 * IS_TOTAL_PREORDER (
 *   r: binary relation on T
 *  ) : boolean is
 *  for all x, y, z: T
 *   ((r(x, y) or r(y, x))  and
 *    (if (r(x, y) and r(y, z)) then r(x, z)))
 *
 * IS_SORTED (
 *   s: string of T,
 *   r: binary relation on T
 *  ) : boolean is
 *  for all x, y: T where (<x, y> is substring of s) (r(x, y))
 * </pre>
 *
 * @author Zhao Liu
 * @author Ummehani Motiwala
 * @author Deepak Prabaharan
 */
public interface WaitingLine<T> extends WaitingLineKernel<T> {

    /**
     * Reports the front of {@code this}. *
     *
     * @return the front entry of {@code this}
     * @aliases reference returned by {@code front}
     * @requires this /= <>
     * @ensures <front> is prefix of this
     */
    T front();

    /**
     * Returns the front entry of {@code this}. *
     *
     * @return the front entry of {@code this}
     * @updates this
     * @requires this /= <>
     * @ensures #this = <removeFront> * this
     */
    T removeFront();

    /**
     * Returns position in {@code this} of {@code customer}. *
     *
     * @param customer
     *            entry to find position of
     * @return position of {@code customer}
     * @requires this /= <> && {@code customer} is in {@code this}
     * @ensures {@code position} = {@code customer} position in this
     */
    int position(T customer);

    /**
     * Sorts {@code this} according to the ordering provided by the
     * {@code compare} method from {@code order}. *
     *
     * @param order
     *            ordering by which to sort
     * @updates this
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
    * perms(this, #this) and
    * IS_SORTED(this, [relation computed by order.compare method]) * </pre>
     */
    void sort(Comparator<T> order);
}
