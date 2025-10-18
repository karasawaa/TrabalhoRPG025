import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Personagem {
    protected String nome;
    protected int vida;
    protected int vidaMaxima;
    protected int mana;
    protected int manaMaxima;
    protected int forca;
    protected int destreza;
    protected int inteligencia;
    protected Arma armaEquipada;
    protected List<StatusEffect> efeitosAtivos;
    protected Random random;
    
    public Personagem(String nome, int vida, int mana, int forca, int destreza, int inteligencia) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.mana = mana;
        this.manaMaxima = mana;
        this.forca = forca;
        this.destreza = destreza;
        this.inteligencia = inteligencia;
        this.efeitosAtivos = new ArrayList<>();
        this.random = new Random();
    }
    
    public abstract boolean podeUsarArma(Arma arma);
    public abstract void aplicarPassiva();
    
    public void equiparArma(Arma arma) {
        if (podeUsarArma(arma)) {
            this.armaEquipada = arma;
            System.out.println(nome + " equipou " + arma.getNome());
        } else {
            System.out.println(nome + " não pode usar " + arma.getNome());
        }
    }
    
    public void atacar(Personagem alvo) {
        if (armaEquipada == null) {
            System.out.println(nome + " não tem arma equipada!");
            return;
        }
        
        if (mana < armaEquipada.getCustoMana()) {
            System.out.println(nome + " não tem mana suficiente para usar " + armaEquipada.getNome());
            return;
        }
        
        for (StatusEffect efeito : efeitosAtivos) {
            if (efeito instanceof Atordoado && efeito.estaAtivo()) {
                System.out.println(nome + " está atordoado e não pode atacar!");
                efeito.reduzirDuracao();
                return;
            }
        }
        
        aplicarPassiva();
        mana -= armaEquipada.getCustoMana();
        if (mana < 0) mana = 0;
        
        ResultadoAtaque resultado = armaEquipada.atacar(this, alvo);
        int danoFinal = calcularDano(resultado.dano);
        alvo.receberDano(danoFinal);
        
        System.out.println(resultado.mensagem);
        
        if (resultado.efeito != null) {
            alvo.adicionarEfeito(resultado.efeito);
        }
    }
    
    public void receberDano(int dano) {
        int danoFinal = (int)(dano * (1 - getReducaoDano()));
        vida -= danoFinal;
        if (vida < 0) vida = 0;
        
        System.out.println(nome + " recebe " + danoFinal + " de dano! Vida: " + vida + "/" + vidaMaxima);
    }
    
    public void adicionarEfeito(StatusEffect efeito) {
        efeitosAtivos.add(efeito);
        System.out.println(nome + " sofreu " + efeito.getNome() + "!");
    }
    
    public void processarEfeitos() {
        List<StatusEffect> efeitosParaRemover = new ArrayList<>();
        
        for (StatusEffect efeito : efeitosAtivos) {
            efeito.aplicarEfeito(this);
            efeito.reduzirDuracao();
            
            if (!efeito.estaAtivo()) {
                efeitosParaRemover.add(efeito);
                System.out.println(nome + " não está mais " + efeito.getNome().toLowerCase() + "!");
            }
        }
        
        efeitosAtivos.removeAll(efeitosParaRemover);
    }
    
    protected int calcularDano(int danoBase) {
        if (random.nextDouble() < 0.1) {
            System.out.println("Acerto crítico!");
            return (int)(danoBase * 1.5);
        }
        return danoBase;
    }
    
    protected double getReducaoDano() {
        return 0.0;
    }
    
    // Getters
    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public int getMana() { return mana; }
    public int getForca() { return forca; }
    public int getDestreza() { return destreza; }
    public int getInteligencia() { return inteligencia; }
    public Arma getArmaEquipada() { return armaEquipada; }
    public boolean estaVivo() { return vida > 0; }
    
    public void restaurarMana(int quantidade) {
        mana += quantidade;
        if (mana > manaMaxima) mana = manaMaxima;
    }
}