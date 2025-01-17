package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Score {
    static String[] arrayHighScore;

    // Tør ikke pille, forstår det ikke
    public static void scoreAdder(Block block, Player player, int combo){
        player.updateCurrentScore((long) (block.getScore()+combo*block.getScore()*0.5));
    }
    
    public static void writeHighScore(Player player){
        Path path = Paths.get("Score.java").toAbsolutePath().getParent();
        String pathToFile=String.valueOf(path);
        pathToFile = pathToFile+"/src/resources/HighScore.txt";
        String score = ""+(long)(player.getCurrentScore());
        String name = player.getName();
        try{
            FileWriter highScoreWrite = new FileWriter(pathToFile, true);
            highScoreWrite.write(name+"="+score+"\n");
            highScoreWrite.close();

        } catch (IOException e){
            System.out.println("Error in writing highscore");
            e.printStackTrace();
        }
    }
    public static void readHighScore(){
        Path path = Paths.get("Score.java").toAbsolutePath().getParent();
        String pathToFile=String.valueOf(path);
        pathToFile = pathToFile+"/src/resources/HighScore.txt";
        try{
            String highscores = Files.readString(Paths.get(pathToFile)); //this line is the problem
            String dividers = "\n";
            arrayHighScore = highscores.split(dividers);

            if (arrayHighScore.length<10){
                String[] tempArr = new String[10];
                for (int i = 0; i<arrayHighScore.length;i++){
                    tempArr[i] = arrayHighScore[i];
                }
                for (int i = arrayHighScore.length;i<10;i++){
                    tempArr[i]="";
                }
                arrayHighScore = tempArr;
            }
            
        } catch(IOException e){
            System.out.println("Error in reading highscore");
            e.printStackTrace();
        }
        for (int i = 0; i<arrayHighScore.length; i++){
            if (cleanString(arrayHighScore[i]).length()>6){
                System.out.println("Only names of 5 letters are allowed in line " +(i+1));
                arrayHighScore[i]="Slimy=0000";
            }
            if (arrayHighScore[i].isEmpty() == true){
                arrayHighScore[i] = "Slime=0000";
            }
        }
    }
    public static boolean containsNonLetters(String str) {
        return !str.matches("[a-z A-Z 0-9]+");
    }
    public static long stringToInt(String s){
        long clean = Long.parseLong(s.replaceAll("\\D+",""));
        return clean;
    }
    public static String cleanString(String string){
        String clean = string.replaceAll("[0-9]","") ;
        clean = clean.replace(".","");
        return clean;
    }
    public static String[] arrayRankArrange(String[] string){
        long[] arrInt = new long[string.length];
        for (int l=0; l<string.length;l++){
            String scores[] = arrayHighScore[l].split("=");
            String scoreStr = scores[1];
            Long score = Long.valueOf(scoreStr);
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
        return arrayHighScore;
    }
}