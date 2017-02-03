package main;

import java.io.StringReader;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.TreeMap;
import java.util.TreeSet;

/*
 ID: hackint1
 LANG: JAVA
 TASK: zerosum
*/
public class Zerosum {
    String OP = " +-";
    String SEPARATOR = ";";
    int n;
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        n = in.nextInt();
        dfs(2, new StringBuilder("1"), out);
    }

    private void dfs(int cur, StringBuilder acc, PrintWriter out) {
        if (cur > n) {
            String accString = acc.toString();
            int left = 0;
            int i = 0;
            for (;i < accString.length(); ++i) {
                if (Character.isDigit(accString.charAt(i))) {
                    left = left * 10 + accString.charAt(i) - '0';
                    continue;
                }
                if (accString.charAt(i) == ' ') {
                    continue;
                }
                break;
            }
            while (true) {
                if (i >= accString.length()) break;

                char op = accString.charAt(i++);
                int right = 0;
                for (;i < accString.length(); ++i) {
                    if (Character.isDigit(accString.charAt(i))) {
                        right = right * 10 + accString.charAt(i) - '0';
                        continue;
                    }
                    if (accString.charAt(i) == ' ') {
                        continue;
                    }
                    break;
                }
                if (op == '+') left += right;
                if (op == '-') left -= right;
            }
            if (left == 0) out.println(accString);
//            Scanner in = new Scanner(new StringReader(accString));
//            in.useDelimiter(SEPARATOR);
//            int res = in.nextInt();
//            //assert res == 1;
//            while (true) {
//                if (!in.hasNext()) break;
//                String op = in.next();
//                int right = in.nextInt();
//                if (op.equals("+")) res += right;
//                if (op.equals("-")) res -= right;
//                //if (op.equals(" ")) res  = res * 10 + right;
//            }
//            if (res == 0) out.println(accString.replaceAll(SEPARATOR, ""));
            return;
        }

        for (int i = 0; i < OP.length(); ++i) {
            //if (OP.charAt(i) != ' ')
            acc.append(OP.charAt(i));
            acc.append(cur);
            dfs(cur + 1, acc, out);
            //if (OP.charAt(i) != ' ')
            acc.setLength(acc.length() - 1);
            acc.setLength(acc.length() - 1);
        }
    }
}
