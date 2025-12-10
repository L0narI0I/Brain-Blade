import extensions.File;
import extensions.CSVFile;
class Main extends Program {

    final String CLEAR = "\033[H\033[2J";
    String currentMonster = "";
    Player player = newPlayer(); 
    
//---------------Constructeur de Classe---------------//
    
    Monstre newMonstre(int HP, int def, int degat, int speed, String type){
        Monstre newMonster = new Monstre();
        newMonster.HPmax = HP;
        newMonster.HPcurrent = HP;
        newMonster.def = def;
        newMonster.dmg = degat;
        newMonster.vitesse = speed;
        newMonster.categorie = type;
        return newMonster;
    }

    Player newPlayer(){
        Player newPlayer = new Player();
        return newPlayer;
    } 


//---------------Fonction Utilitaires---------------//
    
    void afficherAsciiArt(String name_file){
        File file = newFile("./Assets/"+name_file);
        while (ready(file)){
            println(readLine(file));
        }
    }    

    void afficherStat(Player player){
        println("Vos Statistiques : ");
        println("   - Point de Vie : " + player.HPcurrent + "/" + player.HPmax);
        println("   - Degat : "+player.dmg + "  x Buffs Degat : " +player.buffDmg);
        println("   - Defense : "+player.def + "  x Buffs Defense : " +player.buffDef);
        println("   - Vitesse : "+player.vitesse);
        println("   - Taux Coup Critique : x"+player.txCrit + " Degats Critique : " +player.degCrit);
    }

    void afficherStat(Monstre monstre){
        println("Ces Statistiques : ");
        println("   - Point de Vie : " + monstre.HPcurrent + "/" + monstre.HPmax);
        println("   - Degat : "+monstre.dmg + "  x Buffs Degat : " +monstre.buffDmg);
        println("   - Defense : "+monstre.def + "  x Buffs Defense : " +monstre.buffDef);
        println("   - Vitesse : "+monstre.vitesse);
        println("   - Taux Coup Critique : x"+monstre.txCrit + " Degats Critique : " +monstre.degCrit);
    }

    String controleSaisie(String[] possibilite){
        String saisie = "";
        boolean valide = false;
        do{
            print(">>>  ");
            saisie = readString();
            for (int i=0 ; i<length(possibilite) ; i++){
                if(equals(saisie , possibilite[i])){
                    valide = true;
                }
            }
        }while (!valide);
        return saisie;
    }

//---------------Fonction de combat---------------//
    void playerAttack(Player player, Monstre monstre){
        double crit = random(0,1);
        double rd = random(85,115) / 100;
        if (crit < player.txCrit){
            rd += 2;
        }
        double dmg = (player.dmg*player.buffDmg)/(monstre.def*monstre.buffDef)*rd;
    }

    void damage(Player player){
    }

    void damage(Monstre monstre){
    }

    void afficherAnnonceCombat(Player player, Monstre monstre){
        println("Votre personnage : ");
        afficherAsciiArt("Character.txt");
        afficherStat(player);
        afficherAsciiArt("VS.txt");
        afficherAsciiArt("monstre1.txt");
        afficherStat(monstre);
    }

//---------------Fonction de Quizz---------------//

//---------------Boucle Principale---------------//
    void algorithm() {
        print(CLEAR);
        afficherAsciiArt("MainScreen.txt");
        Player player = newPlayer();
        Monstre monstre1 = newMonstre(20,10,20,15,"Volant");
        String choixmenu = controleSaisie(new String[]{"1","2","3"});
        if(equals(choixmenu,"1")){
            afficherAnnonceCombat(player,monstre1);
        }
    }
}
