package INVENTARIO;

import java.awt.GradientPaint;
import java.awt.Graphics2D;

public class tileMap {
	
	private String cenaValida;
	
	//PEÃ‡A DO CENARIO
	tiles pecaDoCenario;
	//ARMAZENA O CENARIO VALIDO DO JOGO
	int [][] cenarioValido;
	
	/*0 wall /  parede
	1 Sand /  areia
	2 Water / agua
	3 Grass / grama
	4 white / branco //testes
	5 Gray /  cinza //testes
	*/
	
	//CENARIO TOPO ESQUERDO 
	int [][] cenarioTopoEsq = {	
								{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
								{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
								{0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
								{0,1,1,0,1,1,1,1,1,1,1,1,1,1,1,0},
								{0,1,1,0,1,1,1,1,1,1,1,1,1,1,1,0},
								{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
								{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
								{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
								{0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0}
							    };
	//CENARIO TOPO DIREITO 
	int [][] cenarioTopoDir = { 
								{0,3,3,0,2,2,2,2,2,2,2,2,0,3,3,0},
								{0,3,3,0,2,2,2,2,2,2,2,2,0,3,3,0},
								{0,3,3,0,2,2,2,2,2,2,2,2,0,3,3,0},
								{0,3,0,0,0,0,0,0,0,0,0,0,0,0,3,0},
								{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
								{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
								{0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0},
								{0,1,1,1,0,2,2,2,2,2,2,0,1,1,1,0},
								{0,1,1,1,0,2,2,2,2,2,2,0,1,1,1,0},
								{0,0,0,0,2,2,2,2,2,2,2,2,0,1,1,0}
						         };

	//CENARIO BASE DIREITA
	int [][] cenarioBaseDir = {	
									{0,0,0,0,2,2,2,2,2,2,2,0,0,1,1,0},
									{0,3,3,0,2,2,2,2,2,2,2,0,0,1,1,0},
									{0,3,3,0,0,0,0,0,0,0,0,0,0,1,1,0},
									{0,3,3,0,0,0,0,0,0,0,0,0,0,1,1,0},
									{0,3,3,1,1,1,1,1,1,1,0,0,0,1,1,0},
									{0,3,3,1,1,1,1,1,1,1,0,0,0,1,1,0},
									{1,1,1,1,1,0,0,0,1,1,0,0,0,1,1,0},
									{1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,0},
									{0,3,3,3,3,0,0,0,1,1,1,1,1,1,1,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
							        };

	//CENARIO BASE ESQUERDA 
int [][] cenarioBaseEsq = {	
								{0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0},
								{0,3,3,3,1,1,3,3,3,3,3,3,3,3,3,0},
								{0,3,3,3,1,1,3,3,3,3,3,3,3,3,3,0},
								{2,2,3,3,1,1,3,3,3,3,3,3,3,3,3,0},
								{2,2,2,3,1,1,3,3,3,3,3,3,3,3,3,0},
								{2,2,2,3,1,1,1,1,3,3,3,3,3,3,3,0},
								{2,2,2,3,1,1,1,1,1,1,1,1,1,1,1,1},
								{2,2,2,3,3,1,1,1,1,1,1,1,1,1,1,1},
								{2,2,2,3,3,3,3,3,3,3,3,3,3,3,3,0},
								{2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
						        };
tileMap() 
	{
		//define qual cenario do jogo que vai ser carregado
		cenarioValido = cenarioTopoEsq;
		this.cenaValida = "TE";
		this.pecaDoCenario = new tiles();//instancia todas as imagens possï¿½vel para este jogo.
	}
	
	public void desenharTileMap(Graphics2D g) 
	{
		int pecaDaMatriz; //identifica qual peca da matriz será carregada
		
		for (int col = 0; col < cenarioValido[0].length; col++)
			for (int row = 0; row < cenarioValido.length; row++)
		{
			//captura os nï¿½meros da matriz dentro da varredura(um de cada vez)
			pecaDaMatriz = cenarioValido[row][col];
			//carrega o img correspodente ao numero de cada matriz (um de cada vez)
			this.pecaDoCenario.carregaPecaDaMatriz(pecaDaMatriz);
			//desenha o tile correspondente (um de cada vez)
			this.pecaDoCenario.desenharTiles(g, row, col);
		}
	}
	
	public String getCenaValida() {
		return this.cenaValida;
	}
	//DECLARANDO OS CENARIOS VALIDOS QUE PODERÃO SER UTILIZADOS
	public void setCenaValida(String cenaValida) {
		this.cenaValida = cenaValida;
		switch (this.cenaValida) {
			case "BE": //base esquerda
				this.cenarioValido = this.cenarioBaseEsq;
				break;
			case "BD": //base direita
				this.cenarioValido = this.cenarioBaseDir;
				break;
			case "TE": //topo esquerdo
				this.cenarioValido =  this.cenarioTopoEsq;
				break;
			case "TD": //topo direita
				this.cenarioValido =  this.cenarioTopoDir;
				break;
		}
	}
	
}
