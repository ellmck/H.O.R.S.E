package com.ellmck.horse;

import android.graphics.*;

public class BallSprite
{
	private Bitmap image;
	private int x;
	private int y;
	private int width;
	private int height;
	private int radius;
	private int xVelocity, yVelocity;
	private boolean isMoving = false;
	private static final Paint ballPaint = new Paint();
	private static final int BALL_COLOR = Color.rgb(0, 255, 0);

	public BallSprite(Bitmap bmp)
	{
	image = Bitmap.createScaledBitmap(bmp, 100, 100, false);
	width = image.getWidth();
	height = image.getHeight();
	}

	public BallSprite()
	{
		x = 0;
		y= 0;
		radius = 50;
		width = radius * 2 ;
		ballPaint.setColor(BALL_COLOR);
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

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius)
	{
		this.radius = radius;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getxVelocity()
	{
		return xVelocity;
	}

	public void setxVelocity(int xVelocity)
	{
		this.xVelocity = xVelocity;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getyVelocity()
	{
		return yVelocity;
	}

	public void setyVelocity(int yVelocity)
	{
		this.yVelocity = yVelocity;
	}

	public boolean isMoving()
	{
		return isMoving;
	}

	public void setMoving(boolean isMoving)
	{
		this.isMoving = isMoving;
	}

	public void draw (Canvas canvas)
	{
		canvas.drawCircle(x, y, radius, ballPaint);
//		canvas.drawBitmap(image, x,  y, null);
	}

}
