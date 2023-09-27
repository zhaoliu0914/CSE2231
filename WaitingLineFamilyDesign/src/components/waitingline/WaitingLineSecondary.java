package components.waitingline;

import java.util.Iterator;

/**
 * Layered implementations of secondary methods for {@code WaitingLine}.
 *
 * @param <T>
 *            type of {@code WaitingLine} entries
 * 
 * @author Zhao Liu
 * @author Ummehani Motiwala
 * @author Deepak Prabaharan
 */
public abstract class WaitingLineSecondary<T> implements WaitingLine<T> {

    /**
     * equals in class Object.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof WaitingLine)) {
            return false;
        }
        if (this == obj) {
            return true;
        }

        WaitingLine<T> waitingLine = (WaitingLine) obj;
        if (this.length() != waitingLine.length()) {
            return false;
        }

        boolean isSame = true;
        Iterator<T> thisIterator = this.iterator();
        Iterator<T> wlIterator = waitingLine.iterator();

        while (thisIterator.hasNext() && isSame) {
            T thisElement = thisIterator.next();
            T wlElement = wlIterator.next();

            if (!thisElement.equals(wlElement)) {
                isSame = false;
            }
        }

        return isSame;
    }

    /**
     * hashCode in class Object.
     */
    @Override
    public int hashCode() {
        int code = 0;
        Iterator<T> thisIterator = this.iterator();
        while (thisIterator.hasNext()) {
            T thisElement = thisIterator.next();

            int size = thisElement.toString().length();
            for (int i = 0; i < size; i++) {
                char temp = thisElement.toString().charAt(i);
                code = code + temp;
            }
        }

        return code;
    }

    /**
     * toString in class Object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<");

        Iterator<T> thisIterator = this.iterator();
        while (thisIterator.hasNext()) {
            T thisElement = thisIterator.next();

            sb.append(thisElement);

            if (thisIterator.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(">");

        return sb.toString();
    }

}
