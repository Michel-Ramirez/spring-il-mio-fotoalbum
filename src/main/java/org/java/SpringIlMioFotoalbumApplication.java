package org.java;

import org.java.auth.config.AuthConfig;
import org.java.auth.db.pojo.Role;
import org.java.auth.db.pojo.User;
import org.java.auth.db.serv.RoleService;
import org.java.auth.db.serv.UserService;
import org.java.db.pojo.Category;
import org.java.db.pojo.Message;
import org.java.db.pojo.Picture;
import org.java.db.serv.CategoryService;
import org.java.db.serv.MessageService;
import org.java.db.serv.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner {

	@Autowired
	private PictureService pictureService;

	@Autowired
	private CategoryService catServ;

	@Autowired
	private MessageService messageServ;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		Role role1 = new Role("ADMIN");
		Role role2 = new Role("SUPERADMIN");

		roleService.save(role1);
		roleService.save(role2);

		String psw = AuthConfig.passwordEncoder().encode("psw");

		User user1 = new User("Michel", "Ramirez", "mich", "michel.ramirez@gmail.com", psw);

		userService.save(user1);

		Category cat1 = new Category("Ritratti");
		Category cat2 = new Category("Natura morta");
		Category cat3 = new Category("Paesaggi");
		Category cat4 = new Category("Fotografia di viaggio");
		Category cat5 = new Category("Fotogiornalismo");
		Category cat6 = new Category("Architettura");
		Category cat7 = new Category("Street photography");
		Category cat8 = new Category("Fotografia concettuale");
		Category cat9 = new Category("Fotografia sportiva");
		Category cat10 = new Category("Fotografia astratta");

		catServ.save(cat1);
		catServ.save(cat2);
		catServ.save(cat3);
		catServ.save(cat4);
		catServ.save(cat5);
		catServ.save(cat6);
		catServ.save(cat7);
		catServ.save(cat8);
		catServ.save(cat9);
		catServ.save(cat10);

		Picture foto1 = new Picture("Bliss",
				"Una foto di un paesaggio collinare verde con un cielo azzurro senza nuvole, diventata famosa come sfondo predefinito di Windows XP.",
				"https://www.resetdigitale.it/img/cms/5de0dcc3d868f064786593.jpg", false, cat3, cat5);
		Picture foto2 = new Picture("Lunch atop a Skyscraper",
				"Immagine di undici operai seduti su una trave durante la costruzione del Rockefeller Center a New York, mangiando il pranzo sospesi in aria.",
				"https://www.fotografidigitali.it/i/n/usa_2_720.jpg", true, cat9, cat5);
		Picture foto3 = new Picture("The Afghan Girl",
				"Ritratto di una giovane ragazza afgana con occhi verdi intensi, scattato da Steve McCurry nel campo profughi di Nasir Bagh durante l'occupazione sovietica.",
				"https://static.alfemminile.com/images/2023/04/28/221021833-f91521c1-7ee9-45f7-b518-316cb0d287e8.jpg",
				true, cat8);
		Picture foto4 = new Picture("Migrant Mother",
				"Fotografia iconica della Grande Depressione negli Stati Uniti, ritrae una madre con i suoi figli in un campo di lavoratori.",
				"https://www.superprof.it/blog/wp-content/uploads/2019/04/steve-mccurry-quando-un-clickdiventa-unopera-darte_e4ec7252-c9ea-11e5-8fc4-aa18047137c1_900_566.jpg",
				true, cat10);
		Picture foto5 = new Picture("V-J Day in Times Square",
				"Immagine di un bacio appassionato tra un marinaio e un'infermiera alla fine della Seconda Guerra Mondiale.",
				"https://cdn.catawiki.net/assets/marketing/uploads-files/46881-a1b23282e88b18c0f713b0ddd2cf2e6785c19267-story_inline_image.jpg",
				true, cat10, cat3);
		Picture foto6 = new Picture("Raising the Flag on Iwo Jima",
				"Foto che mostra sei marines americani alzar la bandiera americana sul Monte Suribachi durante la battaglia di Iwo Jima.",
				"https://www.reflex-mania.com/wp-content/uploads/2018/06/Schermata-2018-06-08-alle-13.51.35.jpg", true,
				cat6);
		Picture foto7 = new Picture("The Falling Man",
				"Una delle immagini più controverse dell'11 settembre, ritrae un uomo cadente dalla Torre Nord del World Trade Center durante gli attacchi terroristici.",
				"https://qph.cf2.quoracdn.net/main-qimg-da9da48ed4e1b6e19412eab690e0c141-lq", true, cat6, cat5);
		Picture foto8 = new Picture("Tank Man",
				"Un uomo sconosciuto che si scontra con una colonna di carri armati durante le proteste sulla piazza Tienanmen a Pechino nel 1989.",
				"https://www.weshoot.it/blog/wp-content/uploads/2018/02/0000000.jpg", true, cat7);
		Picture foto9 = new Picture("Napalm Girl",
				"Rappresenta una bambina vietnamita che corre nuda e urlante dopo essere stata colpita da un attacco al napalm durante la guerra del Vietnam.",
				"https://www.lasecondaguerramondiale.org/images/foto_seconda_guerra_mondiale_2.jpg", true, cat8, cat4);
		Picture foto10 = new Picture("The Steerage",
				"Fotografia di Alfred Stieglitz che mostra i passeggeri della terza classe e della prima classe di una nave diretta in Europa.",
				"https://www.unicusano.it/blog/wp-content/uploads/2018/02/citazioni-famose-della-storia-1280x720.jpg",
				true, cat1, cat4);

		pictureService.save(foto1);
		pictureService.save(foto2);
		pictureService.save(foto3);
		pictureService.save(foto4);
		pictureService.save(foto5);
		pictureService.save(foto6);
		pictureService.save(foto7);
		pictureService.save(foto8);
		pictureService.save(foto9);
		pictureService.save(foto10);

		Message msg1 = new Message("Marco Rossi", "marco.rossi@example.com",
				"Buongiorno, mi presento come nuovo membro della nostra azienda!");
		Message msg2 = new Message("Sofia Bianchi", "sofia.bianchi@example.com",
				"Sono entusiasta di iniziare questa nuova avventura con voi.");
		Message msg3 = new Message("Luca Ferrari", "luca.ferrari@example.com",
				"Non vedo l'ora di contribuire al nostro team e crescere insieme.");
		Message msg4 = new Message("Martina Esposito", "martina.esposito@example.com",
				"Sono qui per aiutarvi in tutto ciò di cui avete bisogno.");
		Message msg5 = new Message("Alessio Romano", "alessio.romano@example.com",
				"Desidero ringraziarvi per questa opportunità e per il supporto.");
		Message msg6 = new Message("Chiara Moretti", "chiara.moretti@example.com",
				"Mi impegnerò al massimo per raggiungere gli obiettivi della nostra azienda.");
		Message msg7 = new Message("Davide Russo", "davide.russo@example.com",
				"Sono desideroso di imparare e sviluppare le mie competenze per contribuire al successo del team.");
		Message msg8 = new Message("Federica Conti", "federica.conti@example.com",
				"Non vedo l'ora di conoscere tutti voi e creare un ambiente di lavoro collaborativo.");
		Message msg9 = new Message("Simone Marchetti", "simone.marchetti@example.com",
				"Se avete suggerimenti o consigli da condividere, sarò più che felice di ascoltarvi.");
		Message msg10 = new Message("Valentina Colombo", "valentina.colombo@example.com",
				"Grazie ancora per l'accoglienza calorosa. Non vedo l'ora di lavorare insieme a voi!");

		messageServ.save(msg1);
		messageServ.save(msg2);
		messageServ.save(msg3);
		messageServ.save(msg4);
		messageServ.save(msg5);
		messageServ.save(msg6);
		messageServ.save(msg7);
		messageServ.save(msg8);
		messageServ.save(msg9);
		messageServ.save(msg10);
	}

}
