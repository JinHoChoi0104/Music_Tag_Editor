package MusicTag;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;

public class test {

	private JFrame frame;
	
	
	private JTextField PathInput = new JTextField(" Enter directory address here");
	private JTextField textField_1;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test window = new test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	
	}

	
	/**
	 * Create the application.
	 */
	public test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Run Edit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(33, 536, 152, 55);
		frame.getContentPane().add(btnNewButton);
		

		
		
		Font font1 = new Font("¸¼Àº °íµñ", Font.PLAIN,12);
		Font font2 = new Font("¸¼Àº °íµñ", Font.ITALIC,12);
		PathInput.setForeground(Color.gray);
		PathInput.setFont(font2);
		
		PathInput.setBounds(32, 78, 427, 21);
		frame.getContentPane().add(PathInput);
		PathInput.setColumns(10);
		
		PathInput.addFocusListener(new FocusListener() {
		    public void focusGained(FocusEvent e) {
		    	PathInput.setText("");
		    	PathInput.setForeground(Color.black);
		    	PathInput.setFont(font1);
		    }

		    public void focusLost(FocusEvent e) {
		    	PathInput.setText(" Enter directory address here");
		    	PathInput.setForeground(Color.gray); 
		    	PathInput.setFont(font2);
		    }
		});

		
		
		
		
		textField_1 = new JTextField();
		textField_1.setBounds(32, 32, 96, 21);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
	}
}
