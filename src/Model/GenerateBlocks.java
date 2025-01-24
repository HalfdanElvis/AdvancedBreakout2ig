//this file generates blocks based on current level using gaussian probability

package Model;

import java.util.ArrayList;

public class GenerateBlocks {
    public static ArrayList<Block> generateBlocks(int level) {
        double rows = 4 + Math.floor(level/5.0);
        double columns = 5 + Math.floor(level/5.0);
        ArrayList<Block> blockList = new ArrayList<>();

        //spacing between blocks
        double space = Model.OptionsModel.getSceneWidth()/Math.sqrt(columns)/Math.sqrt(rows)/40;
        double blockWidth = Model.OptionsModel.getSceneWidth()/columns-space;
        double blockHeight = Model.OptionsModel.getSceneHeight()/3/rows-space;

        //generates thresholds, mainly to allow Upgrade to use the same methods as GenerateBlocks
        double[] thresholds = new double[Block.getMaxTier()+1];
        for (int i = 0; i < thresholds.length; i++) {
            thresholds[i] = i;
        }
        //generates probabilities for each row, so the top rows have the most powerful blocks
        //each row has a probabilities-array associated with it
        //each index in a probabilities-array represents the chance for each tier
        double[][] probabilities = new double[(int) rows][Block.getMaxTier()];
        for (int i = 0; i < probabilities.length; i++) {
            // level makes the max tier only go up to current level, eg, level 1 can only generate tier 1
            // Math.sqrt(level/5) makes the tiers of blocks scale with levels, eg higher level generates (mostly) higher tiers
            // 5+i/rows*10 makes the tiers of blocks scale with rows
            // 0.9 determines the spread
            probabilities[probabilities.length-1-i] = NormalDistribution.generateProbabilities(Math.min(level, Block.getMaxTier()+1), Math.sqrt(level/5)-5+i/rows*10, 0.9, thresholds);
        }
        //generates the blocks based on the probabilities
        for (double i = 0; i < columns; i++) {
            for (double j = 0; j < rows; j++) {
                //generates empty spaces randomly, top rows have low amount of spaces
                //while bottom rows have high amount of spaces
                if (Math.random() > Math.exp(-Math.pow(j, 2)/150/Math.pow(level, 0.25)*10/rows)) {
                    continue;
                }
                int tier = NormalDistribution.chooseTier(probabilities[(int) j]);
                Block block = new Block(tier, level, (blockWidth+space)*i+space/2, (blockHeight+space)*j+space/2+OptionsModel.getSceneHeight()/6, blockWidth, blockHeight);
                blockList.add(block);
            }
        }
        return blockList;
    }
}