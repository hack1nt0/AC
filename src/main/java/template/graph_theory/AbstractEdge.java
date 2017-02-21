package template.graph_theory;

/**
 * Created by dy on 17-1-20.
 */
public abstract class AbstractEdge {
    private boolean visited = false;
    public int getId() {throw new UnsupportedOperationException();}
    public void setId(int id) {throw new UnsupportedOperationException();}

    public long getLength() {throw new UnsupportedOperationException();}
    public int getCost() {throw new UnsupportedOperationException();}
    public void setCost(int cost) {throw new UnsupportedOperationException();}
    public int getFlow() {throw new UnsupportedOperationException();}
    public void setFlow(int flow) {throw new UnsupportedOperationException();}
    public int getCapacity() {throw new UnsupportedOperationException();}
    public void setCapacity(int capacity) {throw new UnsupportedOperationException();}
    public AbstractEdge getReversalEdge() {throw new UnsupportedOperationException();}
    public void setReversalEdge(AbstractEdge reversalEdge) {throw new UnsupportedOperationException();}
    public int getResidual() {throw new UnsupportedOperationException();}
    public int getFrom() {throw new UnsupportedOperationException();}
    public void setFrom(int from) {throw new UnsupportedOperationException();}
    public int getTo() {throw new UnsupportedOperationException();}
    public void setTo(int to) {throw new UnsupportedOperationException();}

    public boolean getVisited() {return visited;}
    public void setVisited() {visited = true;}
    public void notVisited() {visited = false;}


    public int other(int v) {
        if (v == getFrom()) return getTo();
        if (v == getTo()) return getFrom();
        throw new IllegalArgumentException();
    }
    @Override
    public String toString() {
        return null;
    }
}
