package INVENTARIO;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class escutarTeclado implements KeyListener {
	
	public boolean movePraBaixo, movePraCima, movePraEsq, movePraDir;

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		// NÃO SERÁ UTILIZADA, MAS NÃO PODE SER APAGADA
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int numTecla = e.getKeyCode();
		//System.out.println(e.getKeyCode());
		switch (numTecla) {
		case 37: //esquerda
			this.movePraEsq = true;
		break;
		case 38: //cima
			this.movePraCima = true;
		break;
		case 39: //direita
			this.movePraDir = true;
		break;
		case 40: //baixo
			this.movePraBaixo = true;
		break;
		case 27: //fecha o programa
			System.exit(0);
		break;
		default:
			System.out.println("tecla sem efeito");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int numTecla = e.getKeyCode();
		switch (numTecla) {
		case 37: //esquerda
			this.movePraEsq = false;
		break;
		case 38: //cima
			this.movePraCima = false;
		break;
		case 39: //direita
			this.movePraDir = false;
		break;
		case 40: //baixo
			this.movePraBaixo = false;
		break;
		case 27: //fecha o programa
			System.exit(0);
		break;
		default:
			System.out.println("Tecla sem efeito");
		break;
		}
		
	}

}
