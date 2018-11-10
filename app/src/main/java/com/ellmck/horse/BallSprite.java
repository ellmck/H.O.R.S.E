package com.ellmck.horse;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

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
	private static final int BALL_COLOR = Color.rgb(105,105,105);

	protected BallSprite(Bitmap bmp)
	{
		x = 0;
		y= 0;
		radius = 50;
		width = radius * 2 ;
		ballPaint.setColor(BALL_COLOR);
		image = Bitmap.createScaledBitmap(bmp, width, width, false);
	}
	protected void update()
	{
		this.x += xVelocity;
		this.y += yVelocity;
	}

	protected int getHeight()
	{
		return height;
	}

	protected void setHeight(int height)
	{
		this.height = height;
	}

	protected int getRadius() {
		return radius;
	}

	protected void setRadius(int radius)
	{
		this.radius = radius;
	}

	protected int getWidth() {
		return width;
	}

	protected void setWidth(int width)
	{
		this.width = width;
	}

	protected int getX()
	{
		return x;
	}

	protected void setX(int x)
	{
		this.x = x;
	}

	protected int getxVelocity()
	{
		return xVelocity;
	}

	protected void setxVelocity(int xVelocity)
	{
		this.xVelocity = xVelocity;
	}

	protected int getY() {
		return y;
	}

	protected void setY(int y) {
		this.y = y;
	}

	protected int getyVelocity()
	{
		return yVelocity;
	}

	protected void setyVelocity(int yVelocity)
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
//		canvas.drawCircle(x, y, radius, ballPaint);
		canvas.drawBitmap(image, x - radius,  y - radius, null);
	}

}
