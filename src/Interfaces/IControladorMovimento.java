package Interfaces;

import AmbienteP.Ambiente;
import Robos.Robo;

public interface IControladorMovimento {
    void mover(int deltaX, int deltaY, int deltaZ, int tempo, Ambiente ambiente, Robo robo