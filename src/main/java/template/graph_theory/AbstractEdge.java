package template.graph_theory;

/**
 * Created by dy on 17-1-20.
 */
public abstract class AbstractEdge {
    public long getLength() {throw new UnsupportedOperationException();}
    public <T> T getCost() {throw new UnsupportedOperationException();}
    public long getFlow() {throw new UnsupportedOperationException();}
    public long getCapacity() {throw new UnsupportedOperationException();}
    public long getResidual() {throw new UnsupportedOperationException();}
    public int getFrom() {throw new UnsupportedOperationException();}
    public int getTo() {throw new UnsupportedOperationException();}

    @Override
    public String toString() {
        return "AbstractEdge{}";
    }
}
