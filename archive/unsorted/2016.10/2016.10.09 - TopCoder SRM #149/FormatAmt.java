package main;

public class FormatAmt {
    public String amount(int dollars, int cents) {
        String D = String.valueOf(dollars);
        StringBuffer ans = new StringBuffer();
        for (int i = 0; i < D.length(); ++i) {
            if (i != 0 && i % 3 == 0) ans.append(",");
            ans.append(D.charAt(D.length() - i - 1));
        }
        ans.reverse();

        String C = String.valueOf(cents);
        ans.append(".");
        if (C.length() < 2) ans.append("0");
        ans.append(C);
        return "$" + ans.toString();
    }
}
