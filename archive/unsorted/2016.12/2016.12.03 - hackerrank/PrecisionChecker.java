package chelper;

import net.egork.chelper.checkers.Checker;
import net.egork.chelper.checkers.StrictChecker;
import net.egork.chelper.checkers.TokenChecker;
import net.egork.chelper.tester.Verdict;

import java.util.*;

/**
 * Created by dy on 16-12-2.
 */
public class PrecisionChecker implements Checker{
    //(cmd: verbose:int) Print only precision msg=0, plus wrong cases=1, plus right case=2
    int verbose = 1;
    //(cmd: tt:int) test type: test number known(appear in first line)=0, test number unknown=1
    int testType = 0;
    //(cmd: sct:int) single checker type: StrictChecker=1, TokenChecker(space or min float error arent accounted for)=0
    Checker singleChecker = new TokenChecker("1e-9");


    public PrecisionChecker(String params1) throws Exception {
        if (params1 == null || params1.trim().length() == 0) return;

        String[] tmp = params1.split("[ ]");
        Map<String, String> params = new HashMap<String, String>();
        for (int i = 0; i < tmp.length; ++i) {
            String[] kv = tmp[i].split("=");
            params.put(kv[0], kv[1]);
        }
        for (String key : params.keySet()) {
            int vn = Integer.valueOf(params.get(key));
            if (key.endsWith("verbose")) {
                verbose = vn;
            }
            if (key.endsWith("tt")) {
                testType = vn;
            }
            if (key.endsWith("sct")) {
                if (vn == 1) singleChecker = new StrictChecker(null);
            }

        }
        System.out.println(params1);
    }

    @Override
    public Verdict check(String input, String expected, String executed) {
//        if (expected == null) {
//            return Verdict.UNDECIDED;
//        }

        //Iterators, which can segment bulk input string to single test strings.
        Iterator<String> inputItr = new GraphIterator(input, 1);
        Iterator<String> expectedItr = new NLinesIterator(expected, 1);
        Iterator<String> executedItr = new NLinesIterator(executed, 1);

        if (testType == 0) {
            inputItr.remove();
        } else if (testType < 0 || 1 < testType) throw new IllegalArgumentException("0<=tt<=3");

        StringBuffer wrongs = new StringBuffer();
        wrongs.append("\n");
        Frame<Row> resultFrame = new Frame<Row>();
        while (true) {
            if (!inputItr.hasNext()) break;
            String inp = inputItr.next();
            if (expected == null){
                if (executedItr.hasNext()) {
                    String exe = executedItr.next();
                    resultFrame.add(new Row(inp, exe));
                } else {
                    break;
                }
            } else {
                boolean A = executedItr.hasNext();
                boolean B = expectedItr.hasNext();
                if (!A && !B) break;
                String exe = "Not provided";
                String exp = "Not provided";
                Verdict singleVerdict = null;
                if (A && !B) {
                    exe = executedItr.next();
                    singleVerdict = new Verdict(Verdict.VerdictType.PE, "Only " + resultFrame.size() + " tokens expected");
                }
                if (!A && B) {
                    exp = executedItr.next();
                    singleVerdict = new Verdict(Verdict.VerdictType.PE, "More than" + resultFrame.size() + " tokens expected");
                }
                if (A && B) {
                    exe = executedItr.next();
                    exp = expectedItr.next();
                    singleVerdict = singleChecker.check(inp, exp, exe);
                }
                resultFrame.add(new Row(inp, exp, exe, singleVerdict));
            }
        }

        long bad = 0;
        long tot = resultFrame.size();
        for (Row r : resultFrame) if (r.verdictResult.type != Verdict.VerdictType.OK) bad++;
        double precision = (double) (tot - bad) / tot;
        String precisionMsg = "Precision: " + precision + "(" + (tot - bad) + "/" + tot + ")";
        if (verbose == 0)
            return new Verdict(Verdict.VerdictType.OK, precisionMsg);
        if (verbose == 2)
            return new Verdict(Verdict.VerdictType.OK, resultFrame + precisionMsg);
        if (verbose == 1) {
            Frame<Row> wrongFrame = new Frame<Row>();
            if (precision != 1)
                for (Row r : resultFrame) if (r.verdictResult.type != Verdict.VerdictType.OK) wrongFrame.add(r);
            return new Verdict(Verdict.VerdictType.OK, wrongFrame + precisionMsg);
        }
        return null;
    }

    class Row {
        String input, expected = "Not provided", executed;
        Verdict verdictResult = Verdict.UNDECIDED;

        public Row(String input, String expected, String executed, Verdict verdictResult) {
            this.input = input;
            this.expected = expected;
            this.executed = executed;
            this.verdictResult = verdictResult;
        }

        public Row(String input, String executed) {
            this.input = input;
            this.executed = executed;
        }

        @Override
        public String toString() {
            return "Input:\n" + input + '\n' +
                    "Expected output:\n" + expected + '\n' +
                    "Execution output:\n" + executed + '\n' +
                    "Verdict: " + verdictResult + '\n' +
                    "------------------------------------------------------------------\n";
        }
    }

    class Frame<T> implements Iterable<T> {
        ArrayList<T> iList = new ArrayList<T>();
        public void add(T e) {
            iList.add(e);
        }

        @Override
        public String toString() {
            StringBuffer ret = new StringBuffer();
            for (T e : iList) ret.append(e);
            return ret.toString();
        }

        public int size() {
            return iList.size();
        }

        public Iterator<T> iterator() {
            return iList.iterator();
        }

    }
}
