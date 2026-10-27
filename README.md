# 🌤️ Weather API

Spring Boot 3 və OpenWeatherMap API inteqrasiyası ilə hazırlanmış cari hava haqqı məlumatı xidməti. Layihə istifadəçinin daxil etdiyi şəhər adına əsasən real vaxt rejimində hava şəraiti məlumatlarını qaytarır və Frontend inteqrasiyası üçün tam hazır vəziyyətdədir.

---

## 🛠️ Texnologiyalar

- **Java:** 17
- **Framework:** Spring Boot 3.x (Spring WebMVC)
- **HTTP Client:** `RestClient` (OpenWeatherMap inteqrasiyası üçün)
- **Sənədləşmə (Documentation):** OpenAPI 3 / Swagger UI (`springdoc-openapi`)
- **Layihə İdarəetməsi:** Apache Maven
- **Konfiqurasiya:** CORS dəstəyi təmin olunub

---

##  Quraşdırma və İşə Salınma

### 1. Tələblər (Prerequisites)
- **JDK 17** və ya daha yuxarı versiya
- **Maven** 3.x
- **OpenWeatherMap API Key** (Pulsuz API açarı)

### 2. Mühit Dəyişənləri (.env)
Layihənin kök qovluğunda `.env` faylı yaradın və öz OpenWeatherMap API açarınızı əlavə edin:

```properties
OPENWEATHER_API_KEY=sizin_openweather_api_açarınız
