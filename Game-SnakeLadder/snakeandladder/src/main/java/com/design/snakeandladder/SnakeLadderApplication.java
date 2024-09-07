package com.design.snakeandladder;

import java.util.Scanner;

import com.design.snakeandladder.model.Game;
import com.design.snakeandladder.model.Player;

public class SnakeLadderApplication 
{
    public static void main( String[] args )
    {
        Scanner sc=new Scanner(System.in); 
        System.out.println("Enter Board Size"); 
        int boardSize = sc.nextInt(); 
        System.out.println("Enter no. of Players");
        int numberOfPlayers = sc.nextInt(); 
        System.out.println("Enter no. of Ladders");
        int numberOfLadders = sc.nextInt(); 
        System.out.println("Enter no. of Snakes");
        int numberOfSnakes = sc.nextInt(); 
        
        Game game = new Game(numberOfSnakes,numberOfLadders,boardSize); 
        for(int i=0;i<numberOfPlayers;i++) {
        	System.out.println("Enter Player name");
        	String p1 = sc.next();
        	Player player = new Player(p1); 
        	game.addPlayer(player);
        }
        
        game.playGame();
     
    }
}
