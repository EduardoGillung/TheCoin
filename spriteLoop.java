package INVENTARIO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class spriteLoop extends Thread implements Runnable, ActionListener {
	
	 //PARA O SPRITE SER APRESENTADO APENAS 10 VEZES POR SEG
	//E NAO 60 COMO Ã‰ O DE CADA CENA DO JOGO(GAMELOOP) PARA FAZER A ANIMAÇÃO A 10FPS

	int FPS = 10;
	Timer ControleDoTempoDoJogo;
	long contadorDeFPS;
	painel cenaDoJogo;
	escutarTeclado EscutaTecl;
	
	
	spriteLoop(painel P, escutarTeclado eT)
	{
		System.out.println("spriteLoop instanciado");
		this.cenaDoJogo = P;
		this.EscutaTecl = eT;
	}
	
	//metodo obrigatorio devido a interface  Runnable 
	@Override
	public void run()
	{
		//inicia o contado de prints.
		this.contadorDeFPS = 0;
		//intancia o Controle de Tempo do sprite.
		//o qual vai disparar um evento a cada 1000 milisegundos
		this.ControleDoTempoDoJogo = new  Timer(1000, this);
		//inicia o controle do tempo.
		this.ControleDoTempoDoJogo.start();
		
		//****************************
			//define o framerate do jogo sendo 10FPS.
			double FrameRate = 1000000000/this.FPS; //nanosegundos divididos pelo numero de frames.
			//define o tempo decorrido do sprite 
			double TempoDecorrido = 0;
			//Armazena o ultimo momento (em Nanosegundos) que o spriteLoop aconteceu
			long TempoUltimaMedidaDoLoop = System.nanoTime();
			//vai armazenar o tempo atual do jogo em cada  spriteLoop
			long TempoAtualDoLoop;
		while (this.isAlive())
		{
			//Captura o tempo atual do jogo  (em nanosegundos) a cada loop realizado pelo computador.
			TempoAtualDoLoop = System.nanoTime();
			TempoDecorrido = TempoDecorrido + (TempoAtualDoLoop - TempoUltimaMedidaDoLoop)/FrameRate;
			//atualiza o valor do ultimo momento em que o Loop aconteceu
			TempoUltimaMedidaDoLoop = TempoAtualDoLoop;
	
			if (TempoDecorrido >= 1)
			{
				//atualiza status do componentes do Sprite.
				cenaDoJogo.jogador.atualizaSprite(EscutaTecl.movePraEsq, EscutaTecl.movePraCima, EscutaTecl.movePraDir, EscutaTecl.movePraBaixo);
				//incrementa o contador de prints em uma unidade 
				this.contadorDeFPS ++;
				//zera o tempo decorrido para que o mesmo possa atngir 1 segundo novamente.
				TempoDecorrido = 0;
			}
		}
	}
	
	//metodo obrigatorio devido  a interface ActionListerner
	public void actionPerformed(ActionEvent e) 
	{
		System.out.println("FPS Sprite calculado: " + this.contadorDeFPS);
		this.contadorDeFPS = 0;
	}

}
