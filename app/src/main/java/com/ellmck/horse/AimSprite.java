package com.ellmck.horse;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class AimSprite
{
	private final static int AIM_WIDTH = 10;

	private int startX;
	private int stopX;
	private int startY;
	private int stopY;
	private Paint lineColor = new Paint();

	protected AimSprite()
	{
		lineColor.setColor(Color.WHITE);
		lineColor.setStrokeWidth(AIM_WIDTH);
	}

	protected void draw(Canvas canvas)
	{
		canvas.drawLine(startX, startY, stopX, stopY, lineColor);
	}

	public int getStartX()
	{
		return startX;
	}

	public void setStartX(int startX)
	{
		this.startX = startX;
	}

	public int getStopX()
	{
		return stopX;
	}

	public void setStopX(int stopX)
	{
		this.stopX = stopX;
	}

	public int getStartY()
	{
		return startY;
	}

	public void setStartY(int startY)
	{
		this.startY = startY;
	}

	public int getStopY()
	{
		return stopY;
	}

	public void setStopY(int stopY)
	{
		this.stopY = stopY;
	}

	public Paint getLineColor()
	{
		return lineColor;
	}

	public void setLineColor(int color)
	{
		lineColor.setColor(color);
	}
}
