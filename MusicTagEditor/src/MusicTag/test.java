package MusicTag;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.iharder.dnd.FileDrop;

public class test {

	private JFrame frame;

	private JTextField PathInput = new JTextField("");
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

	public void phrase1() { // Path request phrases
		Font font2 = new Font("¸¼Àº °íµñ", Font.ITALIC, 12);
		PathInput.setForeground(Color.gray);
		PathInput.setFont(font2);
		PathInput.setText("Enter directory address here");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 510, 610);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		/*
		 * about Running Button
		 */
		JButton btnNewButton = new JButton("Run Edit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(32, 502, 128, 43);
		frame.getContentPane().add(btnNewButton);

		/*
		 * about PathInputWindow
		 */
		PathInput.setBounds(32, 78, 427, 21);
		frame.getContentPane().add(PathInput);
		PathInput.setColumns(10);
		phrase1();

		PathInput.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				Font font1 = new Font("¸¼Àº °íµñ", Font.PLAIN, 12);
				if (PathInput.getForeground() == Color.gray) {
					PathInput.setForeground(Color.black);
					PathInput.setFont(font1);
					PathInput.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (PathInput.getText().isBlank())
					phrase1();
			}
		});

		textField_1 = new JTextField();
		textField_1.setBounds(32, 32, 96, 21);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(33, 125, 426, 355);
		frame.getContentPane().add(textArea);

		String fileName = "";
		String artist = "";
		String title = "";

		int idx = 0; // index of " - "
		int idx2 = 0; // index of ".mp3"

		new FileDrop(System.out, textArea, /* dragBorder, */ new FileDrop.Listener() {
			public void filesDropped(java.io.File[] fileList) { 
				for (File file : fileList) { 
					try { 
						textArea.append( file.getCanonicalPath() + "\n" );
					} 
					
					catch( java.io.IOException e ) {
					} 
				
				}
				
				/*
				for( int i = 0; i < fileList.length; i++ ) { 
					//fileName = file.getName();
					try { 
						textArea.append( fileList[i].getCanonicalPath() + "\n" ); 
					
					} 
					
					catch( java.io.IOException e ) {
					} 
				} // end for: through each dropped file
					*/
				

			} // end filesDropped
		}); // end FileDrop.Listener

	}
}
