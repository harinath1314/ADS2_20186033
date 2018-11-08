import java.awt.Color;
public class SeamCarver {
	Picture pic;
	int wid;
	int hgt;
	// create a seam carver object based on the given picture
	public SeamCarver(Picture picture) {
		this.pic  = picture;
		if (picture == null) { 
			throw new IllegalArgumentException("picture is null");
			
		}
		this.wid = picture.width();
		this.hgt = picture.height();


	}
	// current picture
	public Picture picture() {
		return pic;
	}
	// width of current picture
	public int width() {
		return wid;
	}

	// height of current picture
	public int height() {
		return hgt;
	}

	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		double energy = 0.0;
		double dx = 0.0;
		double dy = 0.0;
		Color front;
		Color back;
		Color up;
		Color down;

		if (x == 0 || y == 0 || x == wid - 1 || y == hgt - 1) {
			energy = 1000.0;
			return energy;
		}
		front = pic.get(x-1, y);
		back = pic.get(x+1, y);
		up = pic.get(x, y-1);
		down = pic.get(x, y+1);
		dx = Math.pow((front.getRed() - back.getRed()),2) +Math.pow((front.getGreen() - back.getGreen()),2)+Math.pow((front.getBlue() - back.getBlue()),2);
		dy = Math.pow((up.getRed() - down.getRed()),2) +Math.pow((up.getGreen() - down.getGreen()),2)+Math.pow((up.getBlue() - down.getBlue()),2);
		energy = Math.sqrt(dx + dy);
		return energy;




	}

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		return new int[0];
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		return new int[0];
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
}