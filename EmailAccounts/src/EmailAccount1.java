import components.map.Map;
import components.map.Map1L;

/**
 * Implementation of {@code EmailAccount}.
 *
 * @author Zhao Liu
 *
 */
public final class EmailAccount1 implements EmailAccount {

    /*
     * Private members --------------------------------------------------------
     */

    // TODO - declare static and instance data members
    private static Map<String, Integer> emailAddressMap = new Map1L<>();
    private static String emailPostfix = "@osu.edu";
    private String firstName;
    private String lastName;
    private String emailAddress;

    /*
     * Constructor ------------------------------------------------------------
     */

    /**
     * Constructor.
     *
     * @param firstNameInput
     *            the first name
     * @param lastNameInput
     *            the last name
     */
    public EmailAccount1(String firstNameInput, String lastNameInput) {
        this.firstName = firstNameInput.toLowerCase();
        this.lastName = lastNameInput.toLowerCase();

        int emailIndex = 1;

        // update emailAddressMap
        // if lastName exists then index++;
        // if lastName does not exist then set initial index = 1
        if (emailAddressMap.hasKey(this.lastName)) {
            Map.Pair<String, Integer> temp = emailAddressMap
                    .remove(this.lastName);
            emailIndex = temp.value() + 1;
        }

        this.emailAddress = this.lastName + "." + emailIndex + emailPostfix;

        emailAddressMap.add(this.lastName, emailIndex);
    }

    /*
     * Methods ----------------------------------------------------------------
     */

    @Override
    public String name() {
        String firstNameFormatted = this.firstName.substring(0, 1).toUpperCase()
                + this.firstName.substring(1);
        String lastNameFormatted = this.lastName.substring(0, 1).toUpperCase()
                + this.lastName.substring(1);

        return firstNameFormatted + " " + lastNameFormatted;
    }

    @Override
    public String emailAddress() {

        return this.emailAddress;
    }

    @Override
    public String toString() {

        String output = "Name: " + this.name() + ", Email: "
                + this.emailAddress();
        return output;
    }

}
