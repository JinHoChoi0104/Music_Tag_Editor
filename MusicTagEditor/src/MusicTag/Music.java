package MusicTag; // 클래스(interface, enum..)의 묶음으로서 하나의 디렉토리(폴더)이다.

import java.io.File;
import java.util.Scanner;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;


public class Music {
	public static void main(String args[]){
		//renameFile("NAAN.mp3","aa.mp3");
		
		// get input from std
		Scanner console = new Scanner(System.in);
		System.out.print("Enter mp3 file path: ");
		String song = console.nextLine();	  
		File path = new File(song);  // MP3 파일의 경로를 이용하여 File Object를 생성한다.  		  
		
		File fileList[] = path.listFiles();	
		
		String fileName = "";
		String artist = "";
		String title = "";
		int idx = 0;
	

		if(fileList.length > 0){
			for(File file : fileList)
			{
				fileName = file.getName();
				if(fileName.matches(".*.mp3")) {
					
					if(fileName.matches(".* - .*"))
					{
						idx = fileName.indexOf(" - ");						
						artist = fileName.substring(0,idx);
						title = fileName.substring(idx+3);
					//	rewriteTag(fileName, artist, title);
					
						System.out.println("wow!" + fileName);
					}	
					//else ;{
					//else
					//	System.out.println("error! there is no \"-\" ");//+ fileName);
					
					
				}
			}
		}
		System.out.println("end!");
	}
    
   
	
	public static void renameFile(String filename, String newFilename) {
	    File file = new File( filename );
	    File fileNew = new File( newFilename );
	    if( file.exists() ) file.renameTo( fileNew );
	}
	
	public static void rewriteTag(String path, String artist, String title) {
		File file = new File(path);  // MP3 파일의 경로를 이용하여 File Object를 생성한다.  		  
		try {  
			//make modification to the tag class
		    AudioFile f = AudioFileIO.read(file);
			Tag tag = f.getTag();
		 	tag.setField(FieldKey.ARTIST,artist);
		 	tag.setField(FieldKey.TITLE,title);
		 	
		 	//and then commit the change
		 	//f.commit();
		 	AudioFileIO.write(f);
		 	 
		} catch (Exception e) {  
		    e.printStackTrace();  
		}  

	}
}
