package Model;

public class NormalDistribution {
        //generates an array where each index represents the probability of a tier
        public static double[] generateProbabilities(int amount, double mean, double spread, double[] thresholds) {
            double[] probabilities = new double[amount];
            double sum = 0;
            for (int tier = 0; tier < probabilities.length; tier++) {
                probabilities[tier] = gaussianProbability(thresholds[tier], mean, spread);
                sum += probabilities[tier];
            }
            //normalizes them (so the sum is 1)
            for (int tier = 0; tier < probabilities.length; tier++) {
                probabilities[tier] /= sum;
            }
            return probabilities;
        }
    
        //normal distribution function
        private static double gaussianProbability(double x, double mean, double spread) {
            return Math.exp(-Math.pow(x - mean, 2) / (2 * Math.pow(spread, 2))) / (spread * Math.sqrt(2 * Math.PI));
        }
    
        //chooses which tier based on the probabilities
        public static int chooseTier(double[] probabilities) {
            double randomValue = Math.random();
            double cumulative = 0;
            for (int tier = 0; tier < probabilities.length; tier++) {
                cumulative += probabilities[tier];
                if (randomValue <= cumulative) {
                    return tier;
                }
            }
            return probabilities.length-1;
        }
}
