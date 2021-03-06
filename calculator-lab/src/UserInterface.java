import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * A graphical user interface for the calculator. No calculation is being
 * done here. This class is responsible just for putting up the display on 
 * screen. It then refers to the "CalcEngine" to do all the real work.
 * 
 * @author Michael Kolling & Mathana Sreedaran
 */
public class UserInterface
implements ActionListener
{
	private CalcEngine calc;
	private boolean showingAuthor;

	private JFrame frame;
	private JTextField display;
	private JLabel status;


	/**
	 * Create a user interface for a given calcEngine.
	 */
	public UserInterface(CalcEngine engine)
	{
		calc = engine;
		showingAuthor = true;
		makeFrame();
		frame.setVisible(true);
	}

	/**
	 * Make this interface visible again. (Has no effect if it is already
	 * visible.)
	 */
	public void setVisible(boolean visible)
	{
		frame.setVisible(visible);
	}

	/**
	 * Make the frame for the user interface.
	 */
	private void makeFrame()
	{
		frame = new JFrame(calc.getTitle());

		JPanel contentPane = (JPanel)frame.getContentPane();
		contentPane.setLayout(new BorderLayout(8, 8));
		contentPane.setBorder(new EmptyBorder( 10, 10, 10, 10));
		contentPane.setBackground(Color.black);

		display = new JTextField();
		contentPane.add(display, BorderLayout.NORTH);

		display.setEditable(false); //Prevent typing into textfield

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//close app when 'x' button clicked



		JPanel buttonPanel = new JPanel(new GridLayout(6, 4));
		buttonPanel.setBackground(Color.darkGray);
		addButton(buttonPanel, "C");
		addButton(buttonPanel, "negative");
		addButton(buttonPanel, "(");
		addButton(buttonPanel, ")");
		addButton(buttonPanel, "/");
		addButton(buttonPanel, "9");
		addButton(buttonPanel, "8");
		addButton(buttonPanel, "7");
		addButton(buttonPanel, "*");
		addButton(buttonPanel, "4");
		addButton(buttonPanel, "5");
		addButton(buttonPanel, "6");
		addButton(buttonPanel, "+");
		addButton(buttonPanel, "1");
		addButton(buttonPanel, "2");
		addButton(buttonPanel, "3");
		addButton(buttonPanel, "-");
		addButton(buttonPanel, "0");
		addButton(buttonPanel, ".");
		addButton(buttonPanel, "^");
		addButton(buttonPanel, "=");
		contentPane.add(buttonPanel, BorderLayout.CENTER);

		status = new JLabel(calc.getAuthor());
		status.setForeground(Color.white);
		status.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(status, BorderLayout.SOUTH);

		frame.pack();
	}

	/**
	 * Add a button to the button panel.
	 */
	private void addButton(Container panel, String buttonText)
	{
		JButton button = new JButton(buttonText);
		button.addActionListener(this);
		panel.add(button);
	}

	/**
	 * An interface action has been performed. Find out what it was and
	 * handle it.
	 */
	public void actionPerformed(ActionEvent event)
	{
		String command = event.getActionCommand();

		if(command.equals("="))
				calc.equals();
		else if(command.equals("C"))
			calc.clear();
		else
			calc.numberPressed(command);;

		redisplay();
	}

	/**
	 * Update the interface display to show the current value of the 
	 * calculator.
	 */
	private void redisplay()
	{
		display.setText(calc.getDisplayValue());
	}

}
