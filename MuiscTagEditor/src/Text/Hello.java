package Text; // 클래스(interface, enum..)의 묶음으로서 하나의 디렉토리(폴더)이다.

import java.io.File;

//import org.jaudiotagger.audio.AudioFile;

//import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;


//import javax.imageio.ImageIO;
//import javax.imageio.stream.FileImageInputStream;
//import javax.imageio.stream.FileImageOutputStream;
//import org.jaudiotagger.audio;//.AudioFileIO;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.id3.AbstractID3v2Frame;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.ID3v24Tag;
import org.jaudiotagger.tag.id3.framebody.FrameBodyAPIC;
import org.jaudiotagger.tag.id3.framebody.FrameBodyTPE1;
import org.jaudiotagger.tag.id3.valuepair.TextEncoding;
import org.jaudiotagger.tag.reference.PictureTypes;
public class Hello {
    File fs;
    
    public static void main(String args[]){
         new SearchMp3("Directory path here").go();
    }
    
    public void go(){
        if(fs.isDirectory()){
            File list[] = fs.listFiles();
            for(File f : list){
                try{
                    MP3File mp3 = (MP3File) AudioFileIO.read(f);
                    AbstractID3v2Tag tag2 = mp3.getID3v2Tag();
                    
                    Tag tag = mp3.getTag();
                    String title = tag.getFirst(FieldKey.TITLE);
                    String artist = tag.getFirst(FieldKey.ARTIST);
                    String album = tag.getFirst(FieldKey.ALBUM);
                    String year = tag.getFirst(FieldKey.YEAR);
                    String genre = tag.getFirst(FieldKey.GENRE);
             
                    System.out.println("Tag : " + tag2);
                    System.out.println("Song Name : " + title);
                    System.out.println("Artist : " + artist);
                    System.out.println("Album : " + album);
                    System.out.println("Year : " + year);
                    System.out.println("Genre : " + genre);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }
    public SearchMp3(String path) {
        fs = new File(path);
    }
}
