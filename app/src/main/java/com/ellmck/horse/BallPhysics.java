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

	public BallPhysics(BallSprite ball)
	{
		this.ball = ball;
	}

	public void ballAnimation() {

		if (ball == null)
		{
			return;
		}

		//landscape
		int screenWidth = Resources.getSystem().getDisplayMetrics().heightPixels;
		int screenHeight = Resources.getSystem().getDisplayMetrics().widthPixels;

		//this line determines direction of gravity
		ball.setxVelocity(-(ACCELERATION * 1) + ball.getxVelocity());
		ball.update();

		int maxY = screenWidth - (ball.getHeight() / 2);
		int maxX = screenHeight - (ball.getWidth() / 2);
		int minX = 0 + ball.getWidth() / 2;

		// Ball is out of bounds in y dimension
		if (ball.getY() > maxY) {
			ball.setY(maxY);
			ball.setyVelocity(-COEFFICIENT_OF_RESTITUTION * ball.getyVelocity());
		} else if (ball.getY() < 0) {
			ball.setY(0);
			ball.setyVelocity(-COEFFICIENT_OF_RESTITUTION * ball.getyVelocity());
		}


		// Ball is out of bounds in x dimension
		if (ball.getX() > maxX) {
			ball.setX(maxX);
			ball.setxVelocity(-COEFFICIENT_OF_RESTITUTION * ball.getxVelocity());
		} else if (ball.getX() < minX) {
			ball.setX(minX);
			ball.setxVelocity(-COEFFICIENT_OF_RESTITUTION * ball.getxVelocity());
		}

		// ball is rolling along the bottom
		if (ball.getY() == maxY) {
			ball.setxVelocity(COEFFICIENT_OF_FRICTION * ball.getxVelocity());
		}
	}
}
