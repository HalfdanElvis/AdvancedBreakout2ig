package Model;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class HighscoreManager {

    public static CompletableFuture<Object> saveHighScore(String name, long score) {
        Firestore db = FirestoreClient.getFirestore();
    
        return CompletableFuture.supplyAsync(() -> {
            try {
                // get current top 10 scores
                List<QueryDocumentSnapshot> topScores = db.collection("highscores")
                    .orderBy("score", com.google.cloud.firestore.Query.Direction.DESCENDING)
                    .limit(10)
                    .get()
                    .get()
                    .getDocuments();
    
                //checks if score should be added
                if (topScores.size() < 10 || score > topScores.get(topScores.size() - 1).getLong("score")) {
    
                    //find lowest score if it needs to be removed
                    //gets removed later to ensure something is added before removed
                    String lowestScoreId = null;
                    if (topScores.size() >= 10) {
                        lowestScoreId = topScores.get(topScores.size() - 1).getId();
                    }

                    //adds the highscore to database
                    Map<String, Object> highScore = new HashMap<>();
                    highScore.put("name", name);
                    highScore.put("score", score);
                    db.collection("highscores").add(highScore).get(); // add the new score first
    
                    // then if more than 10 scores, remove the lowest one
                    if (lowestScoreId != null) {
                        db.collection("highscores").document(lowestScoreId).delete().get();
                    }
                }
    
                return null; // CompletableFuture requires a return value
            } catch (Exception e) {
                throw new RuntimeException("Error managing highscores", e);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            return null; // handle errors without propagating
        });
    }

    public static String[] getHighscores() {
        Firestore db = FirestoreClient.getFirestore();
        String[] highscores = new String[10];
    
        try {
            // get current top 10
            List<QueryDocumentSnapshot> documents = db.collection("highscores")
                .orderBy("score", com.google.cloud.firestore.Query.Direction.DESCENDING)
                .limit(10)
                .get()
                .get()
                .getDocuments();
    
            // creates the string, and adds placeholders if less than 10 scores
            for (int i = 0; i < highscores.length; i++) {
                if (i < documents.size()) {
                    QueryDocumentSnapshot document = documents.get(i);
                    highscores[i] = document.getString("name") + "=" + document.getLong("score");
                }
                else {
                    highscores[i] = "Slime=0000";
                }
            }
        } catch (Exception e) {
            System.err.println("Error retrieving high scores: " + e.getMessage());
            for (int i = 0; i < highscores.length; i++) {
                highscores[i] = "Slime=0000";
            }
        }
        return highscores;
    }
    
}
