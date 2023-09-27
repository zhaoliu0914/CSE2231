import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;

/**
 * Customized JUnit test fixture for {@code NaturalNumber3}.
 */
public class NaturalNumber3Test extends NaturalNumberTest {

    @Override
    protected final NaturalNumber constructorTest() {
        NaturalNumber naturalNumber = new NaturalNumber3();
        return naturalNumber;
    }

    @Override
    protected final NaturalNumber constructorTest(int i) {
        NaturalNumber naturalNumber = new NaturalNumber3(i);
        return naturalNumber;
    }

    @Override
    protected final NaturalNumber constructorTest(String s) {
        NaturalNumber naturalNumber = new NaturalNumber3(s);
        return naturalNumber;
    }

    @Override
    protected final NaturalNumber constructorTest(NaturalNumber n) {
        NaturalNumber naturalNumber = new NaturalNumber3(n);
        return naturalNumber;
    }

    @Override
    protected final NaturalNumber constructorRef() {
        NaturalNumber naturalNumber = new NaturalNumber1L();
        return naturalNumber;
    }

    @Override
    protected final NaturalNumber constructorRef(int i) {
        NaturalNumber naturalNumber = new NaturalNumber1L(i);
        return naturalNumber;
    }

    @Override
    protected final NaturalNumber constructorRef(String s) {
        NaturalNumber naturalNumber = new NaturalNumber1L(s);
        return naturalNumber;
    }

    @Override
    protected final NaturalNumber constructorRef(NaturalNumber n) {
        NaturalNumber naturalNumber = new NaturalNumber1L(n);
        return naturalNumber;
    }

}
