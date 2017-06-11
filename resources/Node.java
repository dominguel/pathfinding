package resources;
public class Node {

	final private String id;
	final private String name;
	final public int x;
	final public int y;
	
	public Node(String id, String name, int x, int y) {
		this.id = id;
		this.name = name;
		this.x = x;
		this.y = y;
	}

	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	@Override
    public boolean equals(Object obj) {
            if (this == obj)
                    return true;
            if (obj == null)
                    return false;
            if (getClass() != obj.getClass())
                    return false;
            Node other = (Node) obj;
            if (id == null) {
                    if (other.id != null)
                            return false;
            } else if (!id.equals(other.id))
                    return false;
            return true;
    }

    @Override
    public String toString() {
            return name;
    }
}