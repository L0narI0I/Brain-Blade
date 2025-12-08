import extensions.File;
class Main extends Program {

    final String CLEAR = "\033[H\033[2J";
    String currentMonster = "";
    Player player = newPlayer(); 
    
//---------------Constructeur---------------//
    
    Monstre newMonstre(int HP, int def, int degat, int speed, String type){
        Monstre newMonster = new Monstre();
        newMonster.HPmax = HP;
        newMonster.HPcurrent = HP;
        newMonster.defense = def;
        newMonster.dps = degat;
        newMonster.vitesse = speed;
        newMonster.categorie = type;
        return newMonster;
    }

    Player newPlayer(){
        Player newPlayer = new Player();
    } 


//---------------Fonction Utilitaires---------------//
    
    void afficherAsciiArt(String name_file){
        File file = newFile("/home/infoetu/arthur.calande.etu/ijava2/BB/Assets/"+name_file+".txt");
        while (ready(file)){
            println(readLine(file));
        }
    }

//---------------Fonction de combat---------------//
    int playerAttack(Player player, Monstre monstre){
        double rd = random(0.85,1.15);
        double dmg = dmgxbuffDmg/(Monster.defense*Monstre.buffDef)*(rd);
    }

//---------------Fonction de Quizz---------------//

    void algorithm() {
        print(CLEAR);
        afficherAsciiArt("MainScreen");
        while (true){
            
        }
    }
}
