import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EmailAccount1Test {

    /**
     * Test name for different first name
     */
    @Test
    public void testName_1() {
        EmailAccount myAccount1 = new EmailAccount1("Brutus1", "buckeye");
        EmailAccount myAccount2 = new EmailAccount1("Brutus2", "buckeye");

        String expectedAccount1 = "Brutus1 Buckeye";
        String expectedAccount2 = "Brutus2 Buckeye";

        String accountName1 = myAccount1.name();
        String accountName2 = myAccount2.name();

        assertEquals(expectedAccount1, accountName1);
        assertEquals(expectedAccount2, accountName2);
    }

    /**
     * Test name for same first name
     */
    @Test
    public void testName_2() {
        EmailAccount myAccount1 = new EmailAccount1("Brutus1", "buckeye");
        EmailAccount myAccount2 = new EmailAccount1("Brutus1", "buckeye");

        String expectedAccount1 = "Brutus1 Buckeye";
        String expectedAccount2 = "Brutus1 Buckeye";

        String accountName1 = myAccount1.name();
        String accountName2 = myAccount2.name();

        assertEquals(expectedAccount1, accountName1);
        assertEquals(expectedAccount2, accountName2);
    }

    /**
     * Test name for different last name
     */
    @Test
    public void testName_3() {
        EmailAccount myAccount1 = new EmailAccount1("Brutus1", "buckeye1");
        EmailAccount myAccount2 = new EmailAccount1("Brutus2", "buckeye2");

        String expectedAccount1 = "Brutus1 Buckeye1";
        String expectedAccount2 = "Brutus2 Buckeye2";

        String accountName1 = myAccount1.name();
        String accountName2 = myAccount2.name();

        assertEquals(expectedAccount1, accountName1);
        assertEquals(expectedAccount2, accountName2);
    }

    /**
     * Test name for same last name
     */
    @Test
    public void testName_4() {
        EmailAccount myAccount1 = new EmailAccount1("Brutus1", "buckeye1");
        EmailAccount myAccount2 = new EmailAccount1("Brutus2", "buckeye1");

        String expectedAccount1 = "Brutus1 Buckeye1";
        String expectedAccount2 = "Brutus2 Buckeye1";

        String accountName1 = myAccount1.name();
        String accountName2 = myAccount2.name();

        assertEquals(expectedAccount1, accountName1);
        assertEquals(expectedAccount2, accountName2);
    }

    /**
     * Test name for same last name with different case (e.g., Scarlet and
     * SCARLET)
     */
    @Test
    public void testName_5() {
        EmailAccount myAccount1 = new EmailAccount1("Brutus1", "buckeye1");
        EmailAccount myAccount2 = new EmailAccount1("Brutus2", "buckeye1");
        EmailAccount myAccount3 = new EmailAccount1("Brutus3", "BuCKeYe1");

        String expectedAccount1 = "Brutus1 Buckeye1";
        String expectedAccount2 = "Brutus2 Buckeye1";
        String expectedAccount3 = "Brutus3 Buckeye1";

        String accountName1 = myAccount1.name();
        String accountName2 = myAccount2.name();
        String accountName3 = myAccount3.name();

        assertEquals(expectedAccount1, accountName1);
        assertEquals(expectedAccount2, accountName2);
        assertEquals(expectedAccount3, accountName3);
    }

    /**
     * Test email address for different first name
     */
    @Test
    public void testEmailAddress_1() {
        EmailAccount myAccount1 = new EmailAccount1("Brutus1", "buckeye");
        EmailAccount myAccount2 = new EmailAccount1("Brutus2", "buckeye");

        String expectedEmailAddress1 = "buckeye.1@osu.edu";
        String expectedEmailAddress2 = "buckeye.2@osu.edu";

        String emailAddress1 = myAccount1.emailAddress();
        String emailAddress2 = myAccount2.emailAddress();

        assertEquals(expectedEmailAddress1, emailAddress1);
        assertEquals(expectedEmailAddress2, emailAddress2);
    }

    /**
     * Test email address for same first name
     */
    @Test
    public void testEmailAddress_2() {
        EmailAccount myAccount1 = new EmailAccount1("Brutus1", "buckeye");
        EmailAccount myAccount2 = new EmailAccount1("Brutus1", "buckeye");

        String expectedEmailAddress1 = "buckeye.1@osu.edu";
        String expectedEmailAddress2 = "buckeye.2@osu.edu";

        String emailAddress1 = myAccount1.emailAddress();
        String emailAddress2 = myAccount2.emailAddress();

        assertEquals(expectedEmailAddress1, emailAddress1);
        assertEquals(expectedEmailAddress2, emailAddress2);
    }

    /**
     * Test email address for different last name
     */
    @Test
    public void testEmailAddress_3() {
        EmailAccount myAccount1 = new EmailAccount1("Brutus1", "Gray");
        EmailAccount myAccount2 = new EmailAccount1("Brutus2", "buckeye");

        String expectedEmailAddress1 = "gray.1@osu.edu";
        String expectedEmailAddress2 = "buckeye.1@osu.edu";

        String emailAddress1 = myAccount1.emailAddress();
        String emailAddress2 = myAccount2.emailAddress();

        assertEquals(expectedEmailAddress1, emailAddress1);
        assertEquals(expectedEmailAddress2, emailAddress2);
    }

    /**
     * Test email address for same last name
     */
    @Test
    public void testEmailAddress_4() {
        EmailAccount myAccount1 = new EmailAccount1("Brutus1", "buckeye");
        EmailAccount myAccount2 = new EmailAccount1("Brutus2", "buckeye");

        String expectedEmailAddress1 = "buckeye.1@osu.edu";
        String expectedEmailAddress2 = "buckeye.2@osu.edu";

        String emailAddress1 = myAccount1.emailAddress();
        String emailAddress2 = myAccount2.emailAddress();

        assertEquals(expectedEmailAddress1, emailAddress1);
        assertEquals(expectedEmailAddress2, emailAddress2);
    }

    /**
     * Test email address for same last name with different case (e.g., Scarlet
     * and SCARLET)
     */
    @Test
    public void testEmailAddress_5() {
        EmailAccount myAccount1 = new EmailAccount1("Brutus1", "buckeye");
        EmailAccount myAccount2 = new EmailAccount1("Brutus2", "buckeye");
        EmailAccount myAccount3 = new EmailAccount1("Brutus3", "BuCKeYe");

        String expectedEmailAddress1 = "buckeye.1@osu.edu";
        String expectedEmailAddress2 = "buckeye.2@osu.edu";
        String expectedEmailAddress3 = "buckeye.3@osu.edu";

        String emailAddress1 = myAccount1.emailAddress();
        String emailAddress2 = myAccount2.emailAddress();
        String emailAddress3 = myAccount3.emailAddress();

        assertEquals(expectedEmailAddress1, emailAddress1);
        assertEquals(expectedEmailAddress2, emailAddress2);
        assertEquals(expectedEmailAddress3, emailAddress3);
    }

}
