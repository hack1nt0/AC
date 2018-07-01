package template.graph_theory;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class GraphUtils {


    public static void vizDirected(List<Integer>[] adj) {
        int n = adj.length;
        String[] nodes = new String[n];
        for (int i = 0; i < n; ++i) nodes[i] = "" + i;
        List<String> edges = new ArrayList<>();
        for (int i = 0; i < n; ++i)
            for (int j : adj[i])
                edges.add("" + i + " -> " + j);
        viz(true, true, false, nodes, edges.toArray(new String[0]));
    }

    public static void viz(boolean vertical, boolean directed, boolean edgeDuplicated,
                           String[] nodes, String[] edges) {
        StringBuffer dot = new StringBuffer();
        dot.append(edgeDuplicated ? "" : "strict");
        dot.append(directed ? " digraph " : " graph ");
        dot.append(" TMP {\n");
        dot.append(vertical ? "" : "rankdir = LR\n");
        for (int i = 0; i < nodes.length; ++i) dot.append(nodes[i]).append('\n');
        for (int i = 0; i < edges.length; ++i) dot.append(edges[i]).append('\n');
        dot.append("}");
        viz("TMP", dot.toString());
    }

    public static void viz(String title, String dot) {
        try {
            File input = File.createTempFile(title, ".dot");
            File output = File.createTempFile(title, ".pdf");
            PrintWriter dotWriter = new PrintWriter(new FileWriter(input));
            dotWriter.println(dot.toString());
            dotWriter.close();
            String dotCommd = "dot " + input.getAbsolutePath() + " -Tpdf -o " + output.getAbsolutePath();
            Runtime.getRuntime().exec(new String[] { "bash", "-c", dotCommd}).waitFor();
            Runtime.getRuntime().exec(new String[] { "bash", "-c", "open " + output.getAbsolutePath()}).waitFor();
            Thread.sleep(500);

            input.deleteOnExit();
            output.deleteOnExit();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Draw draw = new Draw(title);
//        draw.setCanvasSize(w * 100, h * 100);
//        draw.picture(.5, .5, tmp.getAbsolutePath());
        //while (!tmp.delete());
    }
}