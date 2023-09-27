import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program to copy a text file into another file.
 *
 * @author Put your name here
 *
 */
public final class CopyFileStdJava {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CopyFileStdJava() {
    }

    /**
     *
     */
    public static boolean copyFileByOSU(String inputFile, String outputFile) {
        SimpleReader simpleReader = new SimpleReader1L(inputFile);
        SimpleWriter simpleWriter = new SimpleWriter1L(outputFile);

        String line;
        while (!simpleReader.atEOS()) {
            line = simpleReader.nextLine();

            simpleWriter.println(line);
        }

        simpleReader.close();
        simpleWriter.close();

        return true;
    }

    /**
    *
    */
    public static boolean copyFileByStdJava(String inputFile,
            String outputFile) {
        BufferedReader bufferedReader = null;
        try {
            FileReader fileReader = new FileReader(inputFile);
            bufferedReader = new BufferedReader(fileReader);

        } catch (FileNotFoundException e) {
            System.err.println("File " + inputFile
                    + " can not be read or does not exist.");
            e.printStackTrace();
            return false;
        }
        // TODO how to close bufferedReader when exception.

        BufferedWriter bufferedWriter = null;
        try {
            FileWriter fileWriter = new FileWriter(outputFile);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            System.err.println("File " + outputFile + " can not be written.");
            e.printStackTrace();
            return false;
        }
        // TODO how to close bufferedWriter when exception.

        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                bufferedWriter.write(line);

                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.err.println("Unabl to read File " + inputFile
                    + " or unable to write File " + outputFile);
            e.printStackTrace();
        }

        // TODO if there are some other codes or logic... how to close Reader and Writer
        // TODO should I use e.printStackTrace(); to print particular StackTrace...

        try {
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            System.err.println("Unable to close buffered stream");
            e.printStackTrace();
        }

        return true;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments: input-file-name output-file-name
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Need 2 arguments: name of input text file"
                    + "and name of output text file");
            return;
        }

        String inputFileName = args[0];
        String outputFileName = args[1];

        File inputFile = new File(inputFileName);
        //File outputFile = new File(outputFileName);

        if (!inputFile.exists() || inputFile.isDirectory()) {
            System.err.println("Please enter valid input file name");
        }

        copyFileByStdJava(inputFileName, outputFileName);

    }

}
