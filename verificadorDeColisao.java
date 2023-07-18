package INVENTARIO;

import java.awt.Rectangle;

public class verificadorDeColisao {

	//atributos privados
	private int colEsqX;
	private int colDirX;
	private int rowTopoY;
	private int rowBaseY;
	boolean ColisaoOcorreu;
	
	//construtor
	public verificadorDeColisao() 
	{
		//vazio
	}
	//CONSTRUTOR
	public boolean VerificaColisao(tileMap cenaDoJogo, player jogador, String direcao)
	{
		//parte-se do principio que nao vai ocorrer a colisao
		ColisaoOcorreu = false;
		//COM NA BASE NA FIGURA 66 (Capitulo 8 - parte 2)
		//ponto A = (BordaEsqX, BordaTopoY)
		//ponto B = (BordaDirX, BordaTopoY)
		//ponto C = (BordaEsqx, BordaBaseY)
		//tudo calculo com relaçao a area solida do jogador
		//VERIFICANDO LADO ESQUERDO DA AREA SOLIDA 
		int bordaEsqX = jogador.areaSolida.x;
		int bordaDirX = jogador.areaSolida.x + (int)jogador.areaSolida.getWidth();
		int bordaTopoY = jogador.areaSolida.y;
		int bordaBaseY = jogador.areaSolida.y + (int)jogador.areaSolida.getHeight();
		
		//transformando posicao na cena do jogo em linhasxcolunas do tilemap.
		this.colEsqX = bordaEsqX/48;
		this.colDirX = bordaDirX/48;
		this.rowTopoY = bordaTopoY/48;
		this.rowBaseY = bordaBaseY/48;
		
		//verifica a direcao do movimento do jogador.
		if (direcao == "cima") 
		{
			//simula o avanço do jogador para ver onde ele vai estar se o movimento acontecer.
			int prox_rowTopoY = (bordaTopoY - jogador.Velocidade)/48;
			//VERIFICA SE A POSICAO Y DA AREA SOLIDA DO JOGADOR É MENOR QUE ZERO
			//CASO SEJA POSITIVA O JOGADOR DEVE VERIFICAR SE ESTÁ NA BASE ESQUERDA OU DIREITA
			if (jogador.areaSolida.y < 0) 
			{
				if (cenaDoJogo.getCenaValida() == "BE")
					cenaDoJogo.setCenaValida("TE");
				else
					cenaDoJogo.setCenaValida("TD");
				
				int alturaCenario = cenaDoJogo.cenarioValido.length*48;
				//VERIFICA SE O JOGADOR COLIDIU COM O LIMITE DO CENARIO PEGANDO A ALTURA 
				jogador.posY = alturaCenario - (int)jogador.areaSolida.getHeight();
			}
			else
			{
				//verifica o topo do jogador pelo lado esquerdo
				cenaDoJogo.pecaDoCenario.carregaPecaDaMatriz(cenaDoJogo.cenarioValido[prox_rowTopoY][colEsqX]);
				if (cenaDoJogo.pecaDoCenario.isColisao())
					this.ColisaoOcorreu = true;
				//verifica o topo jogador pelo lado direto
				cenaDoJogo.pecaDoCenario.carregaPecaDaMatriz(cenaDoJogo.cenarioValido[prox_rowTopoY][colDirX]);
				if (cenaDoJogo.pecaDoCenario.isColisao())
					this.ColisaoOcorreu = true;
			}
		}
		else if (direcao == "baixo") 
		{
			int prox_rowBaseY = (bordaBaseY + jogador.Velocidade)/48;
			if (prox_rowBaseY < cenaDoJogo.cenarioValido.length)//VERIFICA SE O JOGADOR COLIDIU COM O LIMITE DA CENA (ALTURA)
			{
				//verifica a base do jogador pelo lado esquerdo
				cenaDoJogo.pecaDoCenario.carregaPecaDaMatriz(cenaDoJogo.cenarioValido[prox_rowBaseY][colEsqX]);
				if (cenaDoJogo.pecaDoCenario.isColisao())
					this.ColisaoOcorreu = true;
				//verifica a base do jogador pelo lado direto
				cenaDoJogo.pecaDoCenario.carregaPecaDaMatriz(cenaDoJogo.cenarioValido[prox_rowBaseY][colDirX]);
				if (cenaDoJogo.pecaDoCenario.isColisao())
					this.ColisaoOcorreu = true;
			}
			else {
				//CASO O JOGADOR INICIOU NO TOPO ESQUERDO
				if (cenaDoJogo.getCenaValida() == "TE")//CASO O CENARIO INICIAL SEJA TE
					cenaDoJogo.setCenaValida("BE");//DIRECIONA A BASE ESQUERDA
				else
					cenaDoJogo.setCenaValida("BD");//CASO CONTRARIO SE FOR DIRECIONA PARA BASE DIREITA
				jogador.posY = -(int)jogador.areaSolida.getHeight();
			}
		}
		else if (direcao == "direita") {
			// simula o avanço do jogador para ver onde ele vai estar se o movimento acontecer.
			int prox_colDirX = (bordaDirX + jogador.Velocidade)/48;
			if (prox_colDirX < cenaDoJogo.cenarioValido[0].length)//VERIFICA SE O JOGADOR COLIDIU COM O LIMITE DA CENA
			{
				// verifica o lado do jogador pela parte inferior
				cenaDoJogo.pecaDoCenario.carregaPecaDaMatriz(cenaDoJogo.cenarioValido[rowBaseY][prox_colDirX]);
				if (cenaDoJogo.pecaDoCenario.isColisao())
					this.ColisaoOcorreu = true;
				// verifica o lado do jogador pela parte superior
				cenaDoJogo.pecaDoCenario.carregaPecaDaMatriz(cenaDoJogo.cenarioValido[rowTopoY][prox_colDirX]);
				if (cenaDoJogo.pecaDoCenario.isColisao())
					this.ColisaoOcorreu = true;
		
			} 
			else {
				if (cenaDoJogo.getCenaValida() == "TE")//QUER DIZER QUE O JOGADOR ESTÁ NESTE CENARIO
					cenaDoJogo.setCenaValida("TD");//PROXIMO CENARIO QUE ELE SERÁ DIRECIONADO 
				else
					cenaDoJogo.setCenaValida("BD");
				//VERIFICA A POSICAO X DO JOGADOR 
				jogador.posX = (int) jogador.areaSolida.getHeight();
			}

		}
		else if (direcao == "esquerda") 
		{
			int prox_rowBaseY = (bordaEsqX - jogador.Velocidade)/48;
			if (jogador.areaSolida.x < 0) {
				if (cenaDoJogo.getCenaValida() == "TD")//CENARIO INICIAL DO JOGADOR
					cenaDoJogo.setCenaValida("TE");//PROXIMO CENARIO QUE ELE SERÁ DIRECIONADO 
				else
					cenaDoJogo.setCenaValida("BE");//CASO O CENARIO INICIAL 
				
				//calcula posicao do jogador no cenario esquerda
				int larguraCenario = cenaDoJogo.cenarioValido[0].length * 48;
				jogador.posX = larguraCenario - (int) jogador.areaSolida.getWidth();
			}
			else
			{
			cenaDoJogo.pecaDoCenario.carregaPecaDaMatriz(cenaDoJogo.cenarioValido[rowBaseY][prox_rowBaseY]);
			if (cenaDoJogo.pecaDoCenario.isColisao())
				this.ColisaoOcorreu = true;
			//verifica o topo jogador pelo lado direto
			cenaDoJogo.pecaDoCenario.carregaPecaDaMatriz(cenaDoJogo.cenarioValido[rowTopoY][prox_rowBaseY]);
			//RETORNA TRUE CASO TENHA COLIDIDO COM O TILE DO CENARIO
			if (cenaDoJogo.pecaDoCenario.isColisao())
				this.ColisaoOcorreu = true;
			}
		}

		/*
		//mostra os dados no console apenas para conferencia
		System.out.println("ColEsqX: " + this.colEsqX);
		System.out.println("ColDirX: " + this.colDirX);
		System.out.println("rowTopY: " + this.rowTopoY);
		System.out.println("rowBaseY: " + this.rowBaseY);
		System.out.println("--------------------------------");*/
		return ColisaoOcorreu;
	}
	//SOBESCRITA
	//VERIFICA COLISAO DO JOGADOR COM OBJETOS DO CENARIO(MOEDA E CHAVE)
	public boolean VerificaColisao(Rectangle Sprite1, Rectangle Sprite2)
	{
		boolean retorno = false;
		//METODO QUE POSSIBILITA A VERIFICAÇÃO ENTRE AS 2 COLISOES
		if(Sprite1.intersects(Sprite2))
			retorno = true;
		return retorno;
		
	}
}

