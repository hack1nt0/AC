package main;

public class DinkyFish {
    public int monthsUntilCrowded(int tankVolume, int maleNum, int femaleNum) {
        if (maleNum + femaleNum > tankVolume * 2)
            return 0;
        int couples = Math.min(maleNum, femaleNum);
        int single = maleNum + femaleNum - couples * 2;
        //couples * 2 ^ n * 2 + single > 2 * V
        int n = (int) Math.ceil(Math.log((2.0 * tankVolume - single) / couples / 2) / Math.log(2));
        if (couples * Math.pow(2, n) * 2 + single <= 2 * tankVolume)
            n++;
        return n;
    }
}
