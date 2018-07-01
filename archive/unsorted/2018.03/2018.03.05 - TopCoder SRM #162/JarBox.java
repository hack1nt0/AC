package main;

public class JarBox {
    public int numJars(int radius, int woodlength) {
        int ans = 0;
        for (int width = radius * 2; woodlength - width * 2 >= radius * 2; ++width) {
            int curJars = 0;
            int rows = (int)(((woodlength - width * 2) / 2.0 - radius * 2) / Math.sqrt(radius * 2 * radius * 2 - radius * radius)) + 1;
            for (int row = 0; row < rows; ++row) {
                if (row % 2 == 0)
                    curJars += width / (radius * 2);
                else
                    curJars += (width - radius) / (radius * 2);
            }
            ans = Math.max(ans, curJars);
//            if (curJars > 784) {
//                System.out.println(curJars);
//            }
        }
        return ans;
    }
}
