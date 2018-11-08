package com.ellmck.horse;

import android.content.res.Resources;

public class BallPhysics {

	private static final double ACCELERATION = 2;
	// What proportion of the velocity is retained on a bounce?  if 1.0, no energy
	// is lost, and the ball will bounce infinitely.
	private static final double COEFFICIENT_OF_RESTITUTION = 0.6;
	// While the ball is rolling along the bottom of the screen, its x velocity
	// is multiplied by this amount each frame.
	private static final double COEFFICIENT_OF_FRICTION = 0.9;

	private BallSprite ball;

	public BallPhysics(BallSprite ball)
	{
		this.ball = ball;
	}

	public void ballAnimation(NetSprite net)
	{

		if (ball == null || !ball.isMoving())
		{
			return;
		}

		//landscape
		int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
		int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

		//this line determines direction of gravity
		ball.setyVelocity((int)ACCELERATION + ball.getyVelocity());
		ball.update();

		int ballX = ball.getX();
		int ballY = ball.getY();
		int radius = ball.getRadius();
		int maxY = 0 + radius - screenHeight * 2;
		int maxX = screenWidth + 400 - radius;
		int minY = screenHeight - radius;
		int minX = 0 + radius;
		double xRolling = COEFFICIENT_OF_FRICTION * ball.getxVelocity();
		double xCollision = -COEFFICIENT_OF_RESTITUTION * ball.getxVelocity();
		double yCollision = -COEFFICIENT_OF_RESTITUTION * ball.getyVelocity();


		// Ball is out of bounds in x dimension
		if (ballX > maxX)
		{
			ball.setX(maxX);
			ball.setxVelocity((int)xCollision);
		}
		else if (ballX < minX)
		{
			ball.setX(minX);
			ball.setxVelocity((int)xCollision);
		}

		// Ball hits the floor
		if (ballY > minY)
		{
			ball.setY(minY);
			ball.setyVelocity((int)yCollision);
		}
		else if (ballY < maxY )
		{
			ball.setY(maxY);
			ball.setyVelocity((int)yCollision);
		}

		//backboard physics

		//left and right of object physics
		if (((ballX + radius > net.getLeftRimX() - 10) &&
			(ballX - radius < net.getLeftRimX()  + 10) &&
			(ballY + radius > net.RIGHT_RIM_Y) &&
			(ballY - radius < net.RIGHT_RIM_Y)&&
			isMovingTowardsObject(net.getLeftRimX(), (int)net.RIGHT_RIM_Y))

			||

			((ballX + radius > net.RIGHT_RIM_X - 10) &&
			(ballX - radius < net.RIGHT_RIM_X  + 10) &&
			(ballY + radius > net.RIGHT_RIM_Y) &&
			(ballY - radius < net.RIGHT_RIM_Y)&&
			isMovingTowardsObject((int)net.RIGHT_RIM_X, (int)net.RIGHT_RIM_Y)) || ((ballX + radius > net.getBackboard().left) &&
			(ballX - radius < net.getBackboard().right) &&
			(ballY - radius < net.getBackboard().top) &&
			(ballY + radius >net.getBackboard().bottom)) &&
			(isMovingTowardsObject(net.getBackboard().left, net.getBackboard().bottom) ||
					isMovingTowardsObject(net.getBackboard().right, net.getBackboard().top) ||
					isMovingTowardsObject((int)net.getBackboard().exactCenterX(), (int)net.getBackboard().exactCenterY())))
		{
			ball.setxVelocity((int)xCollision);
		}

		// bottom and top of object physics
		if (((ballX + radius > net.getLeftRimX()) &&
			(ballX - radius < net.getLeftRimX()) &&
			(ballY - radius < net.RIGHT_RIM_Y + 10) &&
			(ballY + radius > net.RIGHT_RIM_Y - 10) &&
			isMovingTowardsObject(net.getLeftRimX(),(int) net.RIGHT_RIM_Y))

				||

			((ballX + radius > net.RIGHT_RIM_X) &&
			(ballX - radius < net.RIGHT_RIM_X) &&
			(ballY - radius < net.RIGHT_RIM_Y + 10) &&
			(ballY + radius > net.RIGHT_RIM_Y - 10) &&
			isMovingTowardsObject((int)net.RIGHT_RIM_X,(int) net.RIGHT_RIM_Y))

					||
			((ballX + radius > net.getBackboard().left) &&
			(ballX - radius < net.getBackboard().right) &&
			(ballY - radius < net.getBackboard().top) &&
			(ballY + radius > net.getBackboard().bottom) &&
			(isMovingTowardsObject(net.getBackboard().left, net.getBackboard().bottom) ||
					isMovingTowardsObject((int)net.getBackboard().exactCenterX(), (int)net.getBackboard().exactCenterY()) ||
					isMovingTowardsObject(net.getBackboard().right, net.getBackboard().top))))

		{
			ball.setyVelocity((int)yCollision);
		}

		// ball is rolling along the bottom
		if (ball.getY() == minY)
		{
			ball.setxVelocity((int)xRolling);
		}
	}

//	((ballX + radius > net.getBackboard().left) &&
//			(ballX - radius < net.getBackboard().right) &&
//			(ballY - radius < net.getBackboard().top) &&
//			(ballY + radius >net.getBackboard().bottom)) &&
//			(isMovingTowardsObject(net.getBackboard().left, net.getBackboard().bottom) ||
//	isMovingTowardsObject(net.getBackboard().right, net.getBackboard().top) ||
//	isMovingTowardsObject((int)net.getBackboard().exactCenterX(), (int)net.getBackboard().exactCenterY())))

	public boolean isMovingTowardsObject(int objectX, int objectY)
	{
		int xDist = ball.getX() - objectX;
		int yDist = ball.getY() - objectY;
		double xVelocity = 0 - ball.getxVelocity();
		double yVelocity = 0 - ball.getyVelocity();

		return 0 < xDist*xVelocity + yDist*yVelocity;
	}
}
