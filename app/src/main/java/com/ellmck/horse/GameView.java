package com.ellmck.horse;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback
{
	private MainThread thread;
	private BallSprite ball;
	private NetSprite net;
	private BallPhysics ballPhysics;
	private AimSprite aimSprite;

	public GameView(Context context)
	{
		super(context);

		getHolder().addCallback(this);

		thread = new MainThread(getHolder(), this);
		setFocusable(true);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
	{

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder)
	{
		ball = new BallSprite(BitmapFactory.decodeResource(getResources(), R.drawable.basketball));
		ballPhysics = new BallPhysics(ball);
		net = new NetSprite(ball.getWidth() * 1.885);
		aimSprite = new AimSprite();
		thread.setRunning(true);
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder)
	{
		boolean retry = true;
		while (retry)
		{
			try
			{
				thread.setRunning(false);
				thread.join();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			retry = false;
		}
		thread.setRunning(false);

	}

	@Override
	public boolean onTouchEvent(MotionEvent e)
	{
		int action = e.getAction();
		int x = (int) e.getX();
		int y = (int) e.getY();
		switch(action){
			case MotionEvent.ACTION_DOWN:
				//todo for reset button
//				if (x >= xOfYourBitmap && x < (xOfYourBitmap + yourBitmap.getWidth())
//						&& y >= yOfYourBitmap && y < (yOfYourBitmap + yourBitmap.getHeight())) {
//					//tada, if this is true, you've started your click inside your bitmap
//				}
				ball.setMoving(false);
				ball.setX(x - ball.getWidth()*0.5);
				ball.setY(y - ball.getWidth()*0.5);
				aimSprite.setStartX(x);
				aimSprite.setStartY(y);
				break;
			case MotionEvent.ACTION_MOVE:
				aimSprite.setStopX(x);
				aimSprite.setStopY(y);
				aimSprite.setLineColor(Color.WHITE);
				break;
			case MotionEvent.ACTION_UP:
				double xDiff = -(x - ball.getX())*0.2;
				double yDiff = -(y - ball.getY())*0.2;
				ball.setxVelocity(xDiff);
				ball.setyVelocity(yDiff);
				aimSprite.setLineColor(0);

				//only moves if velocity is high enough
				if (xDiff < -10 || xDiff > 10 || yDiff < -10 || yDiff > 10 )
				{
					ball.setMoving(true);
				}
				break;

			default:

				break;
		}
		// MotionEvent reports input details from the touch screen
		// and other input controls. In this case, you are only
		// interested in events where the touch position changed.
//		if (ball != null && e.getAction() == MotionEvent.ACTION_DOWN)
//		{
//			System.out.println("DOWN");
//			ball.setMoving(false);
//			ball.setX((int)e.getX());
//			ball.setY((int)e.getY());
//			return true;
//		}
//		else
//		{
//			ball.setMoving(true);
//			return false;
//		}
		return true;
	}


	public void update()
	{
		ballPhysics.ballAnimation();
	}

	@Override
	public void draw (Canvas canvas)
	{
		if (canvas == null)
		{
			return;
		}
		super.draw(canvas);
		ball.draw(canvas);
		net.draw(canvas);
		aimSprite.draw(canvas);
	}
}
