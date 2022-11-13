package actioncontrol.Queries;

public class SpecialListNode<K, Integer> {
    private final K node;
    private final Integer valueToIt;

    public K getNode() {
        return this.node;
    }

    public Integer getValueToIt() {
        return this.valueToIt;
    }

    public SpecialListNode(K node, Integer valueToIt) {
        this.node = node;
        this.valueToIt = valueToIt;
    }




}
