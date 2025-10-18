import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Batalha {
    private List<Personagem> jogadores;
    private List<Personagem> inimigos;
    private int turno;
    private Scanner scanner;
    
    public Batalha() {
        this.jogadores = new ArrayList<>();
        this.inimigos = new ArrayList<>();
        this.turno = 1;
        this.scanner = new Scanner(System.in);
    }
    
    public void adicionarJogador(Personagem jogador) {
        jogadores.add(jogador);
    }
    
    public void adicionarInimigo(Personagem inimigo) {
        inimigos.add(inimigo);
    }
    
    public void iniciarBatalha() {
        System.out.println("=== BATALHA INICIADA ===");
        
        while (!batalhaTerminou()) {
            System.out.println("\n--- Turno " + turno + " ---");
            
            // Turno dos jogadores
            for (Personagem jogador : jogadores) {
                if (jogador.estaVivo()) {
                    executarTurnoJogador(jogador);
                }
            }
            
            // Processar efeitos nos jogadores
            for (Personagem jogador : jogadores) {
                if (jogador.estaVivo()) {
                    jogador.processarEfeitos();
                }
            }
            
            // Turno dos inimigos (IA simples)
            for (Personagem inimigo : inimigos) {
                if (inimigo.estaVivo()) {
                    executarTurnoInimigo(inimigo);
                }
            }
            
            // Processar efeitos nos inimigos
            for (Personagem inimigo : inimigos) {
                if (inimigo.estaVivo()) {
                    inimigo.processarEfeitos();
                }
            }
            
            turno++;
            exibirStatus();
        }
        
        anunciarVencedor();
    }
    
    private void executarTurnoJogador(Personagem jogador) {
        System.out.println("\nTurno de " + jogador.getNome());
        System.out.println("1. Atacar");
        System.out.println("2. Trocar arma");
        System.out.println("3. Ver status");
        
        int escolha = scanner.nextInt();
        
        switch (escolha) {
            case 1:
                Personagem alvo = escolherAlvo();
                if (alvo != null) {
                    jogador.atacar(alvo);
                }
                break;
            case 2:
                trocarArma(jogador);
                break;
            case 3:
                exibirStatusDetalhado();
                executarTurnoJogador(jogador); // Volta para o menu
                break;
        }
    }
    
    private void executarTurnoInimigo(Personagem inimigo) {
        // IA simples: ataca um jogador aleatório (versão sem Streams)
        List<Personagem> alvosVivos = new ArrayList<>();
        
        for (Personagem jogador : jogadores) {
            if (jogador.estaVivo()) {
                alvosVivos.add(jogador);
            }
        }
        
        if (!alvosVivos.isEmpty()) {
            Personagem alvo = alvosVivos.get((int)(Math.random() * alvosVivos.size()));
            inimigo.atacar(alvo);
        }
    }
    
    private Personagem escolherAlvo() {
        System.out.println("Escolha um alvo:");
        for (int i = 0; i < inimigos.size(); i++) {
            Personagem inimigo = inimigos.get(i);
            String status = inimigo.estaVivo() ? 
                "Vida: " + inimigo.getVida() : "MORTO";
            System.out.println((i + 1) + ". " + inimigo.getNome() + " (" + status + ")");
        }
        
        int escolha = scanner.nextInt() - 1;
        if (escolha >= 0 && escolha < inimigos.size() && inimigos.get(escolha).estaVivo()) {
            return inimigos.get(escolha);
        }
        return null;
    }
    
    private void trocarArma(Personagem personagem) {
        System.out.println("Escolha uma arma:");
        Arma[] armas = {
            new EspadaLonga(), new ArcoElfico(), new CajadoArcano(),
            new MachadoDeGuerra(), new AdagaSombria()
        };
        
        for (int i = 0; i < armas.length; i++) {
            boolean podeUsar = personagem.podeUsarArma(armas[i]);
            String status = podeUsar ? "✓" : "✗";
            System.out.println((i + 1) + ". " + armas[i].getNome() + 
                " (Dano: " + armas[i].getDanoBase() + ", Mana: " + 
                armas[i].getCustoMana() + ") " + status);
        }
        
        int escolha = scanner.nextInt() - 1;
        if (escolha >= 0 && escolha < armas.length) {
            personagem.equiparArma(armas[escolha]);
        }
    }
    
    private boolean batalhaTerminou() {
        // Verifica se todos os jogadores estão mortos
        boolean todosJogadoresMortos = true;
        for (Personagem jogador : jogadores) {
            if (jogador.estaVivo()) {
                todosJogadoresMortos = false;
                break;
            }
        }
        
        // Verifica se todos os inimigos estão mortos
        boolean todosInimigosMortos = true;
        for (Personagem inimigo : inimigos) {
            if (inimigo.estaVivo()) {
                todosInimigosMortos = false;
                break;
            }
        }
        
        return todosJogadoresMortos || todosInimigosMortos;
    }
    
    private void exibirStatus() {
        System.out.println("\n=== STATUS DA BATALHA ===");
        System.out.println("Jogadores:");
        for (Personagem jogador : jogadores) {
            String status = jogador.estaVivo() ? 
                "Vida: " + jogador.getVida() + "/" + jogador.getVida() + 
                " Mana: " + jogador.getMana() : "MORTO";
            System.out.println("  " + jogador.getNome() + " - " + status);
        }
        
        System.out.println("Inimigos:");
        for (Personagem inimigo : inimigos) {
            String status = inimigo.estaVivo() ? 
                "Vida: " + inimigo.getVida() : "MORTO";
            System.out.println("  " + inimigo.getNome() + " - " + status);
        }
    }
    
    private void exibirStatusDetalhado() {
        System.out.println("\n=== STATUS DETALHADO ===");
        for (Personagem jogador : jogadores) {
            System.out.println(jogador.getNome() + ":");
            System.out.println("  Vida: " + jogador.getVida() + "/" + jogador.getVida());
            System.out.println("  Mana: " + jogador.getMana() + "/" + jogador.getMana());
            System.out.println("  Força: " + jogador.getForca());
            System.out.println("  Destreza: " + jogador.getDestreza());
            System.out.println("  Inteligência: " + jogador.getInteligencia());
            System.out.println("  Arma: " + 
                (jogador.getArmaEquipada() != null ? 
                 jogador.getArmaEquipada().getNome() : "Nenhuma"));
        }
    }
    
    private void anunciarVencedor() {
        boolean jogadoresVivos = false;
        for (Personagem jogador : jogadores) {
            if (jogador.estaVivo()) {
                jogadoresVivos = true;
                break;
            }
        }
        
        if (jogadoresVivos) {
            System.out.println("\n🎉 PARABÉNS! Os jogadores venceram a batalha!");
        } else {
            System.out.println("\n💀 DERROTA! Todos os jogadores foram derrotados!");
        }
    }
}