package com.ellmck.horse;


import android.graphics.Rect;

public class PhysicalObject
{
	private Rect bounds;

	public PhysicalObject (double x, double y, double width, double height)
	{
//		bounds.set((int)x, (int)(x + width - 1), (int)y, (int)(y + height - 1));
	}

	public Rect getBounds()
	{
		return bounds;
	}

	public void setBounds(Rect bounds)
	{
		this.bounds = bounds;
	}
}