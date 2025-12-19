import extensions.File;
import extensions.CSVFile;
class Main extends Program {

    final String CLEAR = "\033[H\033[2J";
    String currentMonster = "";
    Player player = newPlayer();

    
//---------------Constructeur de Classe---------------//

    Monstre newMonstre(int HP, int def, int degat, int speed, String fichier){
        Monstre newMonstre = new Monstre();
        newMonstre.HPmax = HP;
        newMonstre.HPcurrent = HP;
        newMonstre.def = def;
        newMonstre.dmg = degat;
        newMonstre.vitesse = speed;
        newMonstre.fichier = fichier;
        return newMonstre;
    }

    Player newPlayer(){
        Player newPlayer = new Player();
        return newPlayer;
    } 

//---------------Fonction Utilitaires------------//

    void toString(Player player){
        afficherAsciiArt("Character.txt");
        println("Vos Points de vie : " + player.HPcurrent + "/" + player.HPmax + "  Vos Degat : " + player.dmg*player.buffDmg);
    }

    void toString(Monstre monstre){
        afficherAsciiArt(monstre.fichier);
        println("Points de vie : " + monstre.HPcurrent + "/" + monstre.HPmax + "  Degat : " + monstre.dmg*monstre.buffDmg);
    }
    
    void afficherAsciiArt(String name_file){
        File file = newFile("./Assets/"+name_file);
        while (ready(file)){
            println(readLine(file));
        }
    }

    void afficherStat(Player player){
        println("");
        println("Vos Statistiques : ");
        println("   - Point de Vie : " + player.HPcurrent + "/" + player.HPmax);
        println("   - Degat : "+player.dmg + "  x Buffs Degat : " +player.buffDmg);
        println("   - Defense : "+player.def + "  x Buffs Defense : " +player.buffDef);
        println("   - Vitesse : "+player.vitesse);
        println("   - Taux Coup Critique : x"+player.txCrit + " Degats Critique : " +player.degCrit);
        println("");
    }

    void afficherStat(Monstre monstre){
        println("");
        println("Ses Statistiques : ");
        println("   - Point de Vie : " + monstre.HPcurrent + "/" + monstre.HPmax);
        println("   - Degat : "+monstre.dmg + "  x Buffs Degat : " +monstre.buffDmg);
        println("   - Defense : "+monstre.def + "  x Buffs Defense : " +monstre.buffDef);
        println("   - Vitesse : "+monstre.vitesse);
        println("   - Taux Coup Critique : x"+monstre.txCrit + " Degats Critique : " +monstre.degCrit);
        println("");
    }   

    String controleSaisie(String[] possibilite, String suplement){
        String saisie = "";
        boolean valide = false;
        do{
            print(suplement+">>> ");
            saisie = readString();
            for (int i=0 ; i<length(possibilite) ; i++){
                if(equals(saisie , possibilite[i])){
                    valide = true;
                }
            }
        }while (!valide);
        println("");
        return saisie;
    }

//---------------Fonction de combat--------------//
    
    void playerAttack(Player player, Monstre monstre){
        double crit = random();
        double rd = random(85,115);
        rd = rd/100;
        if ( crit < player.txCrit){
            println("VOUS INFLIGER UN COUP CRITIQUE !!!");
            rd += 2;
        }
        double dmg =(int)((player.dmg*player.buffDmg)/(monstre.def*monstre.buffDef) * rd * 5) + 1;
        damage(monstre, dmg);
        println("PV du monstre restants"+monstre.HPcurrent);
    }

    void monstreAttack(Monstre monstre, Player player){
        double crit = random();
        double rd = random(85,115);
        rd = rd/100;
        println(""+crit);
        if ( crit < monstre.txCrit){
            println("Le monstre se decahine et vous inflige un COUP CRITIQUE !");
            rd += 2;
        }
        double dmg =(int)((monstre.dmg*monstre.buffDmg)/(player.def*player.buffDef) * rd * 5) + 1;
        damage(player, dmg);
        println("PV du joueurs restants"+monstre.HPcurrent);
    }

