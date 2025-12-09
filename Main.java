Cimport extensions.File;
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
    
    String toStringAsciiArt(String name_file){
        File file = newFile("~/ijava2/BB/Assets/"+name_file+".txt");
        String img;
        while (ready(file)){
            img += readLine(file) + "\n";
        }
        return img;
    }

    String toStringMultiAsciiArt(String name_file1, String name_file2){
        File file1 = newFile("~/ijava2/BB/Assets/"+name_file1+".txt");
        File file2 = newFile("~/ijava2/BB/Assets/"+name_file2+".txt");
        String img;
        while (ready(file1) && ready(file2)) {
            img += readLine(file1) + "\t" + readLine(file2) + "\n";
        }
        return img;
    }      

    String controleSaisie(String[] possibilite){
        String saisie;
        boolean valide = false;
        do{
            print("Entrez votre choix");
            saisie = readString();
            for (int i=0 ; i<length(possibilite) ; i++){
                if(saisie == possibilite[i]){
                    saisie = true;
                }
            }
        }while (!valide);
        return saisie;
    }

//---------------Fonction de combat---------------//
    int playerAttack(Player player, Monstre monstre){
        double crit = random(0,1);
        double rd = random(0.85,1.15);
        if (crit < player.txCrit){
            rd += 2;
        }
        double dmg = dmgxbuffDmg/(Monster.defense*Monstre.buffDef)*(rd);
    }

    int damage(Player player){
    }

    int damege(Monstre monstre){
    }

//---------------Fonction de Quizz---------------//

//---------------Boucle Principale---------------//

    void algorithm() {
        print(CLEAR);
        printt(toStringrAsciiArt("MainScreen"));
        while (true){
            
        }
    }
}
