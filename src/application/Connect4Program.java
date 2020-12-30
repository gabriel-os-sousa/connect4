package application;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.entities.Board;
import model.exception.DomainException;

public class Connect4Program {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		Scanner sc = new Scanner(System.in);
		Board board = new Board();
		
		board.zerarTabuleiro();
		board.exibirTabuleiro();
		

		while (board.getJogador() != 0) {
			System.out.print(board.getJogador() != 0 ? "Jogador "+ board.getJogador() : "Jogador "+ board.getJogador());

			int coluna;
			boolean erro = false;
			
			while (!erro) {
				try {
					System.out.println(" selecione a coluna:");
					coluna = sc.nextInt();
					erro = board.colocarPedra(coluna, board.getJogador());
				} catch (DomainException e) {
					System.out.println(e.getMessage());
				} catch (InputMismatchException e) {
					System.out.println("Digite a coluna (0 a 7)");
					sc.next();
				}
			}
			
			board.trocarJogador();
			board.exibirTabuleiro();
			
			int ganhador = board.verificarGanhador();
			if(ganhador != 0) {
				System.out.println("--> Ganhou o jogador "+ ganhador);
				board.setJogador(0);
			}
		}
		
		sc.close();
	}
}
