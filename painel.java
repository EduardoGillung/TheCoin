package INVENTARIO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class painel extends JPanel {
	
	//METODOS PAINEL
	private String Posicao;
	private int LarguraP, AlturaP;
	private Color CorDeFundoP;

	player jogador;//IDENTIFICADOR DO JOGADOR
	tileMap cenario; ////IDENTIFICADOR DO CENARIO DO JOGO
	gameloop LoopDoJogo;//IDENTIFICADOR DO LOOP DO JOGO 60FPS
	spriteLoop LoopDoSprite;//IDENTIFICADOR DO LOOP DO SPRITE 10FPS
	escutarTeclado EscutaTecl;//IDENTIFICADOR DO ESCUTADOR DAS TECLAS DO JOGO
	objetos [] obj; //IDENTIFICADOR DE OBJETOS DO JOGO
	
	
	painel(String ID_Painel)
	{
		this.Posicao = ID_Painel;
		
		switch (this.Posicao) 
		{
			
			case "centro":
			{
				//atribui os valores para os atributos
				LarguraP = 768; AlturaP = 480;
				CorDeFundoP = Color.black;
				//instancia (cria) o jogador
				this.jogador = new player(670, 70, 48, 48); 
				//INSTANCIANDO OS OBJETOS DO CENARIO
				this.obj = new objetos[2];
				//INTANCIADNO A CHAVE NO CENARIO TOPO ESQUERDO E DENOMINANDO-O
				this.obj[0] = new objetos(200, 200, "TE", "chave");
				this.obj[1] = new objetos(70, 380, "TD", "moeda");
				
				//CARREGA O MAPA(MATRIZ) DA CENA DO JOGO
				this.cenario = new tileMap();
				
				//instancia o escutado do teclado.
				EscutaTecl = new escutarTeclado();
				this.addKeyListener(EscutaTecl);
				this.setFocusable(true);
				
				//INSTANCIA O CORACAO DO JOGO
				 //instancia (cria) o loop do jogos. 
				LoopDoJogo = new gameloop(this, EscutaTecl); 
				 //instancia o loop do jogo,  chamado o m√©todo run() da classe gameloop
				LoopDoJogo.start();
				
				//INTANCIA OS SPRITES DO JOGO
				LoopDoSprite = new spriteLoop(this, EscutaTecl);//cria loop dos sprites
				LoopDoSprite.start(); //instancia o loop do jogo,  chamado o metodo run() da classe gameloop
				
			}
				break;
			
		}
		//PINTA OS PAINEIS
		this.setPreferredSize(new Dimension(LarguraP, AlturaP));
		this.setBackground(CorDeFundoP);
	}
	
	public void paintComponent(Graphics g)
	{
		//DECLARANDO A BIBLIOTECA DE FORMAS GEOMETRICAS
		Graphics2D desenho = (Graphics2D) g;
		g.setColor(this.getBackground());
		//RETANGULO QUE PEGA OS PARAMETRO DA TELA PARA DESENHAR O FUNDO
		g.fillRect(0, 0, this.getWidth(), this.getHeight());		
		
		switch (this.Posicao)
		{
		case "centro": 
			//desenha o cenario
			this.cenario.desenharTileMap(desenho);
			//desenha o jogador no mapa
			jogador.DesenhaPlayer(desenho);
			
			//for(int i = 0; i< this.obj.length; i++)
			//	obj[i].DesenhaObjeto(desenho);
			
			//METODO PARA VERIFICA«√O DE QUAL CENA EST¡ SENDO APRESENTADA
			if(this.cenario.getCenaValida() == "TE")
			{
				for(int i = 0; i< this.obj.length; i++)
				{
					//VERIFICA SE O OBJETO EST¡ INSTANCIADO NO CENARIO SE SIM ELE … DESENHADO
					if(obj[i].cenario == "TE")
						obj[i].desenharObjeto(desenho);
				}
			}
			else if (this.cenario.getCenaValida() =="BD")
			{
				for(int i = 0; i< this.obj.length; i++)
				{
					//VERIFICA SE O OBJETO EST¡ INSTANCIADO NO CENARIO SE SIM ELE … DESENHADO
					if(obj[i].cenario == "BD")
						obj[i].desenharObjeto(desenho);	
				}
			}
			else if (this.cenario.getCenaValida() =="TD")
			{
				
				for(int i = 0; i< this.obj.length; i++)
				{
					//VERIFICA SE O OBJETO EST¡ INSTANCIADO NO CENARIO SE SIM ELE … DESENHADO
					if(obj[i].cenario == "TD")
						obj[i].desenharObjeto(desenho);	
				}
			}
			else if (this.cenario.getCenaValida() =="BE")
			{
				for(int i = 0; i< this.obj.length; i++)
				{
					//VERIFICA SE O OBJETO EST¡ INSTANCIADO NO CENARIO SE SIM ELE … DESENHADO
					if(obj[i].cenario == "BE")
						obj[i].desenharObjeto(desenho);	
				}
			}
		}
	}
}
