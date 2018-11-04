package com.ellmck.horse;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class BallSprite
{
	private static Bitmap image;
	private static int x, y, width, height;
	private static double xVelocity, yVelocity;

	public BallSprite(Bitmap bmp)
	{
		image = bmp;
	}

	public void update()
	{
		this.x += xVelocity;
		this.y += yVelocity;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public double getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public double getxVelocity()
	{
		return xVelocity;
	}

	public void setxVelocity(double xVelocity)
	{
		this.xVelocity = xVelocity;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getyVelocity()
	{
		return yVelocity;
	}

	public void setyVelocity(double yVelocity)
	{
		this.yVelocity = yVelocity;
	}

	public void draw (Canvas canvas)
	{
		canvas.drawBitmap(image, x, y, null);
	}

}