    void damage(Player player, double amount){
        player.HPcurrent -= amount;
        println("Dégâts reçus : "+amount);
    }

    void damage(Monstre monstre, double amount){
        monstre.HPcurrent -= amount;
        println("Dégâts infligés : "+amount);
    }

    boolean estKO(Monstre monstre){
        return monstre.HPcurrent <= 0;
    }

    boolean estKO(Player player){
        return player.HPcurrent <= 0;
    }

    void afficherAnnonceCombat(Player player, Monstre monstre){
        println("");
        println("Votre personnage : ");
        afficherAsciiArt("Character.txt");
        afficherStat(player);
        afficherAsciiArt("VS.txt");
        afficherAsciiArt("monstre1.txt");
        afficherStat(monstre);
        println("");
    }

    boolean executionCombat(Player player, int score){
        Monstre monstre = newMonstre(20,10,20,15,"monstre1.txt");
        afficherAsciiArt(StartCombat.txt);
        do{
            toString(player);
            String s = controleSaisie(new String[]{"1"} , "Votre action ? (1 : Attaquer) ");
            if(equals(s,"1")){
                println("Vous attaquez le monstre !");
                playerAttack(player,monstre);
                println("\n"+"Le monstre riposte !");
                monsterAttack(monstre1,player);
            }
        }while (!estKO(monstre) && !estKO(player));
        if (estKO(monstre)){
            println("Vous avez mis le monstre KO");
            println("Votre score : " + score);
            return true;
        }else{
            println("Le monstre vous a battu, FIN DE LA PARTIE");
            return false; 
        }
    }

//---------------Fonction de Quizz---------------//

    int executionQuestion(player) {
        afficherAsciiArt(StartQuizz.txt);
        
        Question q = newQuestionRandom();
        if(afficherQuestion(q) == true){
            return q.difficulty;
        }else{
            return 0;
        }
    }

    Question newQuestionRandom(){
        
        LoadCSV(question.csv,',');
        Question q = new Question();
        int rd = random(1,20);

        q.question = getCell(question.csv, rd, 0);

        q.answer1 = getCell(question.csv, rd, 1);
        q.answer2 = getCell(question.csv, rd, 2);
        q.answer3 = getCell(question.csv, rd, 3);
        q.answer4 = getCell(question.csv, rd, 4);

        q.correctAnswer = getCell(question.csv, rd, 5);

        q.difficulty = getCell(question.csv, rd, 6);

        return q;
    }

    boolean afficherQuestion(Question q){
        String[] reponsesPossibles = new String[1,2,3,4];
        println("QUESTION :")
        println(q.question);
        println("");

        println("1 : " + q.answer1);
        println("2 : " + q.answer2);
        println("3 : " + q.answer3);
        println("4 : " + q.answer4);

        String reponse = controleSaisie(reponsesPossibles,"Veuillez entrer votre réponse ");
        if(reponse = q.correctAnswer){
            return true;
        }else{
            return false;
        }
    }

//-------Gestion des statistiques monstres-------//

//   --> Ici toutes les fonctions pour les
//       les stats des montres qui évoluront.


    Monstre monstreBaseStats(String type){
        Monstre m = newMonstre();
        for(int i = 1; i<columnCount())
    }




//---------------Boucle Principale---------------//
    void algorithm() {
        boolean enJeu = true;
        print(CLEAR);
        afficherAsciiArt("MainScreen.txt");
        Player player = newPlayer();
        String choixmenu = controleSaisie(new String[]{"1","2","3"}, "");
        if(equals(choixmenu,"1"));
            while(enJeu){
                enJeu = executionCombat(player);
                print(CLEAR);
                enJeu = executionQuestion(player);
            }
        }
        }else if(equals(choixmenu,"2")){
            afficherAnnonceCombat(player,monstre1);
        }
    }
}
