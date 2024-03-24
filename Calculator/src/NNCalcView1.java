import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import components.naturalnumber.NaturalNumber;

/**
 * View class.
 *
 * @author Edison
 */
public final class NNCalcView1 extends JFrame implements NNCalcView {

    /**
     * Controller object registered with this view to observe user-interaction
     * events.
     */
    private NNCalcController controller;

    /**
     * State of user interaction: last event "seen".
     */
    private enum State {
        /**
         * Last event was clear, enter, another operator, or digit entry, resp.
         */
        SAW_CLEAR, SAW_ENTER_OR_SWAP, SAW_OTHER_OP, SAW_DIGIT
    }

    /**
     * State variable to keep track of which event happened last; needed to
     * prepare for digit to be added to bottom operand.
     */
    private State currentState;

    /**
     * Text areas.
     */
    private final JTextArea tTop, tBottom;

    /**
     * Operator and related buttons.
     */
    private final JButton bClear, bSwap, bEnter, bAdd, bSubtract, bMultiply,
            bDivide, bPower, bRoot;

    /**
     * Digit entry buttons.
     */
    private final JButton[] bDigits;

    /**
     * Useful constants.
     */
    private static final int TEXT_AREA_HEIGHT = 5, TEXT_AREA_WIDTH = 20,
            DIGIT_BUTTONS = 10, MAIN_BUTTON_PANEL_GRID_ROWS = 4,
            MAIN_BUTTON_PANEL_GRID_COLUMNS = 4, SIDE_BUTTON_PANEL_GRID_ROWS = 3,
            SIDE_BUTTON_PANEL_GRID_COLUMNS = 1, CALC_GRID_ROWS = 3,
            CALC_GRID_COLUMNS = 1;

