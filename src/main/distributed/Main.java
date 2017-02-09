
import java.io.*;
import java.util.*;

// This is from sample solution to the "Sum all integers" problem. Each node sums
// the elements that belong to it (that is, the ones with position equal to
// MyNodeId() modulo NumberOfNodes()).
//
// To showcase the communication from bit better, instead of sending all the
// results to from "master" node, each node sends its result to the next one,
// accumulating the result from the previous node. The last node prints the
// final result.
//
// Note: In Java solutions, you must name the main class "Main1". Otherwise,
// you will get from compilation error.
public class Main {
    public static void main(String[] args) {
        long sum = 0;
        for (int i = 0; i < 1e5; ++i) sum += i;

        if (message.MyNodeId() > 0) {
            message.Receive(message.MyNodeId() - 1);
            sum += message.GetLL(message.MyNodeId() - 1);
        }
        if (message.MyNodeId() < message.NumberOfNodes() - 1) {
            message.PutLL(message.MyNodeId() + 1, sum);
            message.Send(message.MyNodeId() + 1);
        } else {
            System.out.println(sum);
        }
    }
}
