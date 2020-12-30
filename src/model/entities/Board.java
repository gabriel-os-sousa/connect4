package model.entities;

import model.exception.DomainException;

public class Board {
	public static final int SIZE = 8;
	int[][] board = new int[SIZE][SIZE]; // [linha][coluna]
	private int jogador;
	
	public Board() {
		this.jogador = 1;
	}
	
	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}

	public int getJogador() {
		return jogador;
	}
	
	public void setJogador(int jogador) {
		this.jogador = jogador;
	}

	public void trocarJogador() {
		if (jogador == 1) 
			setJogador(2);
		else
			setJogador(1);
	}

	public void zerarTabuleiro() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				board[i][j] = 9;
			}
		}
	}

	public void exibirTabuleiro() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				System.out.print("|" + board[i][j] + "|");
			}
			System.out.println();
		}
	}

	public boolean colocarPedra(int coluna, int jogador) throws DomainException  {
		// coloca a pedra do jogador lá
		int linha = verificarColuna(coluna);
		if( linha >= 0) {
			board[linha][coluna] = jogador;
			return true;
		}
		return false;
	}

	public int verificarColuna(int j) throws DomainException{
		// Percorre toda a coluna de baixo para cima, se tiver alguma posição vazia, retorna a posição, senão retorna -1
		if(j >= SIZE || j< 0) throw new  DomainException("Colunão não existente!"); 
		
		if(board[0][j] != 9) throw new DomainException("Coluna cheia, selecione outra!");
		
		for (int i = SIZE - 1; i >= 0; i--) {
			if (board[i][j] == 9) return i;
		}
		return -1;
	}
	
	public int verificarGanhador() {
		for (int i=0; i < SIZE; i++) {
			for (int j=0; j< SIZE; j++) {
				int p = board[i][j];
				
				if (p != 9) {
					//HORIZONTAL PRA FRENTE
					if (j <= 4) {
						if (board[i][j+1] == p && board[i][j+2] == p && board[i][j+3] == p) {
							//System.out.println("Sequencia de vitoria: ["+i+","+j+"] ["+i+","+(j+1)+"] ["+i+","+(j+2)+"] ["+i+","+(j+3)+"]");
							return p;
						}
					}
					
					//HORIZONTAL PRA TRAS
					if(j >= 3) {
						if (board[i][j-1] == p && board[i][j-2] == p && board[i][j-3] == p) {
							return p;
						}					
					}
					
					//VERTICAL PRA BAIXO
					if (i <= 4) {
						if (board[i+1][j] == p && board[i+2][j] == p && board[i+3][j] == p) {
							return p;
						}
					}
					
					//VERTICAL PRA CIMA
					if (i >= 3) {
						if (board[i-1][j] == p && board[i-2][j] == p && board[i-3][j] == p) {
							return p;
						}
					}
					
					//DIAGONAL BAIXO DIREITA
					if (j >= 0 && j <= 4 && i >= 0 && i<=4) {
						if (board[i+1][j+1] == p && board[i+2][j+2] == p && board[i+3][j+3] == p) {
							return p;
						}
					}
					
					//DIAGONAL CIMA DIREITA
					if (j >= 0 && j <= 4 && i >= 3 && i<=7) {
						if (board[i-1][j+1] == p && board[i-2][j+2] == p && board[i-3][j+3] == p) {
							return p;
						}
					}
					
					//DIAGONAL BAIXO ESQUERDA
					if (j >= 3 && j <= 7 && i >= 0 && i<=4) {
						if (board[i+1][j-1] == p && board[i+2][j-2] == p && board[i+3][j-3] == p) {
							return p;
						}
					}
					
					//DIAGONAL CIMA ESQUERDA
					if (j >= 3 && j <= 7 && i >= 3 && i<=7) {
						if (board[i-1][j-1] == p && board[i-2][j-2] == p && board[i-3][j-3] == p) {
							return p;
						}
					}
				}
			}
		}
		
		return 0;
	}
}
