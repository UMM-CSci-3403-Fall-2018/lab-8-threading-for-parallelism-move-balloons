package mpd;

public class ThreadedMinimumPairwiseDistance implements MinimumPairwiseDistance, Runnable {

    @Override
    public long minimumPairwiseDistance(int[] values) {
        //public int[] valArray = values;


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


    }

    public int[] updateGlobalResult(int localResult) {
        int globalResult = 0;

        if (localResult > globalResult){
            globalResult = localResult;
        }

        return null;
    }

    private class lowerLeft{
        private boolean rangeCheck = false;

        boolean getRangeCheck() {
            return rangeCheck;
        }

        synchronized void setRangeCheck(boolean newRangeCheck) {
            rangeCheck = newRangeCheck;
        }
    }

    private class bottomRight {
        private boolean rangeCheck = false;

        boolean getRangeCheck() {
            return rangeCheck;
        }

        synchronized void setRangeCheck(boolean newRangeCheck) {
            rangeCheck = newRangeCheck;
        }
    }

    private class topRight {
        private boolean rangeCheck = false;

        boolean getRangeCheck() {
            return rangeCheck;
        }

        synchronized void setRangeCheck(boolean newRangeCheck) {
            rangeCheck = newRangeCheck;
        }
    }

    private class center {
        private boolean rangeCheck = false;

        boolean getRangeCheck() {
            return rangeCheck;
        }

        synchronized void setRangeCheck(boolean newRangeCheck) {
            rangeCheck = newRangeCheck;
        }
    }
}
