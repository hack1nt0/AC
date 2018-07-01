package main;

public class StairClimb {
    public int stridesTaken(int[] flights, int stairsPerStride) {
        int ans = (flights.length - 1) * 2;
        for (int i = 0; i < flights.length; ++i) {
            ans += (flights[i] + stairsPerStride - 1) / stairsPerStride;
        }
        return ans;
    }
}
