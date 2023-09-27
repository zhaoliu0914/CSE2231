import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple program to exercise EmailAccount functionality.
 */
public final class EmailAccountMain {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private EmailAccountMain() {
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
        EmailAccount myAccount = new EmailAccount1("Brutus", "Buckeye");
        /*
         * Should print: Brutus Buckeye
         */
        out.println(myAccount.name());
        /*
         * Should print: buckeye.1@osu.edu
         */
        out.println(myAccount.emailAddress());
        /*
         * Should print: Name: Brutus Buckeye, Email: buckeye.1@osu.edu
         */
        out.println(myAccount);

        EmailAccount myAccount1 = new EmailAccount1("Brutus", "buckeye");
        out.println(myAccount1.name());
        out.println("Expected: buckeye.2 Actual: " + myAccount1.emailAddress());
        out.println(myAccount1);

        System.out.println("=================================");

        EmailAccount myAccount2 = new EmailAccount1("Alice", "Scarlet");
        out.println(myAccount2.name());
        out.println("Expected: scarlet.1 Actual: " + myAccount2.emailAddress());
        out.println(myAccount2);

        System.out.println("=================================");

        EmailAccount myAccount3 = new EmailAccount1("Bob", "Gray");
        out.println(myAccount3.name());
        out.println("Expected: gray.1 Actual: " + myAccount3.emailAddress());
        out.println(myAccount3);

        System.out.println("=================================");

        EmailAccount myAccount4 = new EmailAccount1("Jane", "Scarlet");
        out.println(myAccount4.name());
        out.println("Expected: scarlet.2 Actual: " + myAccount4.emailAddress());
        out.println(myAccount4);

        System.out.println("=================================");

        EmailAccount myAccount5 = new EmailAccount1("Tom", "ScArLeT");
        out.println(myAccount5.name());
        out.println("Expected: scarlet.3 Actual: " + myAccount5.emailAddress());
        out.println(myAccount5);

        in.close();
        out.close();
    }

}
