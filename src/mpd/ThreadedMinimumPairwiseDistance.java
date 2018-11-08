package mpd;

public class ThreadedMinimumPairwiseDistance implements MinimumPairwiseDistance, Runnable {

    public int[] values;

    @Override
    public long minimumPairwiseDistance(int[] values) {

        this.values = values;

        throw new UnsupportedOperationException();
    }

    public void run() {
        // Example to base our run on from Serial
        /*
        long result = Integer.MAX_VALUE;
        for (int i = 0; i < values.length; ++i) {
            for (int j = 0; j < i; ++j) {
                // Gives us all the pairs (i, j) where 0 <= j < i < values.length
                long diff = Math.abs(values[i] - values[j]);
                if (diff < result) {
                    result = diff;
                }
            }
        }
        */

        LowerLeft lowerLeft = new LowerLeft();
        BottomRight bottomRight = new BottomRight();
        TopRight topRight = new TopRight();
        Center center = new Center();

        int N = values.length;

        long result = Integer.MAX_VALUE;
        // Lower left DONE
        if(lowerLeft.getRangeCheck()) {
            for (int i = 0; i < N/2; ++i) {
                for (int j = 0; j < i; ++j) {
                    // Gives us all the pairs (i, j) where 0 ≤ j < i < N/2
                    long diff = Math.abs(values[i] - values[j]);
                    if (diff < result) {
                        result = diff;
                    }
                }
            }
        }
        // Bottom right DONE?
        if(bottomRight.getRangeCheck()) {
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j + N/2 < i; ++j) {
                    // Gives us all the pairs (i, j) where N/2 ≤ j + N/2 < i < N
                    long diff = Math.abs(values[i] - values[j]);
                    if (diff < result) {
                        result = diff;
                    }
                }
            }
        }
        // Top right DONE? probs not
        if(topRight.getRangeCheck()) {
            for (int i = N/2; i < N; ++i) {
                for (int j = N/2; j < i; ++j) {
                    // Gives us all the pairs (i, j) where N/2 ≤ j < i < N
                    long diff = Math.abs(values[i] - values[j]);
                    if (diff < result) {
                        result = diff;
                    }
                }
            }
        }
        // Center DONE?
        if(center.getRangeCheck()) {
            for (int i = 0; i < N; ++i) {
                for (int j = 0; i <= j + N/2; ++j) {
                    // Gives us all the pairs (i, j) where N/2 ≤ i ≤ j + N/2 < N
                    long diff = Math.abs(values[i] - values[j]);
                    if (diff < result) {
                        result = diff;
                    }
                }
            }
        }
    }

    public int[] updateGlobalResult(int localResult) {
        int globalResult = 0;

        if (localResult > globalResult){
            globalResult = localResult;
        }

        return null;
    }

    private class LowerLeft{
        private boolean rangeCheck = false;

        boolean getRangeCheck() {
            int i, j;
            return rangeCheck;
        }

        synchronized void setRangeCheck(boolean newRangeCheck) {
            rangeCheck = newRangeCheck;
        }
    }

    private class BottomRight {
        private boolean rangeCheck = false;

        boolean getRangeCheck() {
            return rangeCheck;
        }

        synchronized void setRangeCheck(boolean newRangeCheck) {
            rangeCheck = newRangeCheck;
        }
    }

    private class TopRight {
        private boolean rangeCheck = false;

        boolean getRangeCheck() {
            return rangeCheck;
        }

        synchronized void setRangeCheck(boolean newRangeCheck) {
            rangeCheck = newRangeCheck;
        }
    }

    private class Center {
        private boolean rangeCheck = false;

        boolean getRangeCheck() {
            return rangeCheck;
        }

        synchronized void setRangeCheck(boolean newRangeCheck) {
            rangeCheck = newRangeCheck;
        }
    }
}
