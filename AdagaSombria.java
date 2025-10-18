public class AdagaSombria implements Arma {
    private boolean inimigoDesprevenido = true;
    
    @Override
    public int getDanoBase() { return 10; }
    
    @Override
    public String getNome() { return "Adaga Sombria"; }
    
    @Override
    public int getCustoMana() { return 10; }
    
    @Override
    public boolean podeUsar(Personagem personagem) {
        return personagem.getDestreza() >= 12;
    }
    
    @Override
    public ResultadoAtaque atacar(Personagem atacante, Personagem alvo) {
        int dano = getDanoBase();
        String mensagem = atacante.getNome() + " ataca com " + getNome();
        
        if (inimigoDesprevenido) {
            dano *= 3;
            mensagem += " Ataque Furtivo! Dano triplo: " + dano + "!";
            inimigoDesprevenido = false;
        } else {
            mensagem += " causando " + dano + " de dano!";
        }
        
        return new ResultadoAtaque(dano, mensagem, null);
    }
}