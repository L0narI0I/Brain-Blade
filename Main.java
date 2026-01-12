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
        newMonstre.buffDmg = 1.0;
        newMonstre.buffDef = 1.0;
        return newMonstre;
    }

    Player newPlayer(){
        Player newPlayer = new Player();
        return newPlayer;
    }

//---------------Fonction Utilitaires------------//

    String formater(String texte, int largeur) {
        String resultat = texte;
        while (length(resultat) < largeur) {
            resultat = resultat + " ";
        }
        return resultat;
    }

    void toStringPlayer(Player player){
        afficherAsciiArt("Character.txt");
        println("Vos Points de vie : " + player.HPcurrent + "/" + player.HPmax + "  Vos Degat : " + (player.dmg * player.buffDmg));
    }

    void toStringMonstre(Monstre monstre){
        afficherAsciiArt(monstre.fichier);
        println("Points de vie : " + monstre.HPcurrent + "/" + monstre.HPmax + "  Degat : " + (monstre.dmg * monstre.buffDmg));
    }
    
    void afficherAsciiArt(String name_file){
        File file = newFile("./Assets/" + name_file);
        while (ready(file)){
            println(readLine(file));
        }
    }

    void afficherStat(Player player){
        println("");
        println("Vos Statistiques : ");
        println("   - Point de Vie : " + player.HPcurrent + "/" + player.HPmax);
        println("   - Degat : " + player.dmg + "  x Buffs Degat : " + player.buffDmg);
        println("   - Defense : " + player.def + "  x Buffs Defense : " + player.buffDef);
        println("   - Vitesse : " + player.vitesse);
        println("   - Taux Coup Critique : x" + player.txCrit + " Degats Critique : " + player.degCrit);
        println("");
    }

    void afficherStat(Monstre monstre){
        println("");
        println("Ses Statistiques : ");
        println("   - Point de Vie : " + monstre.HPcurrent + "/" + monstre.HPmax);
        println("   - Degat : " + monstre.dmg + "  x Buffs Degat : " + monstre.buffDmg);
        println("   - Defense : " + monstre.def + "  x Buffs Defense : " + monstre.buffDef);
        println("   - Vitesse : " + monstre.vitesse);
        println("   - Taux Coup Critique : x" + monstre.txCrit + " Degats Critique : " + monstre.degCrit);
        println("");
    }   

    String controleSaisie(String[] possibilite, String suplement){
        String saisie = "";
        boolean valide = false;
        print(suplement + ">>> ");
        saisie = readString();
        for (int i = 0; i < length(possibilite); i++){
            if (equals(saisie, possibilite[i])){
                valide = true;
            }
        }
        if (!valide) {
            return "ERREUR_SAISIE";
        }
        return saisie;
    }


    int toInt (String s){
        int n =0;
        for (int i = 0; i < length(s); i++){
            if (s.charAt(i) < '0' || s.charAt(i) > '9'){
                return -1;
            }else{
                n = n * 10 + (s.charAt(i) - '0');
            }
        }
        return n;
    }

    void afficherBarreVie(String nom, int actuel, int max) {
        int taille = 20;
        double pourcentage = (max > 0) ? (double) actuel / max : 0;
        int nb = (int)(pourcentage * taille);
        if (nb < 0) nb = 0;
        print(formater(nom, 10) + " [");
        for (int i = 0; i < taille; i++) {
            if (i < nb) print("█");
            else print("░");
        }
        println("] " + actuel + "/" + max + " HP" + "\n");
    }

