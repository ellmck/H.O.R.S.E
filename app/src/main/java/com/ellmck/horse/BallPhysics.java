package com.ellmck.horse;

import android.content.res.Resources;

public class BallPhysics {

	private static final double ACCELERATION = 1.1;
	// What proportion of the velocity is retained on a bounce?  if 1.0, no energy
	// is lost, and the ball will bounce infinitely.
	private static final double COEFFICIENT_OF_RESTITUTION = 0.8;
	// While the ball is rolling along the bottom of the screen, its x velocity
	// is multiplied by this amount each frame.
	private static final double COEFFICIENT_OF_FRICTION = 0.9;
	// a = (v2-v1)/t
	// a*t = (v2-v1)
	// (a*t)+v1 = v2
	private BallSprite ball;

	protected BallPhysics(BallSprite ball)
	{
		this.ball = ball;
	}

	protected void ballAnimation(NetSprite net)
	{

		if (ball == null || !ball.isMoving())
		{
			return;
		}

		//landscape
		int screenWidth = Resources.getSystem().getDisplayMetrics().heightPixels;
		int screenHeight = Resources.getSystem().getDisplayMetrics().widthPixels;

		//this line determines direction of gravity
		ball.setxVelocity(-(ACCELERATION * 1) + ball.getxVelocity());
		ball.update();

		double radius = ball.getWidth() / 2;
		double maxY = screenWidth - radius;
		double maxX = screenHeight - radius;
		double minX = 0 + radius;

		// Ball is out of bounds in y dimension
		if (ball.getY() > maxY)
		{
			ball.setY(maxY);
			ball.setyVelocity(-COEFFICIENT_OF_RESTITUTION * ball.getyVelocity());
		}
		else if (ball.getY() < 0)
		{
			ball.setY(0);
			ball.setyVelocity(-COEFFICIENT_OF_RESTITUTION * ball.getyVelocity());
		}

		// Ball hits the floor
		if (ball.getX() < minX)
		{
			ball.setX(minX);
			ball.setxVelocity(-COEFFICIENT_OF_RESTITUTION * ball.getxVelocity());
		}
		else if (ball.getX() > maxX * 4)
		{
			ball.setX(maxX);
			ball.setxVelocity(-COEFFICIENT_OF_RESTITUTION * ball.getxVelocity());
		}

		//backboard physics
//		if (ball.getX() > net.getBackboard().top && ball.getY()> net.getBackboard().left)
//		{
//			ball.setX(net.getBackboard().top);
//			ball.setxVelocity(-COEFFICIENT_OF_RESTITUTION * ball.getxVelocity());
//			ball.setY(net.getBackboard().left);
//			ball.setyVelocity(-COEFFICIENT_OF_RESTITUTION * ball.getyVelocity());
//		}
//		else if (ball.getX() > net.getBackboard().right && ball.getY() > net.getBackboard().bottom)
//		{
//			ball.setX(net.getBackboard().right);
//			ball.setxVelocity(-COEFFICIENT_OF_RESTITUTION * ball.getxVelocity());
//			ball.setY(net.getBackboard().bottom);
//			ball.setyVelocity(-COEFFICIENT_OF_RESTITUTION * ball.getyVelocity());
//		}
// 		else if (ball.getX() - radius <  net.getBackboard().top)
// 		{
//			ball.setX(net.getBackboard().top);
//			ball.setxVelocity(-COEFFICIENT_OF_RESTITUTION * ball.getxVelocity());
//		}
//		if (ball.getY() + ball.getWidth() / 2 > net.getBackboard().right) {
//			ball.setY(net.getBackboard().right);
//			ball.setyVelocity(-COEFFICIENT_OF_RESTITUTION * ball.getyVelocity());
//		} else if (ball.getY() - ball.getWidth() / 2 <  net.getBackboard().left) {
//			ball.setY(net.getBackboard().left);
//			ball.setyVelocity(-COEFFICIENT_OF_RESTITUTION * ball.getyVelocity());
//		}

		// ball is rolling along the bottom
		if (ball.getX() == maxX)
		{
			ball.setyVelocity(COEFFICIENT_OF_FRICTION * ball.getyVelocity());
		}
	}
}
