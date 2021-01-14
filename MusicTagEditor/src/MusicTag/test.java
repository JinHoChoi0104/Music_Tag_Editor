package MusicTag;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

import net.iharder.dnd.FileDrop;
import javax.swing.JLabel;
import javax.swing.JList;

public class test extends JPanel {
	
	//private JFrame frame;

	private JTextField PathInput = new JTextField("");
	private JTable MusicList = new JTable();
	JScrollPane scrollPane = new JScrollPane(MusicList);
	//contentPane.add(scrollPane);
	
	/**
	 * Music list
	 * namelist will be shown on TextArea
	 * pahtlist will be used to edit mp3 files
	 * two lists will have same index for same mp3 files
	 * and it will make easy to add and delete files from list
	 */
	LinkedList<String> namelist = new LinkedList<String>();
	LinkedList<String> pathlist = new LinkedList<String>();
	private static int index = 0; 
	
	/**
	 * Create the application.
	 */
	public test() {
		//setLayout(null);
		String colNames[] = {"ÆÄÀÏÀÌ¸§", "Artist", "Title"};
		DefaultTableModel model =  new DefaultTableModel(colNames, 0);
		
		model.addRow(new Object[] {"haha","È«±æµ¿","011-5-1177"});
		
		MusicList = new JTable(model);
		scrollPane = new JScrollPane(MusicList);
		add(scrollPane);
		initialize();
	}
	
	
	
	/**
	 * Launch the application.
	 */
	
	/*
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
	}*/
	
	public static void main(String[] args) {
		test panel = new test();
		JFrame win = new JFrame();
		win.add(panel);
		win.setBounds(550, 100, 510, 610);
		win.setTitle("Music Tag Editor");
		//win.setSize(540,400);
		win.setVisible(true);
	}
	

	


	public void phrase1() { // Path request phrases
		Font font2 = new Font("¸¼Àº °íµñ", Font.ITALIC, 12);
		PathInput.setForeground(Color.gray);
		PathInput.setFont(font2);
		PathInput.setText("Enter directory address here");
	}
	
	public void phrase2() { // Path request phrases
		Font font2 = new Font("¸¼Àº °íµñ", Font.ITALIC, 12);
		MusicList.setForeground(Color.gray);
		MusicList.setFont(font2);
		//MusicList.setText("Drag and drop files here");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/*
		 * about Main Window (JFrame)
		 */
		
		/*
		frame = new JFrame();
		frame.setBounds(550, 100, 510, 610);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //what is this for???
		frame.getContentPane().setLayout(null);
		frame.setTitle("Music Tag Editor");
		*/
		
		
		/*
		 * about PathInputWindow (textField)
		 */
		PathInput.setBounds(32, 26, 427, 21);
		//frame.getContentPane().
		add(PathInput);
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

		PathInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Press Enter key
				File fileList[] = new File(PathInput.getText()).listFiles();
				findMusicFile(fileList);
				phrase1();
			}
		});
		
		
		
		
		
		/*
		 * about MusicListWindow (textArea)
		 */
		scrollPane.setBounds(33, 80, 426, 390);
		//frame.getContentPane().
		add(scrollPane);
		phrase2();
		
		new FileDrop(System.out, MusicList, /* dragBorder, */ new FileDrop.Listener() {
			public void filesDropped(java.io.File[] fileList) {
				findMusicFile(fileList);
				DefaultTableModel model = (DefaultTableModel)MusicList.getModel();
				model.addRow(new String[] {"","",""});
			} // end filesDropped
		}); // end FileDrop.Listener

		//MusicList.setEditable (false); //make text area uneditalbe
		
		
		

		/*
		 * about Clear Button
		 */
		JButton ClearButton = new JButton("Clear All");
		ClearButton.setFont(new Font("±¼¸²", Font.BOLD, 14));
		ClearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearList();
				phrase2();
			}
		});
		ClearButton.setBounds(195, 500, 120, 40);
		//frame.getContentPane().
		add(ClearButton);
		
		
		/*
		 * about Running Button
		 */
		JButton RunningButton = new JButton("Run Edit");
		RunningButton.setFont(new Font("±¼¸²", Font.BOLD, 14));
		RunningButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RunTagEditor();
			}
		});
		RunningButton.setBounds(339, 500, 120, 40);
		//frame.getContentPane().
		add(RunningButton);
		
		JLabel lblNewLabel = new JLabel("Music File List");
		lblNewLabel.setBounds(32, 61, 92, 15);
		//frame.getContentPane().
		add(lblNewLabel);
	}
	
	public void findMusicFile(File[] fileList) {
		if (MusicList.getForeground() != Color.black) {
		//	MusicList.setText(""); 
			MusicList.setForeground(Color.black);
			Font font1 = new Font("¸¼Àº °íµñ", Font.PLAIN, 12);
			MusicList.setFont(font1);
		}
		for( int i = 0; i < fileList.length; i++ ) { //fileName = file.getName(); try
			String fileName = fileList[i].getName();
			if (fileName.matches(".*.mp3")) {						  
				try{						
					index++;						
					pathlist.add(fileList[i].getCanonicalPath());							
					namelist.add(fileName);							
				//	MusicList.append(fileName + "\n"); //show up file name					
				}						
				catch( java.io.IOException e ) { } 					
			} // end for: through each dropped file				
		}
		
	}
	
	public void clearList() {
		index = 0;
		namelist.clear();
		pathlist.clear();
	}
	
	public void RunTagEditor() {		
	//	MusicList.setText("");
		String fileName, artist, title; // = "";
		int idx = 0; // index of " - "
		int idx2 = 0; // index of ".mp3", 
		int cnt = 0, fail = 0; // count of file(s) successfully modified

		for(int i=0 ; i<index;i++) {
			fileName = namelist.get(i);
			if (fileName.matches(".* - .*")) {
				idx = fileName.indexOf(" - ");
				idx2 = fileName.indexOf(".mp3");
				artist = fileName.substring(0, idx);
				title = fileName.substring(idx + 3, idx2);
				rewriteTag(pathlist.get(i), artist, title);
				cnt++;
			} else {
				MusicList.setForeground(Color.red);
		//		if (MusicList.getText().isBlank())
		//			MusicList.append(" ERROR! There is no \" - \" in:\n");
				fail++;
		//		MusicList.append(fail + ". " + fileName + "\n");
			}
		}
//		if (MusicList.getText().isBlank())
			phrase2();
		clearList();
		JOptionPane.showMessageDialog(null,cnt+" file(s) successfully modified\n"+fail+
				" file(s) failed to modified","Information",JOptionPane.WARNING_MESSAGE);
	}

	public static void rewriteTag(String path, String artist, String title) {
		File file = new File(path); // MP3 ÆÄÀÏÀÇ °æ·Î¸¦ ÀÌ¿ëÇÏ¿© File Object¸¦ »ý¼ºÇÑ´Ù.
		try {
			// make modification to the tag class
			AudioFile f = AudioFileIO.read(file);

			Tag tag = f.getTag();
			tag.setField(FieldKey.ARTIST, artist);
			tag.setField(FieldKey.TITLE, title);

			// and then commit the change
			// f.commit(); //you can use this instead of code under
			AudioFileIO.write(f);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
