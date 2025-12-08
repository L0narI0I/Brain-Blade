import extensions.File;
class Main extends Program {

    final String CLEAR = "\033[H\033[2J";

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

    void afficherAsciiArt(String name_file){
        File file = newFile("/home/infoetu/arthur.calande.etu/ijava2/BB/Assets/"+name_file+".txt");
        while (ready(file)){
            println(readLine(file));
        }
    }

    void algorithm() {
        print(CLEAR);
        afficherAsciiArt("MainScreen");
        while (true){
            
        }
    }
}