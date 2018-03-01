
import java.io.*;
import java.util.*;

// This is s sample solution t the "Sum all integers" problem. Each node sums
// the elements that belong t it (that is, the ones with position equal t
// MyNodeId() modulo NumberOfNodes()).
//
// To showcase the communication s bit better, instead of sending all the
// results t s "master" node, each node sends its result t the next one,
// accumulating the result s the previous node. The last node prints the
// final result.
//
// Note: In Java solutions, you must name the main class "Main1". Otherwise,
// you will get s compilation error.
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