    /**
     * No argument constructor.
     */
    public NNCalcView1() {
        // Create the JFrame being extended

        /*
         * Call the JFrame (superclass) constructor with a String parameter to
         * name the window in its title bar
         */
        super("Natural Number Calculator");

        // Set up the GUI widgets --------------------------------------------

        /*
         * Set up initial state of GUI to behave like last event was "Clear";
         * currentState is not a GUI widget per se, but is needed to process
         * digit button events appropriately
         */
        this.currentState = State.SAW_CLEAR;

//JTextArea tTop, tBottom
        //Height = 5, Width = 20

        this.tTop = new JTextArea(TEXT_AREA_HEIGHT, TEXT_AREA_WIDTH);

        this.tBottom = new JTextArea(TEXT_AREA_HEIGHT, TEXT_AREA_WIDTH);

        //use private final JButton bClear, bSwap, bEnter, bAdd, bSubtract, bMultiply,
        //bDivide, bPower, bRoot;

        this.bClear = new JButton("Clear");
        this.bSwap = new JButton("Swap");
        this.bEnter = new JButton("Enter");
        this.bAdd = new JButton("+");
        this.bSubtract = new JButton("-");
        this.bMultiply = new JButton("*");
        this.bDivide = new JButton("/");
        this.bPower = new JButton("Power");
        this.bRoot = new JButton("Root");

        //Digits = bDigits

        this.bDigits = new JButton[DIGIT_BUTTONS];
        //Calculator demo 0 through 9

        //3-9 were magic numbers
        final int three = 3;
        final int four = 4;
        final int five = 5;
        final int six = 6;
        final int seven = 7;
        final int eight = 8;
        final int nine = 9;

        this.bDigits[0] = new JButton("0");
        this.bDigits[1] = new JButton("1");
        this.bDigits[2] = new JButton("2");
        this.bDigits[three] = new JButton("3");
        this.bDigits[four] = new JButton("4");
        this.bDigits[five] = new JButton("5");
        this.bDigits[six] = new JButton("6");
        this.bDigits[seven] = new JButton("7");
        this.bDigits[eight] = new JButton("8");
        this.bDigits[nine] = new JButton("9");

        // Set up the GUI widgets --------------------------------------------

        /*
         * Text areas should wrap lines, and should be read-only; they cannot be
         * edited because allowing keyboard entry would require checking whether
         * entries are digits, which we don't want to have to do
         */

        //wrap lines and set style
        this.tTop.setLineWrap(true);
        this.tBottom.setLineWrap(true);
        this.tTop.setWrapStyleWord(true);
        this.tBottom.setWrapStyleWord(true);

        /*
         * Initially, the following buttons should be disabled: divide (divisor
         * must not be 0) and root (root must be at least 2) -- hint: see the
         * JButton method setEnabled
         */

//not editable
        this.tTop.setEditable(false);
        this.tBottom.setEditable(false);

        /*
         * Create scroll panes for the text areas in case number is long enough
         * to require scrolling
         */

        JScrollPane topScrollPane = new JScrollPane(this.tTop);
        JScrollPane bottomScrollPane = new JScrollPane(this.tBottom);

        /*
         * Create main button panel
         */

        /*
         * constants TEXT_AREA_HEIGHT = 5, TEXT_AREA_WIDTH = 20, DIGIT_BUTTONS =
         * 10, MAIN_BUTTON_PANEL_GRID_ROWS = 4, MAIN_BUTTON_PANEL_GRID_COLUMNS =
         * 4, SIDE_BUTTON_PANEL_GRID_ROWS = 3, SIDE_BUTTON_PANEL_GRID_COLUMNS =
         * 1, CALC_GRID_ROWS = 3, CALC_GRID_COLUMNS = 1;
         */
        JPanel mainPanel = new JPanel(new GridLayout(
                MAIN_BUTTON_PANEL_GRID_ROWS, MAIN_BUTTON_PANEL_GRID_COLUMNS));

        /*
         * Add the buttons to the main button panel, from left to right and top
         * to bottom
         */

        mainPanel.add(this.bDigits[seven]);
        mainPanel.add(this.bDigits[eight]);
        mainPanel.add(this.bDigits[nine]);
        mainPanel.add(this.bAdd);
        mainPanel.add(this.bDigits[four]);
        mainPanel.add(this.bDigits[five]);
        mainPanel.add(this.bDigits[six]);
        mainPanel.add(this.bSubtract);
        mainPanel.add(this.bDigits[1]);
        mainPanel.add(this.bDigits[2]);
        mainPanel.add(this.bDigits[three]);
        mainPanel.add(this.bMultiply);
        mainPanel.add(this.bDigits[0]);

        mainPanel.add(this.bPower);
        mainPanel.add(this.bRoot);
        mainPanel.add(this.bDivide);

        /*
         * Create side button panel
         */

        //use constants for side button panel

        JPanel sidePanel = new JPanel(new GridLayout(
                SIDE_BUTTON_PANEL_GRID_ROWS, SIDE_BUTTON_PANEL_GRID_COLUMNS));

        /*
         * Add the buttons to the side button panel, from left to right and top
         * to bottom
         */
        //clear on top
        sidePanel.add(this.bClear);
        //swap under top
        sidePanel.add(this.bSwap);
        //enter under swap
        sidePanel.add(this.bEnter);

        /*
         * Create combined button panel organized using flow layout, which is
         * simple and does the right thing: sizes of nested panels are natural,
         * not necessarily equal as with grid layout
         */

        JPanel combinedPanel = new JPanel(new FlowLayout());

        /*
         * Add the other two button panels to the combined button panel
         */

        combinedPanel.add(mainPanel);
        combinedPanel.add(sidePanel);

        /*
         * Organize main window
         */
//use constants CALC_GRID_ROWS and CALC_GRID_COLUMNS
        this.setLayout(new GridLayout(CALC_GRID_ROWS, CALC_GRID_COLUMNS));
        /*
         * Add scroll panes and button panel to main window, from left to right
         * and top to bottom
         */
        this.add(topScrollPane);
        this.add(bottomScrollPane);
        this.add(combinedPanel);

        // Set up the observers ----------------------------------------------

        /*
         * Register this object as the observer for all GUI events
         */

        //makes GUI interactive

        this.bAdd.addActionListener(this);
        this.bSubtract.addActionListener(this);
        this.bMultiply.addActionListener(this);
        this.bDivide.addActionListener(this);
        this.bPower.addActionListener(this);
        this.bRoot.addActionListener(this);
        this.bClear.addActionListener(this);
        this.bSwap.addActionListener(this);
        this.bEnter.addActionListener(this);

        final int ten = 10;
        int i = 0;
        while (i < ten) {
            this.bDigits[i].addActionListener(this);
            i++;
        }

        // Set up the main application window --------------------------------

        /*
         * Make sure the main window is appropriately sized, exits this program
         * on close, and becomes visible to the user
         */
        this.setVisible(true);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void registerObserver(NNCalcController controller) {
//register controller with view
        this.controller = controller;

    }

    @Override
    public void updateTopDisplay(NaturalNumber n) {
        //n to string and update number for top
        String topNum = n.toString();
        this.tTop.setText(topNum);

    }

    @Override
    public void updateBottomDisplay(NaturalNumber n) {

        //n to string and update number for bottom

        String botNum = n.toString();
        this.tBottom.setText(botNum);

    }

    @Override
    public void updateSubtractAllowed(boolean allowed) {
//enable subtract button
        this.bSubtract.setEnabled(allowed);

    }

    @Override
    public void updateDivideAllowed(boolean allowed) {
//enable divide button
        this.bDivide.setEnabled(allowed);

    }

    @Override
    public void updatePowerAllowed(boolean allowed) {
//enable power button
        this.bPower.setEnabled(allowed);

    }

    @Override
    public void updateRootAllowed(boolean allowed) {
//enable root button
        this.bRoot.setEnabled(allowed);

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        /*
         * Set cursor to indicate computation on-going; this matters only if
         * processing the event might take a noticeable amount of time as seen
         * by the user
         */
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        /*
         * Determine which event has occurred that we are being notified of by
         * this callback; in this case, the source of the event (i.e, the widget
         * calling actionPerformed) is all we need because only buttons are
         * involved here, so the event must be a button press; in each case,
         * tell the controller to do whatever is needed to update the model and
         * to refresh the view
         */
        Object source = event.getSource();
        if (source == this.bClear) {
            this.controller.processClearEvent();
            this.currentState = State.SAW_CLEAR;
        } else if (source == this.bSwap) {
            this.controller.processSwapEvent();
            this.currentState = State.SAW_ENTER_OR_SWAP;
        } else if (source == this.bEnter) {
            this.controller.processEnterEvent();
            this.currentState = State.SAW_ENTER_OR_SWAP;
        } else if (source == this.bAdd) {
            this.controller.processAddEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else if (source == this.bSubtract) {
            this.controller.processSubtractEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else if (source == this.bMultiply) {
            this.controller.processMultiplyEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else if (source == this.bDivide) {
            this.controller.processDivideEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else if (source == this.bPower) {
            this.controller.processPowerEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else if (source == this.bRoot) {
            this.controller.processRootEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else {
            for (int i = 0; i < DIGIT_BUTTONS; i++) {
                if (source == this.bDigits[i]) {
                    switch (this.currentState) {
                        case SAW_ENTER_OR_SWAP:
                            this.controller.processClearEvent();
                            break;
                        case SAW_OTHER_OP:
                            this.controller.processEnterEvent();
                            this.controller.processClearEvent();
                            break;
                        default:
                            break;
                    }
                    this.controller.processAddNewDigitEvent(i);
                    this.currentState = State.SAW_DIGIT;
                    break;
                }
            }
        }
        /*
         * Set the cursor back to normal (because we changed it at the beginning
         * of the method body)
         */
        this.setCursor(Cursor.getDefaultCursor());
    }

}
