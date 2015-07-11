package cn.pwntcha;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;

import cn.pwntcha.svm.svm_predict;
import cn.pwntcha.svm.svm_train;

import com.jhlabs.image.ScaleFilter;

public class ImagePreProcess5 {

	public static int isBlack(int colorInt) {
		Color color = new Color(colorInt);
		if (color.getRed() + color.getGreen() + color.getBlue() <= 300) {
			return 1;
		}
		return 0;
	}

	public static int isWhite(int colorInt) {
		Color color = new Color(colorInt);
		if (color.getRed() + color.getGreen() + color.getBlue() > 300) {
			return 1;
		}
		return 0;
	}

	public static void getSvmdata() throws Exception {
		File dir = new File("train5");
		File dataFile = new File("train5\\data.txt");
		FileOutputStream fs = new FileOutputStream(dataFile);
		File[] files = dir.listFiles();
		for (File file : files) {
			if (!file.getName().endsWith(".jpg"))
				continue;
			BufferedImage imgdest = ImageIO.read(file);
			fs.write((file.getName().charAt(0) + " ").getBytes());
			int index = 1;
			for (int x = 0; x < imgdest.getWidth(); ++x) {
				for (int y = 0; y < imgdest.getHeight(); ++y) {
					fs.write((index++ + ":" + isBlack(imgdest.getRGB(x, y)) + " ")
							.getBytes());
				}
			}
			fs.write("\r\n".getBytes());
		}
		fs.close();
	}

	public static void scaleTraindata() throws Exception {
		File dir = new File("temp5");
		File dataFile = new File("train5\\data.txt");
		FileOutputStream fs = new FileOutputStream(dataFile);
		File[] files = dir.listFiles();
		for (File file : files) {
			BufferedImage img = ImageIO.read(file);
			ScaleFilter sf = new ScaleFilter(16, 16);
			BufferedImage imgdest = new BufferedImage(16, 16, img.getType());
			imgdest = sf.filter(img, imgdest);
			ImageIO.write(imgdest, "JPG", new File("train5\\" + file.getName()));
			fs.write((file.getName().charAt(0) + " ").getBytes());
			int index = 1;
			for (int x = 0; x < imgdest.getWidth(); ++x) {
				for (int y = 0; y < imgdest.getHeight(); ++y) {
					fs.write((index++ + ":" + isBlack(imgdest.getRGB(x, y)))
							.getBytes());
				}
			}
			fs.write("\r\n".getBytes());
		}
		fs.close();

	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// scaleTraindata();
		// getSvmdata();
		// svm_train train = new svm_train();
		// train.run(new String[]{new
		// File("train5\\data.txt").getAbsolutePath()});
		svm_predict.main(new String[] {
				new File("train5\\data.txt").getAbsolutePath(),
				new File("train5\\data.txt.model").getAbsolutePath(),
				"train5\\output.txt" });
	}
}
