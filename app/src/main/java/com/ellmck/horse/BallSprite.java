package com.ellmck.horse;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class BallSprite
{
	private static Bitmap image;
	private static double x;
	private static double y;
	private static double width;
	private static double height;
	private static double xVelocity, yVelocity;
	private boolean isMoving = false;

	protected BallSprite(Bitmap bmp)
	{
		image = Bitmap.createScaledBitmap(bmp, 100, 100, false);
		width = image.getWidth();
		height = image.getHeight();
	}

	protected void update()
	{
		this.x += xVelocity;
		this.y += yVelocity;
	}

	protected double getHeight()
	{
		return height;
	}

	protected void setHeight(int height)
	{
		this.height = height;
	}

	protected double getWidth() {
		return width;
	}

	protected void setWidth(int width)
	{
		this.width = width;
	}

	protected double getX()
	{
		return x;
	}

	protected void setX(double x)
	{
		this.x = x;
	}

	protected double getxVelocity()
	{
		return xVelocity;
	}

	protected void setxVelocity(double xVelocity)
	{
		this.xVelocity = xVelocity;
	}

	protected double getY() {
		return y;
	}

	protected void setY(double y) {
		this.y = y;
	}

	protected double getyVelocity()
	{
		return yVelocity;
	}

	protected void setyVelocity(double yVelocity)
	{
		this.yVelocity = yVelocity;
	}

	protected boolean isMoving()
	{
		return isMoving;
	}

	protected void setMoving(boolean isMoving)
	{
		this.isMoving = isMoving;
	}

	protected void draw (Canvas canvas)
	{
		canvas.drawBitmap(image, (int)x, (int) y, null);
	}

}
