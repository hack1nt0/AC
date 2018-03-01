package main;

import template.string.StringUtils;

import java.util.Arrays;

public class TennisRallies {
    public int howMany(int numLength, String[] forbidden, int allowed) {
        String shots = "cd";
        StringUtils.PrefixAutoMationNode[] nodes = StringUtils.buildPrefixAutomation(Arrays.asList(forbidden), 'd' + 1);
        int[][][] count = new int[numLength + 1][nodes.length][allowed + 1];
        for (int i = numLength; i >= 0; --i) {
            for (int state = 0; state < nodes.length; ++state) {
                StringUtils.PrefixAutoMationNode curNode = nodes[state];
                for (int fc = 0; fc <= allowed; ++fc) {
                    int res = 0;
                    if (i == numLength) {
                        if (fc < allowed) res = 1;
                    } else if (fc == allowed) {
                        res = 0;
                    } else {
                        for (int shot = 0; shot < shots.length(); ++shot) {
                            int c = shots.charAt(shot);
                            StringUtils.PrefixAutoMationNode nextNode = nodes[0];
                            int nFc = fc;
                            if (curNode.childs[c] != null) {
                                nextNode = curNode.childs[c];
                            } else {
                                StringUtils.PrefixAutoMationNode prefix = curNode.prefix;
                                while (prefix.id != 0) {
                                    if (prefix.childs[c] != null) {
                                        nextNode = prefix.childs[c];
                                        break;
                                    }
                                    prefix = prefix.prefix;
                                }
                                if (prefix.id == 0 && prefix.childs[c] != null) {
                                    nextNode = prefix.childs[c];
                                }
                            }
                            StringUtils.PrefixAutoMationNode prefix = nextNode;
                            while (prefix.id != 0) {
                                if (prefix.terminal) ++nFc;
                                prefix = prefix.prefix;
                            }
                            if (nFc <= allowed) res += count[i + 1][nextNode.id][nFc];
                        }
                    }
                    count[i][state][fc] = res;
                }
            }
        }
        return count[0][0][0];
    }

}
