package template.operation;

import template.graph_theory.CCTarjan;

/**
 * Created by dy on 16-12-20.
 */
public class SAT2 {

    CCTarjan cc;
    int variables;

    public SAT2(int N) {
        if (N % 2 != 0) throw new RuntimeException();
        variables = N / 2;
        cc = new CCTarjan(N);
    }

    public void addClosure(int a, boolean inva, int b, boolean invb) {
        if (!inva && !invb) {
            cc.addE(a + variables, b);
            cc.addE(b + variables, a);
        } else if (!inva && invb) {
            cc.addE(a + variables, b + variables);
            cc.addE(b, a);
        } else if (inva && !invb) {
            cc.addE(a, b);
            cc.addE(b + variables, a + variables);
        } else if (inva && invb) {
            cc.addE(a, b + variables);
            cc.addE(b, a + variables);
        }
    }

    public void removeClosure(int a, boolean inva, int b, boolean invb) {
        if (!inva && !invb) {
            cc.removeE(a + variables, b);
            cc.removeE(b + variables, a);
        } else if (!inva && invb) {
            cc.removeE(a + variables, b + variables);
            cc.removeE(b, a);
        } else if (inva && !invb) {
            cc.removeE(a, b);
            cc.removeE(b + variables, a + variables);
        } else if (inva && invb) {
            cc.removeE(a, b + variables);
            cc.removeE(b, a + variables);
        }
    }

    public boolean check() {
        int[] idscc = cc.scc();
        for (int i = 0; i < variables; ++i)
            if (idscc[i] == idscc[i + variables]) return false;
        return true;
    }

    public boolean[] value() {
        int[] idscc = cc.scc();
        return null;
    }
}
