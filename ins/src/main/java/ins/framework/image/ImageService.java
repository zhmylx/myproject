package ins.framework.image;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

public class ImageService {
	private static final double DEFAULT_ZOOM = 0.5D;
	private static final int DEFAULT_WIDTH = 80;
	private static final int DEFAULT_HEIGHT = 20;
	private static final int DEFAULT_FONT_SIZE = 18;
	private static final int DEFAULT_WORD_SIZE = 4;
	private static final int DEFAULT_LINE_SIZE = 5;
	private String sourceDir;
	private String destinationDir;
	private String mode;
	private String width;
	private String height;
	private String characterStorage;

	public void setCharacterStorage(String characterStorage) {
		this.characterStorage = characterStorage;
	}

	public void setDestinationDir(String destinationDir) {
		this.destinationDir = destinationDir;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public void setSourceDir(String sourceDir) {
		this.sourceDir = sourceDir;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public void createMicroImage(String fileName) {
		String destinationFileName = fileName;

		File sourceFile = new File(this.sourceDir, fileName);
		if (!sourceFile.exists()) {
			throw new IllegalArgumentException("file " + sourceFile
					+ " not exist.");
		}

		String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
		if ((!extension.equalsIgnoreCase("jpg"))
				&& (!extension.equalsIgnoreCase("bmp"))
				&& (!extension.equalsIgnoreCase("gif"))
				&& (!extension.equalsIgnoreCase("png"))) {
			throw new IllegalArgumentException("file type " + extension
					+ " not support.");
		}

		int destinationWidth = Integer.parseInt(this.width);
		int destinationHeight = Integer.parseInt(this.height);

		if ((!this.mode.equalsIgnoreCase("ScaleOnly"))
				&& (!this.mode.equalsIgnoreCase("ClipAndScale"))) {
			throw new IllegalArgumentException(
					"mode must be ScaleOnly or ClipAndScale");
		}

		BufferedImage image = null;
		try {
			image = ImageIO.read(sourceFile);
		} catch (IOException e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
		if (image == null) {
			throw new IllegalStateException("Read image error.");
		}

		int sourceWidth = image.getWidth();
		int sourceHeight = image.getHeight();
		BufferedImage destinationImage;
		if (this.mode.equalsIgnoreCase("ScaleOnly")) {

			if (sourceWidth / destinationWidth > sourceHeight
					/ destinationHeight) {
				Image tempImage = image
						.getScaledInstance(
								destinationWidth,
								(int) (destinationWidth * (sourceHeight / sourceWidth)),
								1);
				destinationImage = new BufferedImage(
						destinationWidth,
						(int) (destinationWidth * (sourceHeight / sourceWidth)),
						1);

				Graphics2D graphics = destinationImage.createGraphics();
				graphics.drawImage(tempImage, 0, 0, null);
			} else {
				Image tempImage = image
						.getScaledInstance(
								(int) (destinationHeight * (sourceWidth / sourceHeight)),
								destinationHeight, 1);

				destinationImage = new BufferedImage(
						(int) (destinationHeight * (sourceWidth / sourceHeight)),
						destinationHeight, 1);

				Graphics2D graphics = destinationImage.createGraphics();
				graphics.drawImage(tempImage, 0, 0, null);
			}

			if ((extension.equalsIgnoreCase("bmp"))
					|| (extension.equalsIgnoreCase("gif"))) {
				extension = "png";
				destinationFileName = destinationFileName.substring(0,
						destinationFileName.lastIndexOf('.'))
						+ " . "
						+ extension;
			}

			File destinationFile = new File(this.destinationDir,
					destinationFileName);
			try {
				ImageIO.write(destinationImage, extension, destinationFile);
			} catch (IOException e) {
				throw new IllegalStateException(e.getMessage(), e);
			}
		} else {
			if (sourceWidth / destinationWidth > sourceHeight
					/ destinationHeight) {
				int x = sourceWidth
						- (int) (sourceHeight * (destinationWidth / destinationHeight));
				Image clipedImage = image
						.getSubimage(
								(int) (0.5D * x),
								0,
								(int) (sourceHeight * (destinationWidth / destinationHeight)),
								sourceHeight);

				Image scaledImage = clipedImage.getScaledInstance(
						destinationWidth, destinationHeight, 1);

				destinationImage = new BufferedImage(destinationWidth,
						destinationHeight, 1);
				Graphics2D graphics = destinationImage.createGraphics();
				graphics.drawImage(scaledImage, 0, 0, null);
			} else {
				int y = sourceHeight
						- (int) (sourceWidth * (destinationHeight / destinationWidth));
				Image clipedImage = image
						.getSubimage(
								0,
								(int) (0.5D * y),
								sourceWidth,
								(int) (sourceWidth * (destinationHeight / destinationWidth)));

				Image scaledImage = clipedImage.getScaledInstance(
						destinationWidth, destinationHeight, 1);

				destinationImage = new BufferedImage(destinationWidth,
						destinationHeight, 1);
				Graphics2D graphics = destinationImage.createGraphics();
				graphics.drawImage(scaledImage, 0, 0, null);
			}

			if ((extension.equalsIgnoreCase("bmp"))
					|| (extension.equalsIgnoreCase("gif"))) {
				extension = "png";
				destinationFileName = destinationFileName.substring(0,
						destinationFileName.lastIndexOf('.')) + "." + extension;
			}

			File destinationFile = new File(this.destinationDir,
					destinationFileName);
			try {
				ImageIO.write(destinationImage, extension, destinationFile);
			} catch (IOException e) {
				throw new IllegalStateException(e.getMessage(), e);
			}
		}
	}

	public BufferedImage createValidateImage(HttpSession session) {
		BufferedImage validateImage = new BufferedImage(80, 20, 1);
		Graphics2D graphics = validateImage.createGraphics();

		int length = this.characterStorage.length();
		char[] chars = new char[4];
		Random rand = new Random();
		for (int i = 0; i < 4; ++i) {
			int index = rand.nextInt(length);
			chars[i] = this.characterStorage.charAt(index);
		}
		String str = new String(chars);

		session.setAttribute("validateString", str);

		graphics.setFont(new Font("宋体", 1, 18));
		graphics.drawString(str, 2, 2);

		for (int i = 0; i < 5; ++i) {
			int x1 = rand.nextInt(80);
			int y1 = rand.nextInt(20);
			int x2 = rand.nextInt(80);
			int y2 = rand.nextInt(20);
			graphics.drawLine(x1, y1, x2, y2);
		}
		return validateImage;
	}
}
