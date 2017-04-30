package daschnerj.gen.data.levels.generation.threes;

public class TranscendentalTerrain implements Terrain {
	private double alpha, beta;

	/**
	 * Generates a height map from cosine and sine functions. 
	 * @param alpha Alpha frequency of the terrain.
	 * @param beta Beta frequency of the terrain.
	 */
	public TranscendentalTerrain(double alpha, double beta) {
		this.alpha = alpha;
		this.beta = beta;
	}

	public double getAltitude(double i, double j) {
		return .5 + .5 * Math.sin(i * alpha) * Math.cos(j * beta);
	}

	public RGB getColor(double i, double j) {
		return new RGB(.5 + .5 * Math.sin(i * alpha), .5 - .5 * Math.cos(j * beta), 0.0);
	}
}