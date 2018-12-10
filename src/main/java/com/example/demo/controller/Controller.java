package com.example.demo.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.activation.DataSource;
import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.annotation.MultipartConfig;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entities.Product;
import com.example.demo.entities.ProductOrder;

import com.example.demo.entities.Recipe;
import com.example.demo.entities.StoreManager;
import com.example.demo.entities.Supplier;
import com.example.demo.entities.Aisle;
import com.example.demo.entities.Basket;
import com.example.demo.entities.Category;
import com.example.demo.entities.Competition;
import com.example.demo.entities.Customer;
import com.example.demo.entities.GeneratePdfOrder;
import com.example.demo.entities.Login;
import com.example.demo.entities.Payment;
import com.example.demo.entities.Person;
import com.example.demo.repository.AisleRepository;

import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.RecipeRepository;
import com.example.demo.service.EmailService;
import com.example.demo.service.OrderService;
import com.example.demo.service.PaymentService;
import com.example.demo.service.ProductService;
import com.example.demo.service.RecipeService;
import com.example.demo.service.StoreManagerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.itextpdf.text.pdf.PdfAppearance;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.sun.mail.handlers.message_rfc822;
import com.example.demo.service.AisleService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.CompetitionService;
import com.example.demo.service.CustomerService;

@RestController
@MultipartConfig(maxFileSize = 1024 * 1024 * 1024, maxRequestSize = 1024 * 1024 * 1024)
@CrossOrigin(origins = "http://localhost:3000")
public class Controller {

	@Autowired
	private CustomerService service;
	@Autowired
	private ProductService productService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private StoreManagerService storeManagerService;
	@Autowired
	private AisleService aisleService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private RecipeService recipeService;
	@Autowired
	private CompetitionService competitionService;
	
	 


	private Basket basket = new Basket();

	private List<Product> list = new ArrayList<Product>();

	private static String UPLOADED_FOLDER = "C://Users//User//Downloads//quickstart-master//src//app//menu//img//";

	
	

	//GET MAPPING
	
	//This sort product by save from big to small
@GetMapping("/SortBySave")
public List<Product> sort() {
	return productService.sort();
}

//This method will search fo product using product name and brand name
	 @GetMapping("/search/{search}")
	  public List<Product> search(@PathVariable String search) {
	    List searchResults = null;
	    
	   searchResults= productService.search(search);
	    return searchResults;
	  }
	
	//This will get all product where store quantity is less 20
	 @GetMapping("GetByStoreQuantity")
	 public List<Product> getBySoreQuantity(){
		 return productService.getByStoreQuantity();
	 }

//This method will get All Aisles
	@GetMapping("AllAisle")
	public List<Aisle> getAllAisle() {
		return aisleService.getAll();
	}

	//This method will get person by Id
	@GetMapping("/Register/{id}")
	public Person getPerson(@PathVariable long id) {

		return service.getById(id);
	}
	
	//This method will get All person
	@GetMapping("/Register")
	public List<Person> getAll() {
		return service.getAll();
	}

	//This will validate if the user exist by email
	@GetMapping("/Valid/{email}")
	public Person emailValidate(@PathVariable String email) {

		return service.validateUser(email);
	}

	//This method get all product where save is != 0
	@GetMapping("/GetBySave")
	public List<Product> getBysave() {
		return productService.getBySave();
	}

	//This method will get all product added to cart
	@GetMapping("/GetCart")
	public List<Product> getCart() {
		return basket.getAll();
	}

	//This method will remove one product from cart using id
	@GetMapping("/RemoveCart/{productId}")
	public int removeCart(@PathVariable int productId) {
		basket.removeProduct(productId);

		return basket.getAll().size();
	}

	//This method will calculate the total amount of all the product on the cart
	@GetMapping("/Total")
	public double getTotal() {
		double Totalprice = 0;

		for (int i = 0; i < basket.getAll().size(); ++i) {
			Totalprice += basket.getAll().get(i).getOriginPrice();
		}

		return Totalprice;
	}

	//This method will calculate the total save of all the product on the cart
	@GetMapping("/TotalPromotion")
	public double getTotalPro() {
		double pro = 0;
		for (int i = 0; i < basket.getAll().size(); ++i) {
			pro += basket.getAll().get(i).getSave();
		}

		return pro;

	}

