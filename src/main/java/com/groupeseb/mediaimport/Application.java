package com.groupeseb.mediaimport;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

/*
Lecture du fichier csv contenant les techniques (utilisation de CSV2Bean)
Création des ResourceMedia
Création des Media avec appel common-api (si video, télécharger le thumbnail et ajouter son url)
récupération des techniques
Maj des techniques
Envoi des techniques à la common-api
 */
	}
}
