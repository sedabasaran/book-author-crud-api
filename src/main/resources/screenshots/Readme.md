# 📚 Book-Author CRUD API

Java & Spring Boot ile geliştirilmiş bu proje, kitaplar ve yazarlar arasında ilişki kurarak, 
her iki varlık üzerinde de kapsamlı (Create, Read, Update, Delete) işlemleri destekleyen RESTful bir API sunar.

## 🚀 Özellikler

- ✅ Kitap ve yazar CRUD işlemleri
- ✅ Yazar-kitap ilişkisi (Many-to-One)
- ✅ DTO kullanımı ve veri dönüşümleri (MapStruct)
- ✅ Spring Validation ile giriş doğrulama
- ✅ Sayfalama ve sıralama desteği
- ✅ Unit Test (Mockito)
- ✅ Integration Test (MockMvc)


## 🛠️ Kullanılan Teknolojiler

| Teknoloji      | Açıklama                        |
|----------------|---------------------------------|
| Java 17        | Ana programlama dili            |
| Spring Boot    | Uygulama çatısı                 |
| Spring Data JPA| ORM (veritabanı işlemleri)      |
| PostgreSQL     | Veritabanı                      |
| Lombok         | Boilerplate kod azaltma         |
| MapStruct      | DTO ↔ Entity dönüşümleri        |
| JUnit & Mockito| Test çatısı                     |
| Maven          | Proje yönetimi ve bağımlılıklar |


📁 Projede Neler Var?
✅ 1. Entity'ler
* Author ve Book entity’leri oluşturuldu.
* @ManyToOne ilişkisi kuruldu (Bir yazarın birden fazla kitabı olabilir).
✅ 2. DTO Katmanı
* Dışarıya verilecek veri şekli için BookDto ve AuthorDto yazıldı.
✅ 3. MapStruct ile Dönüşümler
* BookMapper, AuthorMapper ile DTO ↔ Entity dönüşümleri otomatik hale getirildi.
✅ 4. Service & Impl Katmanı
* BookService, AuthorService interface’leri ve implementasyonları yazıldı.
* CRUD işlemleri servis katmanında gerçekleştirildi.
✅ 5. Controller Katmanı
* REST endpoint'ler oluşturuldu:
    * GET /api/books, POST /api/books, PUT /api/books/{id}, DELETE /api/books/{id}
    * Aynısı Author için de yapıldı.
✅ 6. Validasyon
* DTO'lara @NotBlank, @Size, @NotNull gibi bean validation anotasyonları eklendi.
* Hatalı isteklerde 400 Bad Request dönülüyor.
✅ 7. Pagination & Sorting
* Kitapları sayfalı ve sıralı listeleme (/api/books?page=0&size=5&sortBy=title&sortDir=asc)
✅ 8. Unit Test (JUnit + Mockito)
* BookServiceImpl için mock testler yazıldı.
* Mockito ile repository ve mapper bağımlılıkları taklit edildi.
✅ 9. Integration Test (MockMvc)
* BookController ve AuthorController için gerçek HTTP request simülasyonları yapıldı.
* Doğru yanıtlar, validasyon hataları ve status code'lar test edildi.

📬 API Örnekleri
* GET /api/books – Tüm kitapları getirir 
* POST /api/books – Yeni kitap ekler
* PUT /api/books/{id} – Var olan kitabı günceller
* DELETE /api/books/{id} – Kitap siler
* GET /api/books?page=0&size=5&sortBy=authorId&sortDir=asc - Kitap pageable

🚀 Nasıl Çalıştırılır?
bash
KopyalaDüzenle
# Projeyi klonla
git clone https://github.com/sedabasaran/book-author-crud-api.git

# Maven ile çalıştır
cd book-author-crud-api
mvn clean install
mvn spring-boot:run
Veritabanı: PostgreSQL (application.properties içinde bağlantı ayarları var)

🧪 Test Çalıştırma
bash
# Tüm testleri çalıştır
mvn test


### 📸 Postman API Testleri

** Kitap Ekleme **  

file:///Users/sedabasaran/Downloads/book-author-crud-api/src/main/resources/screenshots/post-book.png

** Yazarları Listeleme **  

file:///Users/sedabasaran/Downloads/book-author-crud-api/src/main/resources/screenshots/get-authors.png

** Yazarları Listeleme **  

file:///Users/sedabasaran/Downloads/book-author-crud-api/src/main/resources/screenshots/get-books.png

** Kitapları Listeleme (Pageable ile) **  

file:///Users/sedabasaran/Downloads/book-author-crud-api/src/main/resources/screenshots/pageable.png

** Veritabanı Kitap Kayıtları **  

file:///Users/sedabasaran/Downloads/book-author-crud-api/src/main/resources/screenshots/postgre-books.png


Eğer bu projeyi beğendiysen ⭐ bırakmayı unutma 😊 Her türlü öneri ve katkıya açığım!






