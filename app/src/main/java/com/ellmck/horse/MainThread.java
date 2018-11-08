package com.ellmck.horse;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread
{
	private SurfaceHolder surfaceHolder;
	private GameView gameView;
	private boolean running;
	public static Canvas canvas = null;

	public MainThread(SurfaceHolder surfaceHolder, GameView gameView)
	{
		super();
		this.surfaceHolder = surfaceHolder;
		this.gameView = gameView;
	}
	public void setRunning(boolean run)
	{
		running = run;
	}

	@Override
	public void run()
	{
		while (running)
		{

			try
			{
				canvas = this.surfaceHolder.lockCanvas();
				synchronized (surfaceHolder)
				{
					this.gameView.update();
					this.gameView.draw(canvas);
				}
			} catch (Exception e) {

			} finally
			{
				if (canvas != null)
				{
					try
					{
						surfaceHolder.unlockCanvasAndPost(canvas);
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}

		}
	}
}


