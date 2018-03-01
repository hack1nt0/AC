package main;

public class StringTrain {
    public String buildTrain(String[] cars) {
        String train = cars[0];
        for (int i = 1; i < cars.length; ++i) {
            String car = cars[i];
            int maxCommon = 0;
            for (int common = 1; common < Math.min(train.length(), car.length()); ++common) {
                if (train.substring(train.length() - common).equals(car.substring(0, common))) {
                    maxCommon = common;
                }
            }
            if (0 < maxCommon) {
                train += car.substring(maxCommon);
            }
        }
        boolean[] visited = new boolean[128];
        StringBuilder shrunk = new StringBuilder();
        for (int i = train.length() - 1; i >= 0; --i) {
            char c = train.charAt(i);
            if (!visited[c]) {
                shrunk.append(c);
                visited[c] = true;
            }
        }
        return "" + train.length() + " " + shrunk.reverse();
    }
}
