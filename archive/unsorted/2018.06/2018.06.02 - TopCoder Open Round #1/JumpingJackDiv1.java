package main;

public class JumpingJackDiv1 {
    public int getLocationOfJack(int dist, int k, int x) {
        return (x + 1 - x / k - 1) * dist;
    }
}
