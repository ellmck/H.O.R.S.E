package com.ellmck.horse;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class AimSprite
{
	private final static int AIM_WIDTH = 6;

	private int startX;
	private int stopX;
	private int startY;
	private int stopY;
	private int lineColor = Color.rgb(0,191,255);
	private Paint linePaint = new Paint();

	protected AimSprite()
	{
		linePaint.setColor(lineColor);
		linePaint.setStrokeWidth(AIM_WIDTH);
//		lineColor.setPathEffect(new DashPathEffect(new float[]{2,4},50));
	}

	protected void draw(Canvas canvas)
	{
		canvas.drawLine(startX, startY, stopX, stopY, linePaint);
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

	public Paint getLinePaint()
	{
		return linePaint;
	}

	public void setLinePaint(int color)
	{
		linePaint.setColor(color);
	}

	public int getLineColor()
	{
		return lineColor;
	}

	public void setLineColor(int lineColor)
	{
		this.lineColor = lineColor;
	}
}
