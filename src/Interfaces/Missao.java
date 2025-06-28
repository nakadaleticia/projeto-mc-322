package Interfaces;

import AmbienteP.Ambiente;
import Robos.AgenteInteligente;
import Robos.Robo;

public interface Missao {
    void executar(AgenteInteligente r, Ambiente a);
}
