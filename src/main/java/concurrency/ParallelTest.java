package concurrency;

import template.graph_theory.Edge;
import template.graph_theory.Graph;

import java.util.concurrent.*;

/**
 * Created by dy on 16-12-25.
 */
public class ParallelTest {
    public static void main(String[] args) {
//        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
//        map.put("foo", "bar");
//        map.put("han", "solo");
//        map.put("r2", "d2");
//        map.put("c3", "p0");
//        map.put("foo1", "bar");
//        map.put("han1", "solo");
//        map.put("r21", "d2");
//        map.put("c31", "p0");
//        map.search(1, (key, value) -> {
//            System.out.printlnTable("Search: " + Thread.currentThread().getName() + " " + key + "," + value);
//            return key.equals("r2");
//        });
//
//
//        map.forEach(1, (key, value) -> map.compute(key,
//                (key1, value1) -> {
//                    System.out.printlnTable("Transform: " + Thread.currentThread().getName() + " " + key + "," + value);
//                    return key + "=" + value;
//                }));
//
//        String result = map.reduceValues(100,
//                (s1, s2) -> {
//                    System.out.printlnTable("Reduce: " + Thread.currentThread().getName() + " " + s1 + "," + s2);
//                    return s1 + ", " + s2;
//                });
//
//        System.out.printlnTable("Result: " + result);

        int N = 30;
        int M = 100;
        Graph g = new Graph(N);
        int root = -1;
        for (int e = 0; e < M;) {
            int a = (int)(Math.random() * N);
            if (a >= N) continue;
            int b = (int)(Math.random() * N);
            if (b >= N) continue;
            root = a;
            g.addEdge(new Graph.Edge(a, b, 1));
            g.addEdge(new Graph.Edge(b, a, 1));
            e++;
        }
        pDfs(root, g);

    }

    static Semaphore[] semaphores;
    public static void pDfs(int root, Graph g) {
//        locks = new ReentrantLock[g.N];
//        for (int i = 0; i < locks.length; ++i) locks[i] = new ReentrantLock();
        semaphores = new Semaphore[g.N];
        for (int i = 0; i < semaphores.length; ++i) semaphores[i] = new Semaphore(1);
        final ForkJoinPool forkJoinPool = new ForkJoinPool();
        for (int i = 0; i < semaphores.length; ++i) if (semaphores[i].getQueueLength() == 0) {
            forkJoinPool.invoke(new pDfsHelper(i, g));
            while (forkJoinPool.getRunningThreadCount() > 0);
        }
    }

    private static class pDfsHelper extends RecursiveAction{
        int cur;
        Graph g;
        public pDfsHelper(int cur, Graph g) {
            this.cur = cur;
            this.g = g;
        }

        @Override
        protected void compute() {
            if (!semaphores[cur].tryAcquire(1)) return;
            System.out.println("Thread " + Thread.currentThread().getName() + ": " + cur);
            //List<RecursiveAction> tasks = new ArrayList<>();
            for (Edge e: g.adj[cur]) {
                RecursiveAction task = new pDfsHelper(e.getTo(), g);
                task.fork();
                task.join();
            }
        }

    }
}
