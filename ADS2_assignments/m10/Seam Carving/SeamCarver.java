import java.awt.Color;
import java.util.ArrayList;
public class SeamCarver {
	// create a seam carver object based on the given picture
	private Picture picture;
	private double[][] energy;
	public SeamCarver(Picture pic) {
		if (pic == null) {
			throw new IllegalArgumentException("picture is null");
		}
		this.picture = pic;
		energy = new double[pic.width()][pic.height()];
		for (int col = 0; col < width(); col++) {
			for (int row = 0; row < height(); row++) {
				energy[col][row] = energy(col, row);
			}
		}
	}
	// current picture
	public Picture picture() {
		return this.picture;
	}
	// width of current picture
	public int width() {
		return energy.length;
	}

	// height of current picture
	public int height() {
		return energy[0].length;
	}
	public void relaxEdge(int fromPixel, int toPixel, double[] distTo, int[] edgeTo) {

		if (distTo[fromPixel] + energy[toPixel % width()][toPixel / width()] <
		        distTo[toPixel]) {
			distTo[toPixel] = distTo[fromPixel] +
			                  energy[toPixel % width()][toPixel / width()];
			edgeTo[toPixel] = fromPixel;
		}
	}
	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		double dx = 0.0;
		double dy = 0.0;
		Color front;
		Color back;
		if (x < 0 || x >= width() || y < 0 || y >= height()) {
			throw new IllegalArgumentException("picture is null");
		}
		if (x == 0 || y == 0 || x == width() - 1 || y == height() - 1) {
			return 1000.0;
		} else {
			front = picture.get(x - 1, y);
			back = picture.get(x + 1, y);
			dx = Math.pow((front.getRed() - back.getRed()), 2) + Math.pow((front.getGreen() - back.getGreen()), 2) + Math.pow((front.getBlue() - back.getBlue()), 2);
			front = picture.get(x, y - 1);
			back = picture.get(x, y + 1);
			dy = Math.pow((front.getRed() - back.getRed()), 2) + Math.pow((front.getGreen() - back.getGreen()), 2) + Math.pow((front.getBlue() - back.getBlue()), 2);
			return  Math.sqrt((dx + dy));
		}
	}

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		double[] distTo = new double[width() * height()];
		int[] edgeTo = new int[width() * height()];
		for (int col = 0; col < width(); col++) {
			for (int row = 0; row < height(); row++) {
				if (col == 0)
					distTo[width() * row + col] = 0;
				else
					distTo[width() * row + col] = Double.POSITIVE_INFINITY;
				edgeTo[width() * row + col] = -1;
			}
		}
		for (int col = 0; col < width() - 1; col++) {
			for (int row = 0; row < height(); row++) {

				// relax right-upper edge if it exists
				if (row - 1 >= 0)
					relaxEdge(width() * row + col, width() * (row - 1) + (col + 1), distTo, edgeTo);
				// relax right-middle edge
				relaxEdge(width() * row + col, width() * row + (col + 1), distTo, edgeTo);
				// relax right-bottom edge if it exists
				if (row + 1 <= height() - 1)
					relaxEdge(width() * row + col, width() * (row + 1) + (col + 1), distTo, edgeTo);
			}
		}
		double curMinDist = Double.POSITIVE_INFINITY;
		int lastSeamPixel = -1;
		for (int row = 0; row < height(); row++) {
			if (distTo[(width() * row) + (width() - 1)] < curMinDist) {
				curMinDist = distTo[(width() * row) + (width() - 1)];
				lastSeamPixel = (width() * row) + (width() -1);//------------------->
			}
		}
		int[] horSeam = new int[width()];
		int curPixel = lastSeamPixel;
		// int temp = lastSeamPixel;// my talent

		int i = horSeam.length - 1;
		// while not beginning of the seam. LeftCol = -1
		// horSeam[i] = ((curPixel) % (width()));
		// 	curPixel = edgeTo[curPixel];
		// int j = horSeam.length - 1;


		while (curPixel != -1) {
			// System.out.println(curPixel+" ====== divide");
			horSeam[i] = ((curPixel) / (width()));
			// System.out.println(width()+"-----wid");
			// System.out.println(horSeam[i]+"------------------");
			curPixel = edgeTo[curPixel];
			// System.out.println(curPixel+" ------- ");
			i--;
		}
		// for (int k = horSeam.length - 1; k > 0; k--) {
		// 	if(temp == -1){
		// 		break;
		// 	}
		// 	horSeam[k] = ((temp) / (width()));
		// 	temp = edgeTo[temp];

			
		// }
		if(horSeam[0] != 0)
		{
		horSeam[0] += 1;
			
		}

		return horSeam;
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		// transpose image
		double[][] energycopy = energy;
		transposeEnergy();
		// find horizontal seam
		int[] verSeam  = findHorizontalSeam();
		// transpose back
		energy = energycopy;
		return verSeam;

	}
	public void transposeEnergy() {
		double[][] transposedEnergy = new double[height()][width()];
		for (int col = 0; col < width(); col++) {
			for (int row = 0; row < height(); row++) {
				transposedEnergy[row][col] = energy[col][row];
			}
		}
		energy = transposedEnergy;
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
}