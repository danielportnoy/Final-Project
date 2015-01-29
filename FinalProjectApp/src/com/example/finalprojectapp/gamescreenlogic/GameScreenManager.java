package com.example.finalprojectapp.gamescreenlogic;

public class GameScreenManager {

	private Scenario scenario;

	private static GameScreenManager instance = new GameScreenManager();

	private GameScreenManager() {}

	public static GameScreenManager getInstance() {
		return instance;
	}

	public Scenario getScenario() {
		return scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

	public static void reset() {
		instance = new GameScreenManager();
	}

}