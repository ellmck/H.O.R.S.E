package com.ellmck.horse;

public class Player {

	private String name;
	private static boolean scored;
	private static int startingX, startingY;

	public Player(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public boolean isScored()
	{
		return scored;
	}

	public void setScored(boolean scored)
	{
		this.scored = scored;
	}

	public int getStartingX() {
		return startingX;
	}

	public void setStartingX(int startingX) {
		this.startingX = startingX;
	}

	public int getStartingY() {
		return startingY;
	}

	public void setStartingY(int startingY) {
		this.startingY = startingY;
	}
}
