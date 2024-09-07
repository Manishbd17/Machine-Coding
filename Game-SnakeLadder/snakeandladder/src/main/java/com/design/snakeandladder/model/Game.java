package com.design.snakeandladder.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.apache.commons.lang3.RandomUtils;

public class Game {
	private int numberOfSnakes; 
	private int numberOfLadders; 
	
	private Queue<Player> players;
	private List<Snake> snakes;
	private List<Ladder> ladders; 
	
	private Board board;
	private Dice dice; 
	
	public Game(int numberOfSnakes,int numberOfLadders,int boardSize) {
		this.numberOfSnakes = numberOfSnakes; 
		this.numberOfLadders = numberOfLadders; 
		this.players = new ArrayDeque<>(); 
		this.snakes = new ArrayList<>(numberOfSnakes); 
		this.ladders = new ArrayList<>(numberOfLadders); 
		this.board = new Board(boardSize); 
		this.dice = new Dice(1,6,2); 
		initBoard(); 
	}
	
	private void initBoard() {
		Set<String> slSet = new HashSet<>(); 
		for(int i=0;i<numberOfSnakes;i++) {
			while(true) {
				int snakeStart = RandomUtils.nextInt(board.getStart(),board.getSize());
				int snakeEnd = RandomUtils.nextInt(board.getStart(),board.getSize()); 
				if(snakeStart<=snakeEnd) {
					continue;
				}
				String startEndpair = String.valueOf(snakeStart) + snakeEnd; 
				if(!slSet.contains(startEndpair)) {
					Snake snake = new Snake(snakeStart,snakeEnd); 
					snakes.add(snake); 
					slSet.add(startEndpair); 
					break; 
				}
			}
		}
		for(int i=0;i<numberOfLadders;i++) {
			while(true) {
				int ladderStart = RandomUtils.nextInt(board.getStart(),board.getSize()); 
				int ladderEnd = RandomUtils.nextInt(board.getStart(),board.getSize()); 
				if(ladderStart>=ladderEnd) {
					continue; 
				}
				String startEndpair = String.valueOf(ladderStart) + ladderEnd; 
				if(!slSet.contains(startEndpair)) {
					Ladder ladder = new Ladder(ladderStart,ladderEnd); 
					ladders.add(ladder); 
					slSet.add(startEndpair); 
					break; 
				}
			}
		}
	}
	
	public void addPlayer(Player player) {
		players.add(player); 
	}
	
	public void playGame() {
		while(true) {
			Player player = players.poll(); 
			int value = dice.roll(); 
			int newPos = player.getPosition() + value; 
			if(newPos>board.getEnd()) {
				player.setPosition(player.getPosition());
				players.offer(player); 
			} else {
				player.setPosition(getNewPosition(newPos)); 
				if(player.getPosition()==board.getEnd()) {
					player.setWon(true);
					System.out.println("Player " + player.getName() + " Won.");
				} else {
					System.out.println("Setting " + player.getName() + "'s new position to " + player.getPosition());
					players.offer(player); 
				}
			}
			if(players.size()<2) {
				break;
			}
		}
	}

	private int getNewPosition(int newPos) {
		for(Snake snake: snakes) {
			if(snake.getHead()==newPos) {
				System.out.println("Snake bit");
				return snake.getTail();
			}
		}
		for(Ladder ladder: ladders) {
			if(ladder.getStart()==newPos) {
				System.out.println("Climb Ladder"); 
				return ladder.getEnd(); 
			}
		}
		return newPos; 
	}
	
	//Game
	//initboard
	//addPlayer
	//playgame
	//getNextPos
}
