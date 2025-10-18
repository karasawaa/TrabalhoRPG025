public class RPGMedieval {
    public static void main(String[] args) {
        // Criar personagens jogáveis
        Guerreiro guerreiro = new Guerreiro("Conan");
        Arqueiro arqueiro = new Arqueiro("Legolas");
        Mago mago = new Mago("Gandalf");
        Paladino paladino = new Paladino("Arthur");
        
        // Equipar armas iniciais
        guerreiro.equiparArma(new EspadaLonga());
        arqueiro.equiparArma(new ArcoElfico());
        mago.equiparArma(new CajadoArcano());
        paladino.equiparArma(new MachadoDeGuerra());
        
        // Criar inimigos
        Guerreiro orc = new Guerreiro("Orc Guerreiro");
        Arqueiro goblin = new Arqueiro("Goblin Arqueiro");
        Mago bruxo = new Mago("Bruxo das Trevas");
        
        orc.equiparArma(new MachadoDeGuerra());
        goblin.equiparArma(new AdagaSombria());
        bruxo.equiparArma(new CajadoArcano());
        
        // Configurar batalha
        Batalha batalha = new Batalha();
        batalha.adicionarJogador(guerreiro);
        batalha.adicionarJogador(arqueiro);
        batalha.adicionarJogador(mago);
        batalha.adicionarJogador(paladino);
        
        batalha.adicionarInimigo(orc);
        batalha.adicionarInimigo(goblin);
        batalha.adicionarInimigo(bruxo);
        
        // Iniciar batalha
        batalha.iniciarBatalha();
    }
}