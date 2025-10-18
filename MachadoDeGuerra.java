import java.util.Random;

public class MachadoDeGuerra implements Arma {
    private Random random = new Random();
    
    @Override
    public int getDanoBase() { return 18; }
    
    @Override
    public String getNome() { return "Machado de Guerra"; }
    
    @Override
    public int getCustoMana() { return 5; }
    
    @Override
    public boolean podeUsar(Personagem personagem) {
        return personagem.getForca() >= 15;
    }
    
    @Override
    public ResultadoAtaque atacar(Personagem atacante, Personagem alvo) {
        int dano = getDanoBase();
        StatusEffect efeito = null;
        String mensagem = atacante.getNome() + " ataca com " + getNome() + " causando " + dano + " de dano!";
        
        if (random.nextDouble() < 0.25) {
            efeito = new Atordoado(1);
            mensagem += " Golpe Esmagador! Inimigo atordoado!";
        }
        
        return new ResultadoAtaque(dano, mensagem, efeito);
    }
}