	//This  method will get product from one recipe using recipeId
	@GetMapping("GetByRecipe/{recipeId}")
	public List<Product> getByRecipe(@PathVariable long recipeId) {
		return productService.getByRecipe(recipeId);
	}
//This method will get one recipe by recipeId
	@GetMapping("MyRecipe/{recipeId}")
	public Recipe getRecipe(@PathVariable long recipeId) {
		return recipeService.getRecipe(recipeId);
	}

	//This method will get all recipe from a category
	@GetMapping("RecipeByCategory/{category}")
	public List<Recipe> recipeByCategory(@PathVariable String category) {
		List<Recipe> recipes = new ArrayList<Recipe>();
		Recipe recipe = new Recipe();
		for (int i = 0; i < recipeService.getByCategory().size(); i++) {
			if (category.equals(recipeService.getByCategory().get(i).getCategory())) {
				recipe = recipeService.getByCategory().get(i);
				recipes.add(recipe);
			}
		}
		return recipes;

	}
	//This method will get all Competition
	@GetMapping("GetAllCompetition")
	public List<Competition> getAllCompetition(){
		return competitionService.getAllFind();
	}
	//This method will a specific competition by Id
	@GetMapping("GetById")
	public Competition getById(@PathVariable long competitionId){
		return competitionService.getById(competitionId);
	}
	
	//This method will get productOrder by id
	@GetMapping("pay/{id}")
	public ProductOrder pay(@PathVariable long id) {
		return orderService.getProductOrder(id);
	}

	//This method will get all productOrder
	@GetMapping("GetOrder")
	public List<ProductOrder> getOrder() {

		return orderService.getAllOrder();
	}
//This method will count number of product on cart
	@GetMapping("Count")
	public int count() {

		return basket.getAll().size();
	}
	//This method will get all product
	@GetMapping("/GetProduct")
	public List<Product> getAllProduct() {

		return productService.getAllProduct();
	}
//This will get product by productId
	@GetMapping("/GetProduct/{productId}")
	public Product getProduct(@PathVariable long productId) {

		return productService.getProduct(productId);
	}
//This method will get all category
	@GetMapping("/Category")
	public List<Category> getAllCategory() {
		return categoryService.getAllCategory();
	}
	
	//This method will get all product from a specific category by categoryId
	@GetMapping("/GetAll/{categoryId}")
	public List<Product> getAllProduct(@PathVariable long categoryId) {
		return productService.getAll(categoryId);
	}
//This method will get all category from a specific Aisle by aisleId
	@GetMapping("/GetAllAisle/{aisleId}")
	public List<Category> getAllCategoryByAisle(@PathVariable long aisleId) {
		return categoryService.getAllCategoryByAisle(aisleId);
	}
//This method will get a specific aisle by aisleId
	@GetMapping("GetAisleById/{aisleId}")
	public Aisle getAisle(@PathVariable long aisleId) {
		return aisleService.getById(aisleId);
	}
	
	//POST MAPPING
	
