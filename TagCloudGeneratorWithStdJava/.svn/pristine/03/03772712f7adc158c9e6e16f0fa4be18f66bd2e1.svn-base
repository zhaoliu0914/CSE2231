import java.io.Serializable;
import java.util.Comparator;
import java.util.regex.Pattern;

import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.queue.Queue2;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.sortingmachine.SortingMachine;
import components.sortingmachine.SortingMachine1L;
import components.utilities.Reporter;

/**
 * The program shall ask the user for the name of an input file, for the name of
 * an output file, and for the number of words to be included in the generated
 * tag cloud (a positive integer, say N).
 *
 * The program shall respect the user input as being the complete relative or
 * absolute path as the name of the input file, or the name of the output file,
 * and will not augment the given path in any way, e.g., it will not supply its
 * own filename extension. For example, a reasonable user response for the name
 * of the input file could directly result in the String value
 * "data/importance.txt"; similarly, a reasonable user response for the name of
 * the output file could directly result in the String value
 * "data/importance.html".
 *
 * @author Zhao Liu
 * @author Zishu Ling
 *
 */
public final class TagCloudGenerator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private TagCloudGenerator() {
    }

    /**
     * Compare {@code String}s in lexicographic order of key.
     *
     * The words shall appear in alphabetical order (in which, e.g., "bar" comes
     * before "Foo", not the lexicographic order provided by the String
     * compareTo method which would put capitalized words ahead of lower case
     * ones, e.g., "Foo" would come before "bar").
     */
    private static class OrderByKey
            implements Comparator<Map.Pair<String, Integer>>, Serializable {
        private static final long serialVersionUID = 1L;

        @Override
        public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
            String key1 = o1.key();
            String key2 = o2.key();

            int reuslt = compareInAlphabeticalOrder(key1, key2, 0);
            return reuslt;
        }

    }

    /**
     * Compare {@code str1} and {@code str2} in in alphabetical order. e.g.,
     * "bar" comes before "Foo".
     *
     * @param str1
     *            first argument.
     * @param str2
     *            second argument.
     * @param index
     *            index of string.
     * @return negative: str1 < str2, 0: str1 == str2, position: str1 > str2
     */
    private static int compareInAlphabeticalOrder(String str1, String str2,
            int index) {
        assert str1 != null : "Violation of: str1 is not null.";
        assert str1.length() > 0 : "Violation of: str1 is not empty.";
        assert str2 != null : "Violation of: str2 is not null.";
        assert str2.length() > 0 : "Violation of: str2 is not empty.";
        assert index >= 0 : "Violation of: index is greater than 0.";

        if (str1.equals(str2)) {
            return 0;
        }

        int length1 = str1.length();
        int length2 = str2.length();

        if (index >= length1) {
            return -1;
        }
        if (index >= length2) {
            return 1;
        }

        int result = 0;

        char char1 = str1.charAt(index);
        char char2 = str2.charAt(index);

        if (Character.isLowerCase(char1) && Character.isLowerCase(char2)) {
            result = Character.compare(char1, char2);

            if (result == 0) {
                result = compareInAlphabeticalOrder(str1, str2, index + 1);
            }
        } else if (Character.isLowerCase(char1)
                && Character.isUpperCase(char2)) {
            result = -1;

        } else if (Character.isUpperCase(char1)
                && Character.isLowerCase(char2)) {
            result = 1;

        } else {
            result = Character.compare(char1, char2);
            if (result == 0) {
                result = compareInAlphabeticalOrder(str1, str2, index + 1);
            }
        }

        return result;
    }

    /**
     * Compare {@code String}s in decreasing order of value.
     */
    private static class OrderByValue
            implements Comparator<Map.Pair<String, Integer>>, Serializable {
        private static final long serialVersionUID = 1L;

        @Override
        public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
            int count1 = o1.value();
            int count2 = o2.value();
            return count2 - count1;
        }

    }

    /**
     * Simply, read all the lines in the file and then re-store into a Queue.
     *
     * @param inputFile
     *            from user enter
     * @return the Queue with all the lines from file.
     */
    public static Queue<String> convertInputFileToQueue(
            SimpleReader inputFile) {
        assert inputFile != null : "Violation of: inputFile is not null";
        assert inputFile.isOpen() : "Violation of: inputFile is not open";

        Queue<String> sentences = new Queue2<>();

        String line;
        while (!inputFile.atEOS()) {
            line = inputFile.nextLine();
            sentences.enqueue(line);
        }

        return sentences;
    }

    /**
     * loop every sentences and then split sentence into words based on
     * separators.
     *
     * separator means char from index 32 to 64 in ASCII Code.
     *
     * count every words and store into a map which key is word and value their
     * corresponding counts.
     *
     * @param sentences
     *            a Queue contains all the lines from file
     * @return a map, key is word and value is their corresponding counts
     */
    public static Map<String, Integer> countWords(Queue<String> sentences) {
        assert sentences != null : "Violation of: sentences is not null";

        Map<String, Integer> wordsMap = new Map1L<>();

        // the separator means char from index 32 to 64 in ASCII Code.
        String separators = " \"\t\n\r,-.!?[]';:/()`*";
        Set<String> separatorSet = new Set1L<>();
        //final int fromIndex = 32;
        //final int endIndex = 64;
        for (int i = 0; i < separators.length(); i++) {
            char separator = separators.charAt(i);
            separatorSet.add(String.valueOf(separator));
        }

        // loop every sentences and then split sentence into words based on separators
        // count every words and
        // store into a map which key is word and value is their corresponding counts
        for (String sentence : sentences) {

            Queue<String> wordsQueue = new Queue1L<>();
            wordsQueue.enqueue(sentence);

            // split sentence based on separator
            for (String separator : separatorSet) {
                Queue<String> tempQueue = new Queue1L<>();

                for (String word : wordsQueue) {
                    //String[] tokens = word.split(separator);
                    String[] tokens = word.split(Pattern.quote(separator));

                    for (String token : tokens) {
                        tempQueue.enqueue(token.toLowerCase());
                    }
                }

                wordsQueue.clear();
                wordsQueue.transferFrom(tempQueue);
            }

            // count every words and store word and counts into a map
            for (String word : wordsQueue) {
                if (wordsMap.hasKey(word)) {
                    int count = wordsMap.value(word);
                    wordsMap.replaceValue(word, count + 1);
                } else {
                    wordsMap.add(word, 1);
                }
            }
        }

        // remove word which is empty text.
        if (wordsMap.size() > 0) {
            wordsMap.remove("");
        }

        return wordsMap;
    }

    /**
     * return top {@code number} from {@code wordsMap}.
     *
     * @param wordsMap
     *            key is word, value is their corresponding counts.
     * @param number
     *            top numbers.
     * @param fontSizeMap
     *            key is word, value is font size.
     * @return a sorted Map in alphabetical order
     */
    public static Map<String, Integer> getTopNumberOfWords(
            Map<String, Integer> wordsMap, int number,
            Map<String, Integer> fontSizeMap) {
        assert wordsMap != null : "Violation of: wordsMap is not null";
        assert fontSizeMap != null : "Violation of: fontSizeMap is not null";
        assert number > 0 : "Violation of: number is greater than 0";

        OrderByValue orderByValue = new OrderByValue();
        SortingMachine<Map.Pair<String, Integer>> sortingMachine = new SortingMachine1L<>(
                orderByValue);

        for (Map.Pair<String, Integer> pair : wordsMap) {
            sortingMachine.add(pair);
        }

        Map<String, Integer> topNumberMap = wordsMap.newInstance();

        sortingMachine.changeToExtractionMode();

        int index = 0;
        int minimalCount = Integer.MAX_VALUE;
        int maximalCount = 0;
        while (index < number && sortingMachine.size() > 0) {
            Map.Pair<String, Integer> element = sortingMachine.removeFirst();

            int count = element.value();
            if (count < minimalCount) {
                minimalCount = count;
            }
            if (count > maximalCount) {
                maximalCount = count;
            }

            topNumberMap.add(element.key(), element.value());

            index++;
        }

        // set up font size map.
        // defines font sizes f11 through f48
        fontSizeMap.clear();
        final int minimalFontSize = 11;
        final int span = 48 - minimalFontSize;
        for (Map.Pair<String, Integer> pair : topNumberMap) {
            String key = pair.key();
            int count = pair.value();

            int fontSize = span * (count - minimalCount)
                    / (maximalCount - minimalCount);

            fontSize = fontSize + minimalFontSize;
            fontSizeMap.add(key, fontSize);
        }

        return topNumberMap;
    }

    /**
     * sort words (which is key from {@code wordsMap}) in alphabetical order.
     *
     * @param wordsMap
     *            key is word, value is their corresponding counts.
     * @return SortingMachine, a SortingMachine in alphabetical order
     */
    public static SortingMachine<Map.Pair<String, Integer>> sortWordsInAlphabetical(
            Map<String, Integer> wordsMap) {
        assert wordsMap != null : "Violation of: wordsMap is not null";

        // sort in alphabetical order
        OrderByKey orderByKey = new OrderByKey();

        SortingMachine<Map.Pair<String, Integer>> sortingMachine = new SortingMachine1L<>(
                orderByKey);

        while (wordsMap.size() > 0) {
            Map.Pair<String, Integer> element = wordsMap.removeAny();

            sortingMachine.add(element);
        }
        return sortingMachine;
    }

    /**
     * Output HTML file
     *
     * The words should list in alphabetical order.
     *
     * Words contain no whitespace characters.
     *
     * @param sortingMachine
     *            a SortingMachine in alphabetical order
     * @param fontSizeMap
     *            key is word, value is font size.
     * @param inputFile
     *            a input file from user enter
     * @param outputFile
     *            a output file from user enter
     * @param numberWords
     *            the number of words to be included in the generated tag cloud
     */
    public static void writeWordsToHTML(
            SortingMachine<Map.Pair<String, Integer>> sortingMachine,
            Map<String, Integer> fontSizeMap, String inputFile,
            String outputFile, int numberWords) {
        assert sortingMachine != null : "Violation of: sortingMachine is not null";
        assert inputFile != null : "Violation of: inputFile is not null";
        assert outputFile != null : "Violation of: outputFile is not null";
        assert numberWords > 0 : "Violation of: numberWords is greater than 0";

        // output HTML file
        SimpleWriter outputWriter = new SimpleWriter1L(outputFile);

        outputWriter.println("<html>");
        outputWriter.println("<head>");
        outputWriter.println("<title>Top " + numberWords + " words in "
                + inputFile + "</title>");
        outputWriter.println(
                "<link href=\"http://web.cse.ohio-state.edu/software/2231/web-sw2/assignments/projects/tag-cloud-generator/data/tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">");
        outputWriter.println(
                "<link href=\"tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">");
        outputWriter.println("</head>");
        outputWriter.println("<body>");
        outputWriter.println(
                "<h2>Top " + numberWords + " words in " + inputFile + "</h2>");
        outputWriter.println("<hr>");
        outputWriter.println("<div class=\"cdiv\">");
        outputWriter.println("<p class=\"cbox\">");

        sortingMachine.changeToExtractionMode();
        while (sortingMachine.size() > 0) {
            Map.Pair<String, Integer> pair = sortingMachine.removeFirst();
            String word = pair.key();
            int count = pair.value();

            int fontSize = fontSizeMap.value(word);

            outputWriter.println("<span style=\"cursor:default\" class=\"f"
                    + fontSize + "\" title=\"count: " + count + "\">" + word
                    + "</span>");
        }

        outputWriter.println("</p>");
        outputWriter.println("</div>");
        outputWriter.println("</body>");
        outputWriter.println("</html>");

        outputWriter.close();
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

        //int result = compareInAlphabeticalOrder("aBc", "abC", 0);
        //System.out.println("result = " + result);

        // get file name from user.
        out.print(
                "Please enter name of input file in either relative or absolute path: ");
        String inputFile = in.nextLine();

        // get output file from user.
        out.print("Please enter name of an output HTML file in "
                + "either relative or absolute path: ");
        String outputFile = in.nextLine();

        out.print("Please enter the number of words to be counted: ");
        String numberWordsAsStr = in.nextLine();

        if (numberWordsAsStr == null || numberWordsAsStr.isEmpty()) {
            Reporter.assertElseFatalError(false, "Please enter a valid number, "
                    + numberWordsAsStr + " is not a valid number");
        }
        boolean isNumeric = true;
        for (int i = 0; i < numberWordsAsStr.length(); i++) {
            char tempChar = numberWordsAsStr.charAt(i);
            if (!Character.isDigit(tempChar)) {
                isNumeric = false;
            }
        }
        int numberWords = 0;
        if (isNumeric) {
            numberWords = Integer.parseInt(numberWordsAsStr);
        } else {
            Reporter.assertElseFatalError(false, "Please enter a valid number, "
                    + numberWordsAsStr + " is not a valid number");
        }

        // initializing input file and output writer
        SimpleReader inputReader = new SimpleReader1L(inputFile);

        // read all the lines into contents queue.
        Queue<String> sentences = convertInputFileToQueue(inputReader);

        // convert these lines into a map.
        // key is word, and value is their corresponding counts.
        Map<String, Integer> wordsMap = countWords(sentences);

        Map<String, Integer> fontSizeMap = wordsMap.newInstance();

        Map<String, Integer> topNumberMap = getTopNumberOfWords(wordsMap,
                numberWords, fontSizeMap);

        // store all the key into Queue and sort it in alphabetical order
        SortingMachine<Map.Pair<String, Integer>> sortingMachine = sortWordsInAlphabetical(
                topNumberMap);

        // write all the words into HTML file.
        writeWordsToHTML(sortingMachine, fontSizeMap, inputFile, outputFile,
                numberWords);

        /*
         * Close input and output streams
         */
        inputReader.close();
        in.close();
        out.close();
    }

}
