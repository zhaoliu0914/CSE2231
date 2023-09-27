import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.regex.Pattern;

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
            implements Comparator<Entry<String, Integer>>, Serializable {
        private static final long serialVersionUID = 1L;

        @Override
        public int compare(Entry<String, Integer> entry1,
                Entry<String, Integer> entry2) {
            String str1 = entry1.getKey();
            String str2 = entry2.getKey();

            int reuslt = compareInAlphabeticalOrder(str1, str2, 0);
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

        // if str1 is same with str2, then return 0.
        if (str1.equals(str2)) {
            return 0;
        }

        int length1 = str1.length();
        int length2 = str2.length();

        // the length of str1 less then str2 means str1 comes before str2, vice versa.
        if (index >= length1) {
            return -1;
        }
        if (index >= length2) {
            return 1;
        }

        int result = 0;

        char char1 = str1.charAt(index);
        char char2 = str2.charAt(index);

        // word with lower case comes before word with upper case.
        // if word in char has same lower and upper case and same char,
        // then recursive call to check next char.
        if (Character.isLowerCase(char1) && Character.isLowerCase(char2)) {
            result = Character.compare(char1, char2);

            // recursive call.
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

            // recursive call.
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
            implements Comparator<Entry<String, Integer>>, Serializable {
        private static final long serialVersionUID = 1L;

        @Override
        public int compare(Entry<String, Integer> o1,
                Entry<String, Integer> o2) {
            int count1 = o1.getValue();
            int count2 = o2.getValue();
            return count2 - count1;
        }

    }

    /**
     * Simply, read all the lines in the file and then re-store into a List.
     *
     * @param inputFileName
     *            from user enter
     * @return the List with all the lines from file, or null when can not read
     *         input file.
     */
    public static List<String> convertInputFileToQueue(String inputFileName) {
        assert inputFileName != null : "Violation of: inputFileName is not null";

        List<String> sentenceList = new ArrayList<>();

        // initial input file stream.
        BufferedReader bufferedReader = null;
        try {
            FileReader fileReader = new FileReader(inputFileName);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            System.err.println("input file " + inputFileName
                    + " does not exist or can not be read.");
            // if file does not exist or unreadable, then return null.
            return null;
        }

        // read all lines from input file to List<String>
        String line;
        try {
            line = bufferedReader.readLine();
            while (line != null) {
                sentenceList.add(line);

                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.err.println(
                    "input file " + inputFileName + " can not be read.");
        }

        // close IO stream.
        try {
            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("Unable to close input file " + inputFileName);
        }

        return sentenceList;
    }

    /**
     * loop every sentences and then split sentence into words based on
     * separators.
     *
     * separator means " \"\t\n\r,-.!?[]';:/()`*".
     *
     * count every words and store into a map which key is word and value their
     * corresponding counts.
     *
     * @param sentenceList
     *            a List contains all the lines from file
     * @return a map, key is word and value is their corresponding counts
     */
    public static Map<String, Integer> countWords(List<String> sentenceList) {
        assert sentenceList != null : "Violation of: sentenceList is not null";

        Map<String, Integer> wordsMap = new HashMap<>();

        String separators = " \"\t\n\r,-.!?[]';:/()`*";
        List<String> separatorSet = new ArrayList<>();
        for (int i = 0; i < separators.length(); i++) {
            char separator = separators.charAt(i);
            separatorSet.add(String.valueOf(separator));
        }

        // loop every sentences and then split sentence into words based on separators
        // count every words and
        // store into a map which key is word and value is their corresponding counts
        for (String sentence : sentenceList) {

            List<String> wordsList = new ArrayList<>();
            wordsList.add(sentence);

            // split sentence based on separator
            for (String separator : separatorSet) {
                List<String> tempList = new ArrayList<>();

                for (String word : wordsList) {
                    //String[] tokens = word.split(separator);
                    String[] tokens = word.split(Pattern.quote(separator));

                    for (String token : tokens) {
                        tempList.add(token.toLowerCase());
                    }
                }

                wordsList.clear();
                wordsList.addAll(tempList);
            }

            // count every words and store word and counts into a map
            for (String word : wordsList) {
                if (wordsMap.containsKey(word)) {
                    int count = wordsMap.get(word);
                    //wordsMap.replaceValue(word, count + 1);
                    wordsMap.replace(word, count + 1);
                } else {
                    //wordsMap.add(word, 1);
                    wordsMap.put(word, 1);
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
     * @return a sorted List in top numbers in alphabetical order.
     */
    public static List<Entry<String, Integer>> getTopNumberOfWords(
            Map<String, Integer> wordsMap, int number,
            Map<String, Integer> fontSizeMap) {
        assert wordsMap != null : "Violation of: wordsMap is not null";
        assert fontSizeMap != null : "Violation of: fontSizeMap is not null";
        assert number > 0 : "Violation of: number is greater than 0";

        List<Entry<String, Integer>> topNumberList = new ArrayList<>();
        List<Entry<String, Integer>> wordsList = new ArrayList<>();

        // transform all words from Map into List {@code wordsList}
        java.util.Set<Entry<String, Integer>> wordsEntrySet = wordsMap
                .entrySet();
        for (Entry<String, Integer> wordsEntry : wordsEntrySet) {
            wordsList.add(wordsEntry);
        }

        // Sort the List by the value (which is count) in decreasing order.
        OrderByValue orderByValue = new OrderByValue();
        Collections.sort(wordsList, orderByValue);

        // pick up {@code number} of elements into List
        int index = 0;
        int minimalCount = Integer.MAX_VALUE;
        int maximalCount = 0;
        while (index < number && wordsList.size() > 0) {
            Entry<String, Integer> element = wordsList.get(index);

            int count = element.getValue();
            if (count < minimalCount) {
                minimalCount = count;
            }
            if (count > maximalCount) {
                maximalCount = count;
            }

            topNumberList.add(element);

            index++;
        }

        // sort it in alphabetical order
        OrderByKey orderByKey = new OrderByKey();
        Collections.sort(topNumberList, orderByKey);

        // set up font size map.
        // defines font sizes f11 through f48
        fontSizeMap.clear();
        final int minimalFontSize = 11;
        final int span = 48 - minimalFontSize;
        for (Entry<String, Integer> entry : topNumberList) {
            String word = entry.getKey();
            int count = entry.getValue();

            int fontSize = span * (count - minimalCount)
                    / (maximalCount - minimalCount);

            fontSize = fontSize + minimalFontSize;
            fontSizeMap.put(word, fontSize);
        }

        return topNumberList;
    }

    /**
     * Output HTML file
     *
     * The words should list in alphabetical order.
     *
     * Words contain no whitespace characters.
     *
     * @param topNumberList
     *            a top number List and sorted in alphabetical order.
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
            List<Entry<String, Integer>> topNumberList,
            Map<String, Integer> fontSizeMap, String inputFile,
            String outputFile, int numberWords) {
        assert topNumberList != null : "Violation of: topNumberList is not null";
        assert inputFile != null : "Violation of: inputFile is not null";
        assert outputFile != null : "Violation of: outputFile is not null";
        assert numberWords > 0 : "Violation of: numberWords is greater than 0";

        BufferedWriter bufferedWriter = null;
        try {
            FileWriter fileWriter = new FileWriter(outputFile);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            System.err.println(
                    "Unable to create or open output file " + outputFile);
            return;
        }

        // output HTML file
        try {
            // write <head> and <title>
            bufferedWriter.write("<html>\n");
            bufferedWriter.write("<head>\n");
            bufferedWriter.write("<title>Top " + numberWords + " words in "
                    + inputFile + "</title>\n");
            bufferedWriter.write(
                    "<link href=\"http://web.cse.ohio-state.edu/software/2231/web-sw2/assignments/projects/tag-cloud-generator/data/tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">\n");
            bufferedWriter.write(
                    "<link href=\"tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">\n");
            bufferedWriter.write("</head>\n");
            bufferedWriter.write("<body>\n");
            bufferedWriter.write("<h2>Top " + numberWords + " words in "
                    + inputFile + "</h2>\n");
            bufferedWriter.write("<hr>\n");
            bufferedWriter.write("<div class=\"cdiv\">\n");
            bufferedWriter.write("<p class=\"cbox\">\n");

            // write {@code number} of elements
            for (Entry<String, Integer> entry : topNumberList) {
                String word = entry.getKey();
                int count = entry.getValue();
                int fontSize = fontSizeMap.get(word);

                bufferedWriter.write("<span style=\"cursor:default\" class=\"f"
                        + fontSize + "\" title=\"count: " + count + "\">" + word
                        + "</span>\n");
            }

            bufferedWriter.write("</p>\n");
            bufferedWriter.write("</div>\n");
            bufferedWriter.write("</body>\n");
            bufferedWriter.write("</html>\n");
        } catch (IOException e) {
            System.err.println("Unable to write output file " + outputFile);
        }

        try {
            bufferedWriter.close();
        } catch (IOException e) {
            System.err.println("Unable to close output file " + outputFile);
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // get file name from user.
        System.out.print(
                "Please enter name of input file in either relative or absolute path: ");
        String inputFile = in.nextLine();

        // get output file from user.
        System.out.print("Please enter name of an output HTML file in "
                + "either relative or absolute path: ");
        String outputFile = in.nextLine();

        System.out.print("Please enter the number of words to be counted: ");
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

        // read all the lines into contents List.
        List<String> sentenceList = convertInputFileToQueue(inputFile);
        if (sentenceList != null) {
            // convert these lines into a map.
            // key is word, and value is their corresponding counts.
            Map<String, Integer> wordsMap = countWords(sentenceList);

            Map<String, Integer> fontSizeMap = new HashMap<>();

            List<Entry<String, Integer>> topNumberList = getTopNumberOfWords(
                    wordsMap, numberWords, fontSizeMap);

            // write all the words into HTML file.
            writeWordsToHTML(topNumberList, fontSizeMap, inputFile, outputFile,
                    numberWords);
        }

        /*
         * Close input and output streams
         */
        in.close();
    }

}
