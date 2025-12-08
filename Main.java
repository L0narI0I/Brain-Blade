import extensions.File;
import extensions.CSVFile;
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
        File file = newFile("~/ijava2/BB/Assets/"+name_file+".txt");
        while (ready(file)){
            println(readLine(file));
        }
    }

    String controleSaisie(String[] possibilite){
        String saisie;
        boolean valide = false;
        do{
            print("Entrez votre choix");
            saisie = readInt();
            for (int i=0 ; i<length(possibilite) ; i++){
                if(saisie == possibilite[i]){
                    saisie = true;
                }
            }
        }while (valide);
        return saisie;
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
