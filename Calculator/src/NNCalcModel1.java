import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Model class.
 *
 * @author Edison
 */
public final class NNCalcModel1 implements NNCalcModel {

    /**
     * Model variables.
     */
    private final NaturalNumber top, bottom;

    /**
     * No argument constructor.
     */
    public NNCalcModel1() {
        this.top = new NaturalNumber2();
        this.bottom = new NaturalNumber2();
    }

    @Override
    public NaturalNumber top() {
//return the top number in the calculator
        return this.top;
    }

    @Override
    public NaturalNumber bottom() {
//return the bottom number in the calculator
        return this.bottom;
    }

}
