package mpd;

public class ThreadedMinimumPairwiseDistance implements MinimumPairwiseDistance {

    private int[] values;
    private long globalResult = Integer.MAX_VALUE;

    @Override
    public long minimumPairwiseDistance(int[] values) {

        Thread one = new Thread(new LowerLeft());
        Thread two = new Thread(new BottomRight());
        Thread three = new Thread(new TopRight());
        Thread four = new Thread(new Center());

        Thread[] threads = new Thread[4];
        threads[0] = one;
        threads[1] = two;
        threads[2] = three;
        threads[3] = four;

        this.values = values;

        for (int i = 0; i < 4; i++) {
            threads[i].start();
        }

        for (int i = 0; i < 4; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return globalResult;
    }

    private long updateGlobalResult(long localResult) {
        if (localResult < globalResult){
            globalResult = localResult;
        }

        return globalResult;
    }

    private class LowerLeft implements Runnable {

        public void run() {
            int N = values.length;
            long result = Integer.MAX_VALUE;

            //System.out.println("n = " + N);
            for (int i = 0; i < N/2; ++i) {
                for (int j = 0; j < i; ++j) {
                    // Gives us all the pairs (i, j) where 0 ≤ j < i < N/2
                    long diff = Math.abs(values[i] - values[j]);
                    //System.out.println(diff);
                    if (diff < result) {
                        result = diff;
                    }
                }
            }
            updateGlobalResult(result);
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
            updateGlobalResult(result);
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
            updateGlobalResult(result);
        }

    }

    private class Center implements Runnable {

        public void run() {
            int N = values.length;
            long result = Integer.MAX_VALUE;

            for (int j = 0; j < N/2 ; ++j) {
                for (int i = N/2; i <= j + N/2 ; ++i) {
                    // Gives us all the pairs (i, j) where N/2 ≤ i ≤ j + N/2 < N
                    long diff = Math.abs(values[i] - values[j]);
                    if (diff < result) {
                        result = diff;
                    }
                }
            }
            updateGlobalResult(result);
        }

    }
}
