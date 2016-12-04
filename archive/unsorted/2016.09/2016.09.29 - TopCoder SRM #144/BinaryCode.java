package main;

public class BinaryCode {
    public String[] decode(String message) {
        int[] dec = new int[message.length()];
        for (int i = 0; i < dec.length; ++i) dec[i] = message.charAt(i) - '0';
        String org1 = getOrg(0, dec);
        String org2 = getOrg(1, dec);

        return new String[] {org1, org2};
    }

    private String getOrg(int init0, int[] dec) {
        String FAIL = "NONE";
        int[] org = new int[dec.length];
        org[0] = init0;
        for (int i = 0; i < org.length; ++i) {
            if (org[i] != 0 && org[i] != 1) return FAIL;

            int pre = i - 1 < 0 ? 0 : org[i - 1];

            if (i == org.length - 1 && pre + org[i] != dec[i])
                return FAIL;

            if (i == org.length - 1) break;

            org[i + 1] = dec[i] - org[i] - pre;
        }
        String ans = "";
        for (int i : org) ans += i;
        return ans;
    }
}
