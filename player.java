package INVENTARIO;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class player {

	
	int posX, posY, largura, altura;
	private Color CorDeFundo;
	
	//VARIAVEIS DAS IMAGENS DOS SPRITES QUE ALTERNARAM PARA FAZER MOVIMENTO DO JOGADOR
	Image imagemD1, imagemR1, imagemL1, imagemU1;
	Image imagemD2, imagemR2, imagemL2, imagemU2;
	Image imagemD3, imagemR3, imagemL3, imagemU3;
	Image imagemPlayer; //importar o imageIcon
	
	int Velocidade = 3;
	Rectangle areaSolida;
	INVENTARIO[] invetarioJogador;
	
	player(int posX, int posY, int largura, int altura)
	{
		ImageIcon icon;
		
		this.posX = posX;
		this.posY = posY;
		this.largura = largura;
		this.altura = altura;
		this.CorDeFundo = Color.white;
		
		//CARREGANDO OS SPRITES PARA DO JOGADOR
		icon = new ImageIcon("res/player/down1.png");
		this.imagemPlayer = icon.getImage();
		icon = new ImageIcon("res/player/down1.png");
		this.imagemD1 = icon.getImage();
		icon = new ImageIcon("res/player/left1.png");
		this.imagemL1 = icon.getImage();
		icon = new ImageIcon("res/player/right1.png");
		this.imagemR1 = icon.getImage();
		icon = new ImageIcon("res/player/up1.png");
		this.imagemU1 = icon.getImage();
		icon = new ImageIcon("res/player/down2.png");
		this.imagemD2 = icon.getImage();
		icon = new ImageIcon("res/player/left2.png");
		this.imagemL2 = icon.getImage();
		icon = new ImageIcon("res/player/right2.png");
		this.imagemR2 = icon.getImage();
		icon = new ImageIcon("res/player/up2.png");
		this.imagemU2 = icon.getImage();
		icon = new ImageIcon("res/player/down3.png");
		this.imagemD3 = icon.getImage();
		icon = new ImageIcon("res/player/left3.png");
		this.imagemL3 = icon.getImage();
		icon = new ImageIcon("res/player/right3.png");
		this.imagemR3 = icon.getImage();
		icon = new ImageIcon("res/player/up3.png");
		this.imagemU3 = icon.getImage();
		
		//INTANCIA O RETANGULO DA AREA SOLIDA PUXANDO TODOS OS PARAMETROS DO RETANGULO(posy, posx, altura, largura)
		DefineValoresAreaSolidaJogador();
		
		//INSTANCIA O ARRAY INVENTARIO COM DUAS POSICOES 
		invetarioJogador = new INVENTARIO[2];
		//INSTANCIA  A PRIMEIRAPOSICAO COMO CHAVE E O SEUGNDO COMO MOEDA
		invetarioJogador[0] = new INVENTARIO("chave");
		invetarioJogador[1] = new INVENTARIO("moeda");
	}
	
	public void DefineValoresAreaSolidaJogador()
	{
		this.areaSolida = new Rectangle(); //neste caso o retangulo toma conta de parte do player
		this.areaSolida.x = this.posX + 7;
		this.areaSolida.y = this.posY + this.altura/2;
		this.areaSolida.width = this.largura - 20;
		this.areaSolida.height = this.altura/2;
	}
	
	public void atualizaPosicao(boolean moveEsq, boolean moveCima, boolean moveDir, boolean moveBaixo)
	{
		//ATUALIZA POSICAO DO JOGADOR CONFORME A TECLA PRESSIONADA 
		if (moveEsq)  this.posX = this.posX - this.Velocidade;				
		else if (moveDir)	this.posX = this.posX + this.Velocidade;						
		else if (moveCima)	this.posY = this.posY - this.Velocidade;					
		else if (moveBaixo)	this.posY = this.posY + this.Velocidade;
		
		//ATUALIZA A AREA SOLIDA DE COLISAO PARA QUANDO O JOGADOR ANDAR ELA ATUALIZAR JUNTAMENTE A ELE
		this.areaSolida.x = this.posX + 7;
		this.areaSolida.y = this.posY + this.altura/2;
		
		//PARA VERIFICAR COLUNA E LINHA DO TILE QUE O JOGADOR ESTÁ
		//System.out.println("coluna= " + (int)this.posX/this.largura);
		//System.out.println("linha = " + (int)this.posY/this.altura);
	}
	// ATUALIZA AS IMAGENS DOS SPRITES AO JOGADOR SE MOVER (ANIMAÇAO)
	public void atualizaSprite(boolean moveEsq, boolean moveCima, boolean moveDir, boolean moveBaixo)
	{
		if (moveEsq)
		{
			if (this.imagemPlayer == this.imagemL1)
				this.imagemPlayer = this.imagemL2;
			else if((this.imagemPlayer == this.imagemL2))
				this.imagemPlayer = this.imagemL3;
			else
				this.imagemPlayer = this.imagemL1;
		}
		if (moveDir)
		{
			if (this.imagemPlayer == this.imagemR1)
				this.imagemPlayer = this.imagemR2;
			else if((this.imagemPlayer == this.imagemR2))
				this.imagemPlayer = this.imagemR3;
			else
				this.imagemPlayer = this.imagemR1;
		}
		if (moveCima)	
		{
			if (this.imagemPlayer == this.imagemU1)
				this.imagemPlayer = this.imagemU2;
			else if((this.imagemPlayer == this.imagemU2))
				this.imagemPlayer = this.imagemU3;
			else
				this.imagemPlayer = this.imagemU2;
		}
		if (moveBaixo)	
			{
			if (this.imagemPlayer == this.imagemD1)
				this.imagemPlayer = this.imagemD2;
			else if((this.imagemPlayer == this.imagemD2))
				this.imagemPlayer = this.imagemD3;
			else
				this.imagemPlayer = this.imagemD2;
			}
	}
	//CLASE QUE PERMITE CAPTURAR O OBJETO SOBRE QUAL PASSOU POR CIMA
	public void capturaObjeto(String nome)
	{
		for(int i = 0; i <invetarioJogador.length; i++)
		{
			if (this.invetarioJogador[i].nomeObjeto == nome)
			{
				this.invetarioJogador[i].qtdeOjbeto++;
				break;
			}
		}
	}
	
	public void DesenhaPlayer(Graphics desenho)
	{
		//desenha area de colisao do jogador
		//desenho.setColor(Color.black);
		//desenho.fillRect(this.areaSolida.x, this.areaSolida.y, this.areaSolida.width, this.areaSolida.height);
		//desenha sprite do jogador.
		desenho.drawImage(imagemPlayer, this.posX, this.posY, this.largura, this.altura, null);
	}
}
