package daschnerj.gen.data.sounds;


public class SoundVector
{
	private float x,y;
	public SoundVector(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public SoundVector subtract(SoundVector subtract)
	{
		return new SoundVector(this.x - subtract.x, this.y - subtract.y);
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	@Override
	public String toString()
	{
		return "{SoundVector(" + x + "," + y + ")}";
	}
	
	public float getMagnitude()
	{
		return (float) Math.sqrt((double)(Math.pow(x, 2) + Math.pow(y, 2)));
	}
	
	public SoundVector normalize()
	{
		float magnitude = this.getMagnitude();
		if(magnitude == 0)
			return new SoundVector(0, 0);
		return new SoundVector(x/magnitude, y/magnitude);
	}
}