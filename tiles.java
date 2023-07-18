package INVENTARIO;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class tiles {

	private final int largura = 48, altura = 48;
	private int posX, posY;	
	//private	String caminhoImg;
	private Image ImgAtual;
	private Image imgGrass, imgSand, imgWall, imgWater;
	private Image imgWhite, imgGray;
	
	private boolean colisao;
	
	//construtor
	tiles()
	{
		this.carregaImagensTiles();
	}
	
	public void carregaImagensTiles() 
	{
		ImageIcon icon;
		icon = new ImageIcon("res/tiles/grass1.png");
		this.imgGrass = icon.getImage();
		icon = new ImageIcon("res/tiles/sand1.png");
		this.imgSand = icon.getImage();
		icon = new ImageIcon("res/tiles/water1.png");
		this.imgWater = icon.getImage();
		icon = new ImageIcon("res/tiles/wall1.png");
		this.imgWall = icon.getImage();
		icon = new ImageIcon("res/tiles/white.png");
		this.imgWhite = icon.getImage();
		icon = new ImageIcon("res/tiles/gray.png");
		this.imgGray = icon.getImage();
	}
	//DECLARANDO O METODO QUE IRA PERMITIR QUAL TILE IRA PERMITIR A COLISAO OU NAO
	public void carregaPecaDaMatriz(int ValorDaPeca) 
	{
		if (ValorDaPeca == 0)
		{
			this.ImgAtual = this.imgWall;
			this.colisao = true;//nao permite passagem.
		}
		if (ValorDaPeca == 1) 
		{
			this.ImgAtual = this.imgSand;
			this.colisao = false;//permite passagem.
		}
		if (ValorDaPeca == 2) 
		{
			this.ImgAtual = this.imgWater;
			this.colisao = true;//nao permite passagem.
		}
		if (ValorDaPeca == 3) 
		{
			this.ImgAtual = this.imgGrass;
			this.colisao = false;//permite passagem.
		}
		if (ValorDaPeca == 4) 
		{
			this.ImgAtual = this.imgWhite;
			this.colisao = false;//permite passagem.
		}
		if (ValorDaPeca == 5) 
		{
			this.ImgAtual = this.imgGray;
			this.colisao = true;//nao permite passagem.
		}
		//MUDA OS TILES PARA BRANCO E CINZA PARA TESTE DE COLISOES
		/*if (this.colisao == true) 
			{
			this.ImgAtual = this.imgGray;
			}
		else {
			this.ImgAtual = this.imgWhite;
		}*/
	}
	
	public boolean isColisao() {
		return colisao;
	}
	public void setColisao(boolean colisao) {
		this.colisao = colisao;
	}
	
	
	public void desenharTiles(Graphics2D desenho, int linha, int coluna)
	{
		this.posX =  coluna * this.largura;
		this.posY =  linha * this.altura;
		desenho.drawImage(this.ImgAtual, this.posX, this.posY, this.largura, this.altura, null);
	}
	
	
}
/*ImageIcon icon;
icon = new ImageIcon("res/tiles/white.png");
this.imgAtual = icon.getImage();
for (int i = 0; i<= 800; i= i+48)
desenho.drawImage(this.imgAtual, i, 0,  this.largura, this.altura, null);
for (int j = 0; j<= 500; j= j+48)
*/
