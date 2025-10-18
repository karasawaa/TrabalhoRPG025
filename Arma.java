public interface Arma {
    int getDanoBase();
    String getNome();
    int getCustoMana();
    boolean podeUsar(Personagem personagem);
    ResultadoAtaque atacar(Personagem atacante, Personagem alvo);
}