package input.components.segment;

import input.components.point.PointNode;

/**
 * @author eleanorbadgett, sam nusstein, ellie johnson
 * A utility class only for representing ONE segment
 */
public class SegmentNode
{
	protected PointNode _point1;
	protected PointNode _point2;
	
	public PointNode getPoint1() { return _point1; }
	public PointNode getPoint2() { return _point2; }
	
	public SegmentNode(PointNode pt1, PointNode pt2)
	{
		_point1 = pt1;
		_point2 = pt2;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof SegmentNode)) return false;
		SegmentNode that = (SegmentNode) obj;
				
		if(this.getPoint1().equals(that.getPoint1()) &&
				this.getPoint2().equals(that.getPoint2())) return true;
		
		
		if(this.getPoint1().equals(that.getPoint2()) &&
				this.getPoint2().equals(that.getPoint1())) return true;

		return false;
	}
	
	@Override
	public String toString() 
	{
		StringBuilder str = new StringBuilder();
		str.append("Segment: " + getPoint1() + " , " + getPoint2());
		
		return str.toString();
	}
	
}