//---------------Fonction de combat--------------//
    
    void playerAttack(Player player, Monstre monstre){
        double rd = (random() * 30 + 85) / 100.0;
        double multiplier = (random() < player.txCrit) ? player.degCrit : 1.0;
        if (multiplier > 1.0) println("COUP CRITIQUE !");
        int dmg = (int)(((player.dmg * player.buffDmg) / (monstre.def / 5.0 + 1)) * rd * multiplier);
        damage(monstre, (dmg < 1 ? 1 : dmg));
    }

    void monstreAttack(Monstre monstre, Player player){
        double rd = (random() * 30 + 85) / 100.0;
        int dmg = (int)(((monstre.dmg * monstre.buffDmg) / (player.def / 5.0 + 1)) * rd);
        damage(player, (dmg < 1 ? 1 : dmg));
    }

    void damage(Player player, double amount){
        player.HPcurrent -= (int)amount;
        println("Vous recevez " + amount + " dégâts !");
    }

    void damage(Monstre monstre, double amount){
        monstre.HPcurrent -= (int)amount;
        println("Le monstre reçoit " + amount + " dégâts !");
    }

    boolean estKO(Monstre monstre){
        return monstre.HPcurrent <= 0;
    }

    boolean estKO(Player player){
        return player.HPcurrent <= 0;
    }

    void afficherAnnonceCombat(Player player, Monstre monstre){
        File file_character = newFile("./Assets/Character.txt");
        File file_VS = newFile("./Assets/VS.txt");
        File file_monstre = newFile("./Assets/" + monstre.fichier);
        while (ready(file_character) && ready(file_VS) && ready(file_monstre)){
            String ligne = formater(readLine(file_character), 35) + formater(readLine(file_VS), 20) + readLine(file_monstre);
            println(ligne);
        }
    }

    void tourDeCombat(Player p, Monstre m) {
        String s = controleSaisie(new String[]{"1", "2"}, "Action (1:Attaque, 2:Stats)");
        if (equals(s, "1")) {
            playerAttack(p, m);
            if (!estKO(m)) {
                sleep(600);
                monstreAttack(m, p);
            }
            sleep(1200);
        } else if (equals(s, "2")) {
            afficherStat(p);
            print("Entrée pour continuer...");
            readString();
        }
    }

    Monstre monstreAleatoire(){
        int rd = random(0,4);
        CSVFile montres = loadCSV("monstres.csv", ',');
        return newMonstre(toInt(getCell(montres, rd, 1)),
                           toInt(getCell(montres, rd, 2)),
                           toInt(getCell(montres, rd, 3)),
                           toInt(getCell(montres, rd, 4)),
                           toInt(getCell(montres, rd, 5)));

    boolean executionCombat(Player player, int score){
        Monstre monstre = monstreAleatoire();
        print(CLEAR);
        afficherAsciiArt("StartCombat.txt");
        sleep(1200);
        while (!estKO(player) && !estKO(monstre)) {
            print(CLEAR);
            afficherAnnonceCombat(player, monstre);
            println("");
            afficherBarreVie("JOUEUR", player.HPcurrent, player.HPmax);
            afficherBarreVie("MONSTRE", monstre.HPcurrent, monstre.HPmax);
            println("");
            tourDeCombat(player, monstre);
        }
        return !estKO(player);
    }


    void buffStatsJoueur(Player j, String diff){
        difficulty = toInt(diff);
        String[] stats = new String[]{"HPmax", "Soin", "Dégats", "Défense", "Vitesse", "Taux Critique", "Dégats Critique"};
        String choix = controleSaisie(stats, "Choisissez une statistique à améliorer : ");
        if (equals(choix, "HP")){
            j.HPmax += difficulty * 3;
            j.HPcurrent += difficulty * 3;
        } else if (equals(choix, "Soin")){
            j.HPcurrent += difficulty * 5;
            if (j.HPcurrent > j.HPmax){
                j.HPcurrent = j.HPmax;
            }
        } else if (equals(choix, "Dégats")){
            j.dmg += difficulty * 2;
        } else if (equals(choix, "Défense")){
            j.def += difficulty * 2;
        } else if (equals(choix, "Vitesse")){
            j.vitesse += difficulty * 1;
        } else if (equals(choix, "Taux Critique")){
            j.txCrit += 0.05 * difficulty;
        } else if (equals(choix, "Dégats Critique")){
            j.degCrit += 0.1 * difficulty;
        }


//---------------Fonction de Quizz---------------//

    void preparerEcranQuiz() {
        print(CLEAR);
        afficherAsciiArt("StartQuizz.txt");
        sleep(1000);
    }

    void afficherQuestionCadree(String texte, String diff) {
        String contenu = " QUESTION : " + texte + " ";
        int largeur = length(contenu) + 2;
        if (largeur < 40) { 
            largeur = 40; 
        }
        println("\nDIFFICULTÉ : " + diff);
        print("+");
        for (int i = 0; i < largeur - 2; i++) { 
            print("-"); 
        }
        println("+");
        println("|" + formater(contenu, largeur - 2) + "|");
        print("+");
        for (int i = 0; i < largeur - 2; i++) { 
            print("-"); 
        }
        println("+");
    }

    void afficherOptionsQuiz(String a1, String a2, String a3, String a4) {
        println("\n1 : " + a1 + " | 2 : " + a2);
        println("3 : " + a3 + " | 4 : " + a4);
        println("");
    }

    String saisirReponseValide(Question q) {
        String[] choix = new String[]{"1", "2", "3", "4"};
        String rep = "ERREUR_SAISIE";
        while (equals(rep, "ERREUR_SAISIE")) {
            rep = controleSaisie(choix, "Réponse");
            if (equals(rep, "ERREUR_SAISIE")) {
                print(CLEAR);
                afficherAsciiArt("StartQuizz.txt");
                afficherQuestionCadree(q.question, q.difficulty);
                afficherOptionsQuiz(q.answer1, q.answer2, q.answer3, q.answer4);
                println("Choix invalide, veuillez entrer un chiffre entre 1 et 4.");
            }
        }
        return rep;
    }

    boolean poserQuestionEtVerifier(Question q) {
        afficherQuestionCadree(q.question, q.difficulty);
        afficherOptionsQuiz(q.answer1, q.answer2, q.answer3, q.answer4);
        String reponse = saisirReponseValide(q);
        return equals(reponse, q.correctAnswer);
    }

    void appliquerConsequencesQuiz(Player p, boolean reussite, String diff, String solution) {
        if (reussite) {
            println("\n*** BRAVO ! BONNE RÉPONSE ! ***");
            buffStatsJoueur(p, diff);
        } else {
            println("\n--- DOMMAGE, MAUVAISE RÉPONSE ---");
            println("La réponse était la : " + solution);
        }
    }

    boolean executionQuestion(Player player) {
        preparerEcranQuiz();
        Question q = newQuestionRandom();
        boolean estCorrect = poserQuestionEtVerifier(q);
        appliquerConsequencesQuiz(player, estCorrect, q.difficulty, q.correctAnswer);
        sleep(2000);
        return estCorrect;
    }

    Question newQuestionRandom(){
        CSVFile tab = loadCSV("questions.csv", ',');
        int rd = (int)(random() * (rowCount(tab) - 1)) + 1;
        Question q = new Question();
        q.question = getCell(tab, rd, 0);
        q.answer1 = getCell(tab, rd, 1);
        q.answer2 = getCell(tab, rd, 2);
        q.answer3 = getCell(tab, rd, 3);
        q.answer4 = getCell(tab, rd, 4);
        q.correctAnswer = getCell(tab, rd, 5);
        q.difficulty = getCell(tab, rd, 6);
        return q;
    }

//---------------Boucle Principale---------------//

    void lancerPartie() {
        int score = 0;
        boolean vivant = true;
        while(vivant) {
            vivant = executionCombat(player, score);
            if (vivant) {
                score++;
                executionQuestion(player);
            }
        }
        println("\nPARTIE TERMINÉE. Score final : " + score);
        sleep(3000);
    }

    void algorithm() {
        boolean continuer = true;
        while(continuer) {
            print(CLEAR);
            afficherAsciiArt("MainScreen.txt");
            String choix = controleSaisie(new String[]{"1","2","3"}, "");
            if (equals(choix, "1")) {
                lancerPartie();
            } else if (equals(choix, "2")) {
                println("\nRÈGLES : Enchaînez les combats et répondez aux quiz pour survivre !");
                readString();
            } else if (equals(choix, "3")) {
                continuer = false;
            }
        }
    }
}
