public class ArcoElfico implements Arma {
    @Override
    public int getDanoBase() { return 12; }
    
    @Override
    public String getNome() { return "Arco Élfico"; }
    
    @Override
    public int getCustoMana() { return 15; }
    
    @Override
    public boolean podeUsar(Personagem personagem) {
        return personagem.getDestreza() >= 8;
    }
    
    @Override
    public ResultadoAtaque atacar(Personagem atacante, Personagem alvo) {
        int dano = getDanoBase();
        String mensagem = atacante.getNome() + " usa " + getNome() + " causando " + dano + " de dano!";
        mensagem += " Chuva de Flechas! Atinge todos os inimigos!";
        
        return new ResultadoAtaque(dano, mensagem, null);
    }
}