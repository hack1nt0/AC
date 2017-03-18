package main;

import java.util.Scanner;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: heritage
*/

public class Heritage {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        char[] inOrder = in.next().toCharArray();
        char[] preOrder = in.next().toCharArray();
        dfs(inOrder, 0, inOrder.length, preOrder, 0, preOrder.length, out);
        out.println();
    }

    private void dfs(char[] inOrder, int inFrom, int inTo, char[] preOrder, int preFrom, int preTo, PrintWriter out) {
        if (inTo <= inFrom) return;
        char root = preOrder[preFrom];
        for (int i = inFrom; i < inTo; ++i) {
            if (inOrder[i] == root) {
                dfs(inOrder, inFrom, i, preOrder, preFrom + 1, preFrom + 1 + i - inFrom, out);
                dfs(inOrder, i + 1, inTo, preOrder, preFrom + 1 + i - inFrom, preTo, out);
                break;
            }
        }
        out.print(root);
    }
}
