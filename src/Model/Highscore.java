package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Highscore {
    static String[] arrayHighscore;
    
    public static void writeHighscore(Player player){
        Path path = Paths.get("Score.java").toAbsolutePath().getParent();
        String pathToFile=String.valueOf(path);
        pathToFile = pathToFile+"/src/resources/Highscore.txt";
        String score = ""+(long)(player.getCurrentScore());
        String name = player.getName();
        try{
            FileWriter highscoreWrite = new FileWriter(pathToFile, true);
            highscoreWrite.write(name+"="+score+"\n");
            highscoreWrite.close();

        } catch (IOException e){
            System.out.println("Error in writing highscore");
            e.printStackTrace();
        }
    }
    public static void readHighscore(){
        Path path = Paths.get("Score.java").toAbsolutePath().getParent();
        String pathToFile=String.valueOf(path);
        pathToFile = pathToFile+"/src/resources/Highscore.txt";
        try{
            String highscores = Files.readString(Paths.get(pathToFile)); //this line is the problem
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
            System.out.println("Error in reading highscore");
            e.printStackTrace();
        }
        for (int i = 0; i<arrayHighscore.length; i++){
            if (arrayHighscore[i].isEmpty() == true){
                arrayHighscore[i] = "Slime=0000";
            }
        }
    }

    public static String[] arrayRankArrange(String[] string){
        long[] arrInt = new long[string.length];
        for (int l=0; l<string.length;l++){
            String scores[] = arrayHighscore[l].split("=");
            String scoreStr = scores[1];
            Long score = Long.valueOf(scoreStr.trim());
            arrInt[l] = score;
        }
        long temp;
        String temp2;
        for (int i =0; i<arrInt.length;i++){
            for(int j=i+1; j<arrInt.length;j++){
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