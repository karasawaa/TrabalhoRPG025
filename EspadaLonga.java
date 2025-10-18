import java.util.Random;

public class EspadaLonga implements Arma {
    private Random random = new Random();
    
    @Override
    public int getDanoBase() { return 15; }
    
    @Override
    public String getNome() { return "Espada Longa"; }
    
    @Override
    public int getCustoMana() { return 0; }
    
    @Override
    public boolean podeUsar(Personagem personagem) {
        return personagem.getForca() >= 10;
    }
    
    @Override
    public ResultadoAtaque atacar(Personagem atacante, Personagem alvo) {
        int dano = getDanoBase();
        StatusEffect efeito = null;
        String mensagem = atacante.getNome() + " ataca com " + getNome() + " causando " + dano + " de dano!";
        
        if (random.nextDouble() < 0.3) {
            efeito = new Sangramento(3);
            mensagem += " Corte Profundo! Inimigo sangrando por 3 turnos!";
        }
        
        return new ResultadoAtaque(dano, mensagem, efeito);
    }
}