package chnp.manager.common;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class VerificationCode {
	private Integer width = 90;// 验证码图片宽度

	private Integer height = 20;// 验证码图片高度

	private Integer fontHeight = 18;// 验证码字体高度：默认为18

	private Integer codeNum = 4;// 验证码数量：默认为4

	private char[] codeSequence = {
			'A', 'B', 'C', 'D', 'E', 'F', 'G',
			'H', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
			'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'i', 'j', 'k', 'm', 'n', 'o',
			'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getFontHeight() {
		return fontHeight;
	}

	public void setFontHeight(Integer fontHeight) {
		this.fontHeight = fontHeight;
	}

	public Integer getCodeNum() {
		return codeNum;
	}

	public void setCodeNum(Integer codeNum) {
		this.codeNum = codeNum;
	}

	public char[] getCodeSequence() {
		return codeSequence;
	}

	private String codeString = "";// 验证码

	private BufferedImage bufferedImage;// 验证码图片

	public String getCodeString() {
		return codeString;
	}

	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public void generate() {
		// 定义图像buffer
		bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Graphics gd = bufferedImage.getGraphics();// 获取图像的画笔
		gd.setColor(Color.WHITE);// 设置画笔颜色：白色
		gd.fillRect(0, 0, width, height);// 画一个矩形面
		gd.setColor(Color.BLACK);// 设置画笔颜色：黑色
		gd.drawRect(0, 0, width - 1, height - 1);// 画一个矩形框

		Random random = new Random();
		// 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。
		gd.setColor(Color.BLACK);
		for (int i = 0; i < 40; i++) {
			int start_x = random.nextInt(width);
			int start_y = random.nextInt(height);
			int offset_x = random.nextInt(12);
			int offset_y = random.nextInt(12);
			gd.drawLine(start_x, start_y, start_x + offset_x, start_y + offset_y);
		}

		// codes用于保存随机产生的验证码，以便用户登录后进行验证。
		gd.setFont(new Font("Fixedsys", Font.BOLD, fontHeight));// 设置画笔字体属性
		for (int i = 0; i < codeNum; i++) {
			String code = String.valueOf(codeSequence[random.nextInt(codeSequence.length-1)]);
			gd.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
			gd.drawString(code, (i + 1) * 15, (height - fontHeight) / 2 + fontHeight);
			this.codeString += code;
		}
	}

}