	//This method will save Aisle
	@PostMapping("Aisle")
	public String save(@RequestBody Aisle aisle) {
		String message = "";
		try {

			String file = "app/menu/img/";
			String image = aisle.getImage().substring(12);
			String banner = aisle.getBanner().substring(12);

			File files = new File("C://Users//User//Pictures//" + image);
			File myFile = new File("C://Users//User//Pictures//" + banner);

			BufferedImage bImage;
			BufferedImage mybImage;
			bImage = ImageIO.read(files);
			mybImage = ImageIO.read(myFile);

			ImageIO.write(bImage, "jpg", new File(UPLOADED_FOLDER + image));
			ImageIO.write(mybImage, "jpg", new File(UPLOADED_FOLDER + banner));

			aisle.setBanner(file + banner);
			aisle.setImage(file + image);

			aisleService.save(aisle);
			message = "Aisle Added";

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return message;

	}
	//This method will check if the user exist for login purpose
	@PostMapping("/login")
	public Person login(@RequestBody Login login) {
		Person person = null;
		person = service.login(login);
		return person;
	}
	//This method will save Recipe
	@PostMapping("Recipe")
	public void saveRecipe(@RequestBody Recipe recipe) throws IOException   {

		
		
			String file = "app/menu/img/";
			
			String rimage = recipe.getPhoto().substring(12);
			String rbanner = recipe.getBanner().substring(12);

			File files = new File("C://Users//User//Pictures//" + rimage);
			File myFile = new File("C://Users//User//Pictures//" + rbanner);

			BufferedImage rImage;
			BufferedImage mybImage;
			
			rImage = ImageIO.read(files);
			mybImage = ImageIO.read(myFile);
			
			ImageIO.write(rImage, "jpg", new File(UPLOADED_FOLDER + rimage));
			ImageIO.write(mybImage, "jpg", new File(UPLOADED_FOLDER + rbanner));

			recipe.setBanner(file + rbanner);
			recipe.setPhoto(file + rimage);
			
			
			List<Product> productLists = new ArrayList<Product>(recipe.getProduct());
			for (int i = 0; i < productLists.size(); i++) {
				
				Category category = categoryService.getCategory(productLists.get(i).getQuantity());
				productLists.get(i).setCategory(category);
				productLists.get(i).setRecipe(recipe);
				productLists.get(i).setQuantity(1);
				
				
				double originPrice = productLists.get(i).getOriginPrice();
				double save = productLists.get(i).getSave();
				double newPrice = productLists.get(i).calculateNewPrice(originPrice, save);
				productLists.get(i).setNewPrice(newPrice);
				String images = productLists.get(i).getImage().substring(12);
				File fil = new File("C://Users//User//Pictures//" + images);
				BufferedImage bImages;
				bImages = ImageIO.read(fil);
				ImageIO.write(bImages, "jpg", new File(UPLOADED_FOLDER + images));
				
				productLists.get(i).setImage(file+images);

			}
			
			
			
			Set product = new HashSet<Product>() {
				{

					addAll(productLists);
				}
			};

			recipe.setProduct(product);

			recipeService.save(new HashSet<Recipe>() {
				{
					add(recipe);

				}
			});

		

		
		
	}
	//This method will save Competition
	@PostMapping("Competition")
	public void saveCompetition(@RequestBody Competition competition) throws IOException {
		
		String file = "app/menu/img/";
		
		String rimage = competition.getPhoto().substring(12);
		String rbanner = competition.getBanner().substring(12);

		File files = new File("C://Users//User//Pictures//" + rimage);
		File myFile = new File("C://Users//User//Pictures//" + rbanner);

		BufferedImage rImage;
		BufferedImage mybImage;
		
		rImage = ImageIO.read(files);
		mybImage = ImageIO.read(myFile);
		
		ImageIO.write(rImage, "jpg", new File(UPLOADED_FOLDER + rimage));
		ImageIO.write(mybImage, "jpg", new File(UPLOADED_FOLDER + rbanner));

		competition.setBanner(file + rbanner);
		competition.setPhoto(file + rimage);
		
		List<Product> productLists = new ArrayList<Product>(competition.getProduct());
		for (int i = 0; i < productLists.size(); i++) {
			
			Category category = categoryService.getCategory(productLists.get(i).getQuantity());
			productLists.get(i).setCategory(category);
			productLists.get(i).setCompetition(competition);
			productLists.get(i).setQuantity(1);
			
			
			double originPrice = productLists.get(i).getOriginPrice();
			double save = productLists.get(i).getSave();
			double newPrice = productLists.get(i).calculateNewPrice(originPrice, save);
			productLists.get(i).setNewPrice(newPrice);
			String images = productLists.get(i).getImage().substring(12);
			File fil = new File("C://Users//User//Pictures//" + images);
			BufferedImage bImages;
			bImages = ImageIO.read(fil);
			ImageIO.write(bImages, "jpg", new File(UPLOADED_FOLDER + images));
			
			productLists.get(i).setImage(file+images);

		}
		
		
		
		Set product = new HashSet<Product>() {
			{

				addAll(productLists);
			}
		};

		competition.setProduct(product);

		competitionService.save(new HashSet<Competition>() {
			{
				add(competition);

			}
		});

	}

	//This method is used to add product quantity then update save,newPrice and originalPrice on cart
	@PostMapping("/addQauntity")
	public List<Product> addQauntity(@RequestBody Product product) {

		for (int i = 0; i < basket.getAll().size(); ++i) {
			if (product.getProductId() == basket.getAll().get(i).getProductId()) {
				basket.getAll().get(i).setNewPrice(product.getNewPrice());
				basket.getAll().get(i).setOriginPrice(product.getOriginPrice());
				basket.getAll().get(i).setQuantity(product.getQuantity());
				basket.getAll().get(i).setSave(product.getSave());
				break;
			}
		}

		return basket.getAll();
	}
//This method will save ProductOrder
	@PostMapping("Order/{id}")
	public ProductOrder saveOrder(@PathVariable long id, @RequestBody ProductOrder productOrder) {

		Customer customer = (Customer) service.getById(id);
		ProductOrder myOrder = new ProductOrder();
		myOrder.setCustomer(customer);
		myOrder.setAddress(productOrder.getAddress());
		myOrder.setAddressNickName(productOrder.getAddressNickName());
		myOrder.setApartment(productOrder.getApartment());
		myOrder.setDate(productOrder.getDate());
		myOrder.setMydefault(productOrder.isMydefault());
		orderService.save(myOrder);
		return myOrder;
	}

	//This method will send prove of payment to user and store manager will attached pdf file of cart vai email,save payment after payment have been processed
	@RequestMapping(value = "/payment/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_PDF_VALUE)
	public void citiesReport(@PathVariable long id, @RequestBody Payment payment)
			throws IOException, MessagingException, NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException {

		ByteArrayInputStream bis = GeneratePdfOrder.orderReport(basket.getAll());

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=orderReport.pdf");

		ResponseEntity<InputStreamResource> pdf = ResponseEntity.ok().headers(headers)
				.contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bis));

		Customer person = (Customer) service.getById(id);

		SimpleMailMessage registrationEmail = new SimpleMailMessage();

		String[] to = { person.getLogin().getEmail(), "bhekimuzibhembe@gmail.com" };
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		registrationEmail.setTo(to);
		registrationEmail.setSubject("Order Receipt");
		registrationEmail.setText("To confirm your e-mail address, please click the link below:\n");
		registrationEmail.setFrom("noreply@domain.com");

		// File files = new File("C://Users//User//Pictures//orderReport.pdf");
		ByteArrayDataSource attachment = new ByteArrayDataSource(bis, "application/pdf");
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;

		byte[] data = new byte[1024];
		while ((nRead = bis.read(data, 0, data.length)) != -1) {
			buffer.write(data, 0, nRead);
		}

		Field f = ByteArrayInputStream.class.getDeclaredField("buf");
		f.setAccessible(true);
		byte[] bufs = (byte[]) f.get(bis);

		Payment myPayment = new Payment();
		myPayment.setReceipt(bufs);

		myPayment.setCardHolderName(payment.getCardHolderName());
		myPayment.setCardNumber(payment.getCardNumber());
		myPayment.setCvc(payment.getCvc());
		myPayment.setValidDates(payment.getValidDates());
		ProductOrder productOrder = orderService.getProductOrder(id);
		myPayment.setProductOrder(productOrder);
		paymentService.save(myPayment);

		helper.addAttachment("OrderReceipt", attachment);
		// helper.addAttachment("Order",files );
		helper.setSubject(registrationEmail.getSubject());
		helper.setText("Dear " + person.getName() + "\n"
				+ "\n Thank you for purchasing with Pick n Pay. Please see below a summary of your purchase ");
		helper.setTo(registrationEmail.getTo());
		helper.setFrom("noreply@domain.com");

		mailSender.send(message);

	}
	//This method will product to cart
	@PostMapping("/AddToCart/")
	public int addToCart(@RequestBody Product product) {

		basket.addProduct(product);

		return basket.getAll().size();
	}
	
	
	//This method will save category
	@PostMapping("/Category/{aisleId}")
	public void addCategory(@PathVariable long aisleId, @RequestBody Category category) {

		try {
			String file = "app/menu/img/";
			String image = category.getImage().substring(12);

			File files = new File("C://Users//User//Pictures//" + image);
			BufferedImage bImage;
			bImage = ImageIO.read(files);
			ImageIO.write(bImage, "jpg", new File(UPLOADED_FOLDER + image));
			category.setImage(file + image);
			Aisle aisle = aisleService.getById(aisleId);
			category.setAisle(aisle);
			categoryService.saveCategory(category);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	//This method will save product
	@PostMapping("/Product/{categoryId}")
	public String addProduct(@PathVariable long categoryId, @RequestBody Product product) {
		String message = "";
		try {

			double originPrice = product.getOriginPrice();
			double save = product.getSave();
			double newPrice = product.calculateNewPrice(originPrice, save);
			String file = "app/menu/img/";
			String image = product.getImage().substring(12);

			File files = new File("C://Users//User//Pictures//" + image);
			BufferedImage bImage;
			bImage = ImageIO.read(files);
			ImageIO.write(bImage, "jpg", new File(UPLOADED_FOLDER + image));
			product.setImage(file + image);
			product.setNewPrice(newPrice);
			Category category = categoryService.getCategory(categoryId);
			product.setCategory(category);
			productService.saveProduct(product);
			message = "Product Added";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "Not Added";
		}
		return message;
	}
	//This method will save the store manager
	@PostMapping("/Manager")
	public String addStoreManager(@RequestBody StoreManager storeManager) {
		String message = "";
		StoreManager store = storeManagerService.validateStoreManager(storeManager.getLogin().getEmail());
		if (store != null) {
			message = "Store Manager Already exist";
		} else {
			storeManager.getLogin().setRole("StoreManager");
			storeManagerService.saveStoreManager(storeManager);
			message = "Registration  successfully";
		}
		return message;
	}
//This method will save a customer then send comfirmation email
	@PostMapping("/Register")
	public Person add(@RequestBody Customer person) throws IOException {
		Person rtPerson;
		Person myPerson = service.validateUser(person.getLogin().getEmail());
		if (myPerson != null) {
			rtPerson = null;
		} else {

			SimpleMailMessage registrationEmail = new SimpleMailMessage();
			registrationEmail.setTo(person.getLogin().getEmail());
			registrationEmail.setSubject("Registration Confirmation");
			registrationEmail.setText("To confirm your e-mail address, please click the link below:\n");
			registrationEmail.setFrom("noreply@domain.com");

			emailService.sendEmail(registrationEmail);
			person.getLogin().setRole("User");

			service.createAdmin(person);
			rtPerson = person;
		}

		return rtPerson;
	}
	
	//DELETE MAPPING
	

//This method will delete product by productId
	@DeleteMapping("DeleteProduct/{productId}")
	public String delete(@PathVariable long productId) {
		String message = "";

		Product product = productService.getProduct(productId);
		productService.delete(product);
		message = "Product has been Removed";

		return message;

	}
	
	
	//PUT MAPPING
//This method will update product by productId
	@PutMapping("UpdateProduct/{productId}")
	public String updateProduct(@PathVariable long productId, @RequestBody Product product) {
		String message = "";

		try {
			Product Myproduct = productService.getProduct(productId);
			String file = "app/menu/img/";
			String image = product.getImage().substring(12);

			File files = new File("C://Users//User//Pictures//" + image);
			BufferedImage bImage;
			bImage = ImageIO.read(files);
			ImageIO.write(bImage, "jpg", new File(UPLOADED_FOLDER + image));

			Myproduct.setImage(file + image);

			Myproduct.setProductName(product.getProductName());
			double originPrice = product.getOriginPrice();
			double save = product.getSave();
			double newPrice = product.calculateNewPrice(originPrice, save);
			Myproduct.setNewPrice(newPrice);
			Myproduct.setOriginPrice(product.getOriginPrice());
			Myproduct.setSave(product.getSave());
			Myproduct.setBrandName(product.getBrandName());
			productService.update(Myproduct);
			message = "Product Updated";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return message;
	}
//This method will update user details by userId
	@PutMapping("/Register/{id}")
	public void update(@RequestBody Person person, @PathVariable long id) {

		Person newObj = service.getById(id);

		newObj.getLogin().setEmail(person.getLogin().getEmail());
		newObj.setName(person.getName());
		newObj.setSurname(person.getSurname());
		newObj.getLogin().setPassword(person.getLogin().getPassword());
		newObj.getLogin().setRole(person.getLogin().getRole());
		newObj.getAddress().setCellNumber(person.getAddress().getCellNumber());
		newObj.getAddress().setCity(person.getAddress().getCity());
		newObj.getAddress().setPostalCode(person.getAddress().getPostalCode());
		newObj.getAddress().setProvince(person.getAddress().getProvince());
		newObj.getAddress().setStreetAddress(person.getAddress().getStreetAddress());
		newObj.getAddress().setSuburb(person.getAddress().getSuburb());
		service.update(newObj);
	}
	//After every payment this method will update store quantity
	@PutMapping("StoreQuantity")
	public void updateStoreQuantity() {
		List<Product> myList =basket.getAll();
		for(int i=0;i<myList.size();i++) {
			Product newObj =productService.getProduct(myList.get(i).getProductId());
			int quantity =myList.get(i).getQuantity();
			int store =myList.get(i).getStoreQuantity();
			int storeQuantity =store -quantity;
			newObj.setStoreQuantity(storeQuantity);
			productService.update(newObj);
		}
	}
}
