import extensions.File;
import extensions.CSVFile;
class Main extends Program {

    final String CLEAR = "\033[H\033[2J";
    String currentMonster = "";
    Player player = newPlayer(); 
    
//---------------Constructeur de Classe---------------//
    
    Monster newMonster(int HP, int def, int degat, int speed, String type){
        Monster newMonster = new Monster();
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
        return newPlayer;
    } 


//---------------Fonction Utilitaires---------------//
    
    String toStringAsciiArt(String name_file){
        String img = "";
        File file = newFile("~/ijava2/BB/Assets/"+name_file+".txt");
        while (ready(file)){
            img += readLine(file) + "\n";
        }
        return img;
    }

    String toStringMultiAsciiArt(String name_file1, String name_file2){
        File file1 = newFile("~/ijava2/BB/Assets/"+name_file1+".txt");
        File file2 = newFile("~/ijava2/BB/Assets/"+name_file2+".txt");
        String img = "";
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
                    valide = true;
                }
            }
        }while (!valide);
        return saisie;
    }

//---------------Fonction de combat---------------//
    double playerAttack(Player player, Monster monster){
        
        double rd = random(80,115);
        rd = rd/100;
        //return (Player.dmg*Player.buffDmg)/(Monster.defense*Monster.buffDef)*(rd);
        return rd;
    }

//---------------Fonction de Quizz---------------//

//---------------Boucle Principale---------------//

    void algorithm() {
        print(CLEAR);
        print(toStringAsciiArt("MainScreen"));
        while (true){
            
        }
    }
}
