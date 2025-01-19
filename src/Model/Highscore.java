//this file reads and writes from highscore.txt to keep track of highscores locally
//it also sorts the highscores so it only shows top 10
//written by Simon

package Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Highscore {
    static String[] arrayHighscore;
    private static String filePath = System.getProperty("user.dir")+"/highscore.txt";
    
    //writes the current highscore list to highscore.txt
    public static void writeHighscore(Player player){
        String score = ""+(long)(player.getCurrentScore());
        String name = player.getName();
        try{
            FileWriter highscoreWrite = new FileWriter(filePath, true);
            highscoreWrite.write(name+"="+score+"\n");
            highscoreWrite.close();

        } catch (IOException e){
            File file = new File(filePath);
            try {
                file.createNewFile();
            } catch (IOException e1) {
            }
        }
    }

    //reads the highscores from highscore.txt
    public static void readHighscore(){
        File file = new File(filePath);
        //creates highscore.txt if it doesn't exist
        if (!file.isFile()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try{
            String highscores = Files.readString(Paths.get(filePath));
            String dividers = "\n";
            arrayHighscore = highscores.split(dividers);

            if (arrayHighscore.length<10){
                String[] tempArr = new String[10];
                for (int i = 0; i<arrayHighscore.length;i++){
                    tempArr[i] = arrayHighscore[i];
                }
                for (int i = arrayHighscore.length;i<10;i++){
                    tempArr[i]="";
                }
                arrayHighscore = tempArr;
            }
            
        } catch(IOException e){
            e.printStackTrace();
        }

        for (int i = 0; i<arrayHighscore.length; i++){
            if (arrayHighscore[i].isEmpty() == true){
                arrayHighscore[i] = "Slime=0000";
            }
        }
    }

    //sorts the scores
    public static String[] arrayRankArrange(String[] string){
        long[] arrInt = new long[string.length];
        for (int l=0; l<string.length; l++){
            String scores[] = arrayHighscore[l].split("=");
            String scoreStr = scores[1];
            Long score = Long.valueOf(scoreStr.trim());
            arrInt[l] = score;
        }

        long temp;
        String temp2;
        for (int i =0; i<arrInt.length; i++){
            for(int j=i+1; j<arrInt.length; j++){
                if(arrInt[i] < arrInt[j]) {    
                    temp = arrInt[i];    
                    temp2 = string[i];
                    arrInt[i] = arrInt[j];  
                    string[i] = string[j];  
                    arrInt[j] = temp;    
                    string[j] = temp2;
                }     
            }
        }
        return string;
    }
        
    public static String[] getHighscore(){
        return arrayHighscore;
    }
}