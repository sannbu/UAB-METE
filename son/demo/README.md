  # Anonymous Messaging Application with Rooms

Basit bir anonim mesaj uygulaması. Kullanıcılar oda oluşturabilir, mesaj gönderebilir ve diğer kullanıcıların mesajlarını görüntüleyebilir.

## Özellikler

- **Oda Sistemi**: Kullanıcılar farklı konularda odalar oluşturabilir
- **Anonim Mesaj Gönderme**: Kullanıcılar kimlik bilgisi olmadan mesaj gönderebilir
- **Resim Yükleme**: Mesajlarla birlikte resim eklenebilir
- **Yanıt Sistemi**: Mesajlara yanıt verilebilir (reply)
- **Beğeni/Beğenmeme**: Mesajlara like/dislike verilebilir
- **Gerçek Zamanlı Güncelleme**: WebSocket ile anlık mesaj güncellemeleri
- **Mesaj Silme**: Mesajlar silinebilir

## API Endpoints

### Room Endpoints

#### 1. Tüm Odaları Getirme (GET)
```
GET /rooms
```

#### 2. Oda Oluşturma (POST)
```
POST /rooms
Content-Type: application/json

{
    "name": "firstroom",
    "description": "First room description"
}
```

#### 3. Oda Bilgilerini Getirme (GET)
```
GET /rooms/{roomName}
```

#### 4. Oda Güncelleme (PUT)
```
PUT /rooms/{id}
Content-Type: application/json

{
    "name": "updatedroom",
    "description": "Updated description"
}
```

#### 5. Oda Silme (DELETE)
```
DELETE /rooms/{id}
```

### Message Endpoints

#### 1. Mesaj Gönderme (POST)
```
POST /mess
Content-Type: application/json

{
    "content": "Merhaba dünya!",
    "roomName": "firstroom"
}
```

#### 2. Belirli Odada Mesaj Gönderme (POST)
```
POST /mess/{roomName}
Content-Type: application/json

{
    "content": "Merhaba dünya!",
    "replayId": 1,
    "imagePath": "image.jpg"
}
```

#### 3. Tüm Mesajları Getirme (GET)
```
GET /mess
```

#### 4. Odadaki Mesajları Getirme (GET)
```
GET /mess/{roomName}
```

#### 5. Belirli Bir Mesajı Getirme (GET)
```
GET /mess/{id}
```

#### 6. Mesaj Silme (DELETE)
```
DELETE /mess/{id}
```

#### 7. Mesaj Beğenme (POST)
```
POST /mess/like
Content-Type: application/json

{
    "id": 1
}
```

#### 8. Mesaj Beğenmeme (POST)
```
POST /mess/dislike
Content-Type: application/json

{
    "id": 1
}
```

## Web Arayüzü

### Sayfalar:
1. **`/rooms.html`** - Oda yönetimi sayfası
2. **`/display.html`** - Mesajları görüntüleme sayfası
3. **`/display.html?room=firstroom`** - Belirli odadaki mesajları görüntüleme
4. **`/send.html`** - Mesaj gönderme sayfası
5. **`/send.html?room=firstroom`** - Belirli odaya mesaj gönderme

## Çalıştırma

1. Projeyi derleyin:
```bash
mvn clean install
```

2. Uygulamayı çalıştırın:
```bash
mvn spring-boot:run
```

3. Uygulama http://localhost:8080 adresinde çalışacak

4. PostgreSQL veritabanını kontrol etmek için pgAdmin veya psql kullanabilirsiniz

## Veritabanı

Uygulama PostgreSQL veritabanı kullanır. Uygulamayı çalıştırmadan önce PostgreSQL'i kurmanız ve veritabanını oluşturmanız gerekir.

### PostgreSQL Kurulumu:

1. PostgreSQL'i indirin ve kurun: https://www.postgresql.org/download/
2. PostgreSQL'i başlatın
3. Veritabanını oluşturun:
```sql
CREATE DATABASE anonymous_messages;
```

### Veritabanı Ayarları:
- Host: localhost
- Port: 5432
- Database: postgres
- Username: postgres
- Password: mysecretpassword

Bu ayarları `application.yml` dosyasında değiştirebilirsiniz.

## Kullanım Örneği

1. **Oda Oluşturma**: `/rooms.html` sayfasından "firstroom" adında bir oda oluşturun
2. **Odaya Mesaj Gönderme**: `/send.html?room=firstroom` sayfasından mesaj gönderin
3. **Mesajları Görüntüleme**: `/display.html?room=firstroom` sayfasından mesajları görüntüleyin

## Test Örnekleri

### Oda Oluşturma:
```bash
curl -X POST http://localhost:8080/rooms \
  -H "Content-Type: application/json" \
  -d '{"name": "firstroom", "description": "First room"}'
```

### Odada Mesaj Gönderme:
```bash
curl -X POST http://localhost:8080/mess/firstroom \
  -H "Content-Type: application/json" \
  -d '{"content": "Merhaba!", "replayId": null, "imagePath": null}'
```

### Odadaki Mesajları Getirme:
```bash
curl http://localhost:8080/mess/firstroom
```

### Tüm Odaları Getirme:
```bash
curl http://localhost:8080/rooms
``` 