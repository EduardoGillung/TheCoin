package INVENTARIO;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class objetos
{
	private int posX, posY;
	private int largura = 48, altura = 48;
	private Image imagemObjeto;
	String nomeObj;
	String cenario;
	Rectangle areaSolida;
	boolean visivel;

	
	public objetos(int x, int y, String cena, String nome) //CONSTRUTOR
	{
		this.posX = x;
		this.posY = y;
		this.nomeObj = nome;
		this.cenario = cena;
		//IDENTIFICADOR DE AREA SOLIDA
		this.visivel = true;
		
		//CARREGA IMAGENS DO OBJETO QUE CORRESPONDE AO NOME DO MESMO
		ImageIcon icon;
		String caminho = "";
		if (this.nomeObj == "chave") //CHAVE
			caminho = "res/objetos/" + this.nomeObj + ".png";
		else if (this.nomeObj == "moeda")//MOEDA
			caminho = "res/objetos/" + this.nomeObj + ".png";
		icon = new ImageIcon(caminho);
		
		this.imagemObjeto = icon.getImage();
		
		//DEFINE VALORES P AREA SOLIDA DO JOGADOR
		this.areaSolida = new Rectangle();
		this.areaSolida.x = this.posX +10;
		this.areaSolida.y = this.posY +12;
		this.areaSolida.width = this.largura -17;
		this.areaSolida.height = this.altura-25;
		
		
	}
	
	
	
	public void desenharObjeto(Graphics desenho)
	{
		//CASO A COLISAO COM O OBJETO DETECTE, CAPTURA O OBJETO REPINTANDO A TELA PARA IR AO SEU INVENTARIO E SUMINDO O ITEM 
		if(this.visivel) {
		//desenha area de colisao do jogador
		//desenho.setColor(Color.black);
		//desenho.fillRect(this.areaSolida.x, this.areaSolida.y, this.areaSolida.width, this.areaSolida.height);
		desenho.drawImage(imagemObjeto,this.posX, this.posY, this.altura, this.largura, null );
		//desenha sprite do jogador.
		}
	
}
}
