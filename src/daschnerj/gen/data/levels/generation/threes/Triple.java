package daschnerj.gen.data.levels.generation.threes;

public class Triple {
	  private double x, y, z;
	  public Triple (double x, double y, double z) {
	    this.x = x;
	    this.y = y;
	    this.z = z;
	  }
	  public Triple add (Triple t) {
	    return new Triple (x + t.x, y + t.y, z + t.z);
	  }
	  public Triple subtract (Triple t) {
	    return new Triple (x - t.x, y - t.y, z - t.z);
	  }
	  public Triple cross (Triple t) {
	    return new Triple (y * t.z - z * t.y, z * t.x - x * t.z,
	      x * t.y - y * t.x);
	  }
	  public double dot (Triple t) {
	    return x * t.x + y * t.y + z * t.z;
	  }
	  public double length2 () {
	    return dot (this);
	  }
	  public Triple normalize () {
	    return scale (1.0 / Math.sqrt (length2 ()));
	  }
	  public Triple scale (double scale) {
	    return new Triple (x * scale, y * scale, z * scale);
	  }
	}
