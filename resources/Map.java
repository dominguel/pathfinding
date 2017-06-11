package resources;

import java.util.List;

public class Map {

	private final List<Node> nodes;
    private final List<Segment> segments;

    public Map(List<Node> nodes, List<Segment> segments) {
            this.nodes = nodes;
            this.segments = segments;
    }

    public List<Node> getNodes() {
            return nodes;
    }

    public List<Segment> getSegments() {
            return segments;
    }

}
