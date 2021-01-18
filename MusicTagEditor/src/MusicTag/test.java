package MusicTag;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

import net.iharder.dnd.FileDrop;

public class test extends JPanel {
	
	private static JFrame win = new JFrame();
	private JTextField PathInput = new JTextField("");
	private JTable DataSet = new JTable();
	private DefaultTableModel model = new DefaultTableModel(new String[] { "File Name", "Artist", "Title" }, 0) {
		public boolean isCellEditable(int row, int column) { // all cells false
			return false; //make cells are unable to edit
		}
	};
	JScrollPane MusicList = new JScrollPane(DataSet);

	private static int Xsize = 800;

	/**
	 * Music list namelist will be shown on TextArea pahtlist will be used to edit
	 * mp3 files two lists will have same index for same mp3 files and it will make
	 * easy to add and delete files from list
	 */
	LinkedList<String> namelist = new LinkedList<String>();
	LinkedList<String> pathlist = new LinkedList<String>();
	private static int index = 0;

	
	/**
	 * Create the application.
	 */
	public test() {
		setLayout(null);

		DataSet.setModel(model);
		int width[] = { 10, 3, 5 };
		for (int i = 0; i < 3; i++) {
			TableColumn col = DataSet.getColumnModel().getColumn(i);
			col.setPreferredWidth(Xsize / 18 * width[i]);
		}
		
		MusicList = new JScrollPane(DataSet);
		add(MusicList);
		
		initialize();
	}

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*
		 * about Main Window (JFrame)
		 */
		test panel = new test();
		win.getContentPane().add(panel);
		win.setBounds(300, 150, Xsize, 600);
		win.setTitle("Music Tag Editor");
		win.setVisible(true);
	}

	public void phrase1() { // Path request phrases
		Font font2 = new Font("맑은 고딕", Font.ITALIC, 12);
		PathInput.setForeground(Color.gray);
		PathInput.setFont(font2);
		PathInput.setText("Enter directory address here");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/*
		 * about label (JLabel)
		 */
		JLabel lblNewLabel = new JLabel("Music File List");
		lblNewLabel.setBounds(17, 52, 110, 15);
		add(lblNewLabel);

		
		/*
		 * about PathInputWindow (textField)
		 */
		PathInput.setBounds(17, 13, 647, 23);
		add(PathInput);
		PathInput.setColumns(10);
		phrase1();
		PathInput.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				Font font1 = new Font("맑은 고딕", Font.PLAIN, 12);
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
			public void actionPerformed(ActionEvent e) { // Press Enter key
				File fileList[] = new File(PathInput.getText()).listFiles();
				findMusicFile(fileList);
				phrase1();
				PathInput.transferFocus();
			}
		});
		
		
		/*
		 * about add Button
		 */
		JButton addButton = new JButton("add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File fileList[] = new File(PathInput.getText()).listFiles();
				findMusicFile(fileList);
				phrase1();
			}
		});
		addButton.setBounds(676, 13, 91, 23);
		add(addButton);

		
		/*
		 * about MusicListWindow (textArea)
		 */
		MusicList.setBounds(17, 68, Xsize - 50, 439);
		add(MusicList);

		new FileDrop(MusicList, /* dragBorder, */ new FileDrop.Listener() {
			public void filesDropped(java.io.File[] fileList) {
				findMusicFile(fileList);
			} // end filesDropped
		}); // end FileDrop.Listener
		class key implements KeyListener {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 127) {
					removeMusic();
				}
			}
			public void keyReleased(KeyEvent e) {
			}
			public void keyTyped(KeyEvent e) {
			}

		}
		DataSet.addKeyListener(new key());
		DataSet.setFocusable(true);
		win.addWindowListener( new WindowAdapter() {  //when window is open
			public void windowOpened( WindowEvent e ){ 
				MusicList.requestFocus(); //MusicList gets focus
				} 
			}
		);

		/*
		 * about Remove Button
		 */
		JButton RemoveButton = new JButton("Remove");
		RemoveButton.setFont(new Font("굴림", Font.BOLD, 14));
		RemoveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeMusic();
			}
		});
		RemoveButton.setBounds(421, 520, 100, 30);
		add(RemoveButton);

		/*
		 * about Clear Button
		 */
		JButton ClearButton = new JButton("Clear All");
		ClearButton.setFont(new Font("굴림", Font.BOLD, 14));
		ClearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearList();
			}
		});
		ClearButton.setBounds(544, 520, 100, 30);
		add(ClearButton);

		/*
		 * about Running Button
		 */
		JButton RunningButton = new JButton("Run Edit");
		RunningButton.setFont(new Font("굴림", Font.BOLD, 14));
		RunningButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RunTagEditor();
			}
		});
		RunningButton.setBounds(667, 520, 100, 30);
		add(RunningButton);
		
		
	}

	public void findMusicFile(File[] fileList) {
		if (DataSet.getForeground() != Color.black) {
			clearList();
			DataSet.setForeground(Color.black);
		}
		for (int i = 0; i < fileList.length; i++) {
			String path = fileList[i].getPath();
			int idx = pathlist.indexOf(path); //return -1 if there is no path in pathlist

			if (idx == -1) { 
				String fileName = fileList[i].getName();

				if (fileName.matches(".*.mp3")) { //add only mp3 files
					index++;
					pathlist.add(fileList[i].getPath());
					namelist.add(fileName);

					File file = new File(pathlist.get(i)); // MP3 파일의 경로를 이용하여 File Object를 생성한다.
					try {
						Tag tag = AudioFileIO.read(file).getTag();
						String artist2 = tag.getFirst(FieldKey.ARTIST);
						String title2 = tag.getFirst(FieldKey.TITLE);

						model.addRow(new String[] { fileName, artist2, title2 });
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void removeMusic() {
		int[] row = DataSet.getSelectedRows();
		for (int i = 0; i < row.length; i++) {
			model.removeRow(row[i] - i);// subtract i to account for index difference from the rows which have already
										// been removed
			namelist.remove(row[i] - i);
			pathlist.remove(row[i] - i);
		}
		index -= row.length;
	}

	public void clearList() {
		index = 0;
		namelist.clear();
		pathlist.clear();
		model.setNumRows(0);
	}

	public void RunTagEditor() {
		String fileName, artist, title; // = "";
		int idx = 0; // index of " - "
		int idx2 = 0; // index of ".mp3",
		int cnt = 0, fail = 0; // count of file(s) successfully modified

		for (int i = 0; i < index; i++) {
			fileName = namelist.get(fail);
			if (fileName.matches(".* - .*")) {
				idx = fileName.indexOf(" - ");
				idx2 = fileName.indexOf(".mp3");
				artist = fileName.substring(0, idx);
				title = fileName.substring(idx + 3, idx2);
				rewriteTag(pathlist.get(fail), artist, title);
				cnt++;

				model.removeRow(fail);
				namelist.remove(fail);
				pathlist.remove(fail);
			} else {
				DataSet.setForeground(Color.red);
				fail++;
			}
		}

		index = index - cnt;
		JOptionPane.showMessageDialog(null,
				cnt + " file(s) successfully modified\n" + fail + " file(s) failed to modified", "Information",
				JOptionPane.WARNING_MESSAGE);
	}

	public static void rewriteTag(String path, String artist, String title) {
		File file = new File(path); // MP3 파일의 경로를 이용하여 File Object를 생성한다.
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
