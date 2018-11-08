package mpd;

public class ThreadedMinimumPairwiseDistance implements MinimumPairwiseDistance {

    public int[] values;

    @Override
    public long minimumPairwiseDistance(int[] values) {

        this.values = values;

        throw new UnsupportedOperationException();
    }

    public int[] updateGlobalResult(int localResult) {
        int globalResult = 0;

        if (localResult > globalResult){
            globalResult = localResult;
        }

        return null;
    }

    private class LowerLeft implements Runnable {

        public void run() {
            int N = values.length;
            long result = Integer.MAX_VALUE;

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

    }

    private class BottomRight implements Runnable {

        public void run() {
            int N = values.length;
            long result = Integer.MAX_VALUE;

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

    }

    private class TopRight implements Runnable {

        public void run() {
            int N = values.length;
            long result = Integer.MAX_VALUE;

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

    }

    private class Center implements Runnable {

        public void run() {
            int N = values.length;
            long result = Integer.MAX_VALUE;

            for (int j = 0; j < N; ++j) {
                for (int i = 0; i < N; ++i) {
                    // Gives us all the pairs (i, j) where N/2 ≤ i ≤ j + N/2 < N
                    long diff = Math.abs(values[i] - values[j]);
                    if (diff < result) {
                        result = diff;
                    }
                }
            }
        }

    }
}
