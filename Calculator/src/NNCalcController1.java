import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Controller class.
 *
 * @author Edison
 */
public final class NNCalcController1 implements NNCalcController {

    /**
     * Model object.
     */
    private final NNCalcModel model;

    /**
     * View object.
     */
    private final NNCalcView view;

    /**
     * Useful constants.
     */
    private static final NaturalNumber TWO = new NaturalNumber2(2),
            INT_LIMIT = new NaturalNumber2(Integer.MAX_VALUE);

    /**
     * Updates this.view to display this.model, and to allow only operations
     * that are legal given this.model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     * @ensures [view has been updated to be consistent with model]
     */
    private static void updateViewToMatchModel(NNCalcModel model,
            NNCalcView view) {

        NaturalNumber modelForTop = model.top();
        NaturalNumber modelForBottom = model.bottom();

        //numbers to strings

        String topNum = modelForTop.toString();
        String botNum = modelForBottom.toString();

        //update display
        view.updateBottomDisplay(modelForBottom);
        view.updateTopDisplay(modelForTop);

        //check subtraction
        boolean ableToSubtract = modelForBottom.compareTo(modelForTop) <= 0;
        view.updateSubtractAllowed(ableToSubtract);

        //check division
        boolean ableToDivide = !modelForBottom.isZero();
        view.updateDivideAllowed(ableToDivide);

        //check power and bottom should be at least two.

        boolean ableToPower = modelForBottom.compareTo(TWO) >= 0;
        view.updatePowerAllowed(ableToPower);

        //check root and the bottom number is at least 2 within limit.
        boolean ableToRoot = modelForBottom.compareTo(TWO) >= 0
                && modelForBottom.compareTo(INT_LIMIT) <= 0;
        view.updateRootAllowed(ableToRoot);

    }

    /**
     * Constructor.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public NNCalcController1(NNCalcModel model, NNCalcView view) {
        this.model = model;
        this.view = view;
        updateViewToMatchModel(model, view);
    }

    @Override
    public void processClearEvent() {
        /*
         * Get alias to bottom from model
         */
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.clear();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSwapEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        NaturalNumber temp = top.newInstance();
        temp.transferFrom(top);
        top.transferFrom(bottom);
        bottom.transferFrom(temp);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processEnterEvent() {

        NaturalNumber forTop = this.model.top();
        NaturalNumber forBottom = this.model.bottom();
        //copy value from bottom to top
        forTop.copyFrom(forBottom);

        //update view
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddEvent() {

        NaturalNumber forTop = this.model.top();
        NaturalNumber forBottom = this.model.bottom();

        //top add bottom
        forTop.add(forBottom);
        //move result to bottom
        forBottom.transferFrom(forTop);

        //update view
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processSubtractEvent() {

        NaturalNumber forTop = this.model.top();
        NaturalNumber forBottom = this.model.bottom();
        //top subtract bottom
        forTop.subtract(forBottom);
        //move result to bottom
        forBottom.transferFrom(forTop);
        //update view
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processMultiplyEvent() {

        NaturalNumber forTop = this.model.top();
        NaturalNumber forBottom = this.model.bottom();
        //top multiply bottom
        forTop.multiply(forBottom);
        //move result to bottom
        forBottom.transferFrom(forTop);
        //update view
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processDivideEvent() {

        NaturalNumber forTop = this.model.top();
        NaturalNumber forBottom = this.model.bottom();
        //find remainder
        NaturalNumber remainder = forTop.divide(forBottom);
//move result to bottom
        forBottom.transferFrom(forTop);
        //move  remainder to top
        forTop.transferFrom(remainder);
        //update view
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processPowerEvent() {

        NaturalNumber forTop = this.model.top();
        NaturalNumber forBottom = this.model.bottom();
        //convert bottom number to int
        int power = forBottom.toInt();
        //raise to the power of the bottom value
        forTop.power(power);
        //move result to bottom
        forBottom.transferFrom(forTop);
//update view
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processRootEvent() {

        NaturalNumber forTop = this.model.top();
        NaturalNumber forBottom = this.model.bottom();
        //convert bottom number to int
        int root = forBottom.toInt();
        //find root of top
        forTop.root(root);
        //move result to bottom
        forBottom.transferFrom(forTop);
//update view
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddNewDigitEvent(int digit) {

        NaturalNumber bottom = this.model.bottom();
        //add digit
        bottom.multiplyBy10(digit);
        //update view
        updateViewToMatchModel(this.model, this.view);

    }

}
