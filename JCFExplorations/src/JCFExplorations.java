import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

import components.naturalnumber.NaturalNumber;

/**
 * Simple class to experiment with the Java Collections Framework and how it
 * compares with the OSU CSE collection components.
 *
 * @author Zhao Liu
 *
 */
public final class JCFExplorations {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private JCFExplorations() {
    }

    /**
     * Raises the salary of all the employees in {@code map} whose name starts
     * with the given {@code initial} by the given {@code raisePercent}.
     *
     * @param map
     *            the name to salary map
     * @param initial
     *            the initial of names of employees to be given a raise
     * @param raisePercent
     *            the raise to be given as a percentage of the current salary
     * @updates map
     * @requires [the salaries in map are positive] and raisePercent > 0
     * @ensures <pre>
     * DOMAIN(map) = DOMAIN(#map)  and
     * [the salaries of the employees in map whose names start with the given
     *  initial have been increased by raisePercent percent (and truncated to
     *  the nearest integer); all other employees have the same salary]
     * </pre>
     */
    public static void giveRaise(components.map.Map<String, Integer> map,
            char initial, int raisePercent) {
        assert map != null : "Violation of: map is not null";
        assert raisePercent > 0 : "Violation of: raisePercent > 0";

        //Map<String, Integer> updatedMap = map.newInstance();
        components.map.Map<String, Integer> tempMap = map.newInstance();
        tempMap.transferFrom(map);

        while (tempMap.size() > 0) {
            components.map.Map.Pair<String, Integer> temp = tempMap.removeAny();
            String name = temp.key();
            int salary = temp.value();

            char firstCharName = name.charAt(0);
            if (firstCharName == initial) {
                salary = salary + (salary * raisePercent / 100);
            }

            map.add(name, salary);
        }
    }

    /**
     * Raises the salary of all the employees in {@code map} whose name starts
     * with the given {@code initial} by the given {@code raisePercent}.
     *
     * @param map
     *            the name to salary map
     * @param initial
     *            the initial of names of employees to be given a raise
     * @param raisePercent
     *            the raise to be given as a percentage of the current salary
     * @updates map
     * @requires <pre>
     * [the salaries in map are positive] and raisePercent > 0 and
     * [the dynamic types of map and of all objects reachable from map
     *  (including any objects returned by operations (such as
     *  entrySet() and, from there, iterator()), and so on,
     *  recursively) support all optional operations]
     * </pre>
     * @ensures <pre>
     * DOMAIN(map) = DOMAIN(#map)  and
     * [the salaries of the employees in map whose names start with the given
     *  initial have been increased by raisePercent percent (and truncated to
     *  the nearest integer); all other employees have the same salary]
     * </pre>
     */
    public static void giveRaise(java.util.Map<String, Integer> map,
            char initial, int raisePercent) {
        assert map != null : "Violation of: map is not null";
        assert raisePercent > 0 : "Violation of: raisePercent > 0";

        java.util.Set<Entry<String, Integer>> entrySet = map.entrySet();
        for (Entry<String, Integer> entry : entrySet) {
            String name = entry.getKey();
            int salary = entry.getValue();

            char firstCharName = name.charAt(0);
            if (firstCharName == initial) {
                salary = salary + (salary * raisePercent / 100);

                entry.setValue(salary);
            }
        }

    }

    /**
     * Increments by 1 every element in the given {@code Set}.
     *
     * @param set
     *            the set whose elements to increment
     * @updates set
     * @ensures <pre>
     * DOMAIN(map) = DOMAIN(#map)  and
     * [set is the set of integers that are elements of #set incremented by 1]
     * </pre>
     */
    public static void incrementAll(components.set.Set<NaturalNumber> set) {
        assert set != null : "Violation of: set is not null";

        components.set.Set<NaturalNumber> tempSet = set.newInstance();
        components.set.Set<NaturalNumber> updatedSet = set.newInstance();

        tempSet.transferFrom(set);

        while (tempSet.size() > 0) {
            NaturalNumber naturalNumber = tempSet.removeAny();
            naturalNumber.increment();

            updatedSet.add(naturalNumber);
        }

        set.transferFrom(updatedSet);
    }

    /**
     * Increments by 1 every element in the given {@code Set}.
     *
     * @param set
     *            the set whose elements to increment
     * @updates set
     * @requires <pre>
     * [the dynamic types of set and of all objects reachable from set
     *  (including any objects returned by operations (such as iterator()), and
     *  so on, recursively) support all optional operations]
     * </pre>
     * @ensures <pre>
     * DOMAIN(map) = DOMAIN(#map)  and
     * [set is the set of integers that are elements of #set incremented by 1]
     * </pre>
     */
    public static void incrementAll(java.util.Set<NaturalNumber> set) {
        assert set != null : "Violation of: set is not null";

        java.util.Set<NaturalNumber> tempSet = new HashSet<>();

        Iterator<NaturalNumber> iterator = set.iterator();
        while (iterator.hasNext()) {
            NaturalNumber naturalNumber = iterator.next();

            iterator.remove();

            naturalNumber.increment();
            tempSet.add(naturalNumber);
        }

        set.addAll(tempSet);
    }

}
