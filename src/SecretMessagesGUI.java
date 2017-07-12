import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SecretMessagesGUI extends JPanel {
	private JTextField txtKey;
	private JTextArea txtOut;
	private JTextArea txtIn;
	private JSlider slider;

	public String encode(String message, int k) {

		String out = "";
		char key = (char) k;

		for (int x = 0; x < message.length(); x++) {

			char in = message.charAt(x);

			if (in >= 'A' && in <= 'Z') {

				in += key;
				if (in > 'Z')
					in -= 26;
				if (in < 'A')
					in += 26;

			}

			if (in >= 'a' && in <= 'z') {

				in += key;
				if (in > 'z')
					in -= 26;
				if (in < 'a')
					in += 26;

			}

			if (in >= '0' && in <= '9') {
				in += (k % 10);

				if (in > '9')
					in -= 10;
				if (in < '0')
					in += 10;
			}

			out += in;

		}

		return out;

	}

	public SecretMessagesGUI() {
		setForeground(Color.BLACK);
		setBackground(Color.RED);
		setLayout(null);

		txtIn = new JTextArea();
		txtIn.setFont(new Font("Monospaced", Font.PLAIN, 32));
		txtIn.setBackground(Color.DARK_GRAY);
		txtIn.setForeground(Color.WHITE);
		txtIn.setBounds(10, 40, 1085, 285);
		add(txtIn);

		JLabel lblKey = new JLabel("Key:");
		lblKey.setForeground(Color.BLACK);
		lblKey.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblKey.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKey.setBounds(542, 353, 93, 33);
		add(lblKey);

		txtKey = new JTextField();
		txtKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtKey.setText("0");
		txtKey.setForeground(Color.RED);
		txtKey.setBounds(640, 351, 58, 31);
		add(txtKey);
		txtKey.setColumns(3);

		JButton btnEncodedecode = new JButton("ENCODE/DECODE");
		btnEncodedecode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get the message from txtIn

				String message = txtIn.getText();

				// get the key amount from txtKey

				int key = Integer.parseInt(txtKey.getText());

				slider.setValue(key);

				// encode that message with that key

				String output = encode(message, key);

				// show the message in txtOut

				txtOut.setText(output);

			}
		});
		btnEncodedecode.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEncodedecode.setBounds(724, 351, 288, 31);
		add(btnEncodedecode);

		txtOut = new JTextArea();
		txtOut.setBackground(Color.WHITE);
		txtOut.setFont(new Font("Monospaced", Font.PLAIN, 32));
		txtOut.setBounds(10, 420, 1085, 304);
		add(txtOut);

		JLabel lblAjCodedecodeSoftware = new JLabel("AJ Code/Decode Software");
		lblAjCodedecodeSoftware.setForeground(Color.WHITE);
		lblAjCodedecodeSoftware.setFont(new Font("Viner Hand ITC", Font.BOLD, 25));
		lblAjCodedecodeSoftware.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjCodedecodeSoftware.setBounds(366, 0, 373, 33);
		add(lblAjCodedecodeSoftware);
		setPreferredSize(new Dimension(1108, 752));

		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				txtKey.setText("" + slider.getValue());

				// get the message from txtIn

				String message = txtIn.getText();

				// get the key amount from txtKey

				int key = Integer.parseInt(txtKey.getText());

				// encode that message with that key

				String output = encode(message, key);

				// show the message in txtOut

				txtOut.setText(output);
			}
		});
		slider.setBackground(Color.BLACK);
		slider.setForeground(Color.WHITE);
		slider.setFont(new Font("Tahoma", Font.PLAIN, 20));
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(13);
		slider.setSnapToTicks(true);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setValue(0);
		slider.setMaximum(13);
		slider.setMinimum(-13);
		slider.setBounds(20, 335, 521, 75);
		add(slider);
	}

	public static void main(String[] args) {

		// set up a window Jframe for the app
		JFrame frame = new JFrame("AJ Code/Decode App");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// add the encoder panel to the frame

		frame.getContentPane().add(new SecretMessagesGUI());

		// prepare and show the frame

		frame.pack();
		frame.setVisible(true);

	}
}
