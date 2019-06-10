public class PlayingWithPlanks {
    public String canItBeDone(int plankLength, int pieces) {
        boolean ok = dfs(plankLength, pieces);
        return ok ? "possible" : "impossible";
    }

    private boolean dfs(int plankLength, int pieces) {
        if (pieces == 0)
            return true;
        if (plankLength < pieces)
            return false;
        boolean res = dfs(plankLength - pieces, pieces - 1);
        //|| dfs(plankLength - pieces, pieces - 1);
        return res;
    }

}
