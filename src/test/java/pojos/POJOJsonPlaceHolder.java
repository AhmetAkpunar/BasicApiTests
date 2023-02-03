package pojos;

public class POJOJsonPlaceHolder {

    /*
         {
         "title":"Ahmet",
         "body":"Merhaba",
         "userId":10,
         "id":70
         }
     */

    // 1- Adım : Objemizin içerisinde var olan tüm key değerlerini private variable yapalım

    private String title;
    private String body;
    private int id;
    private int userId;

    // 2- Adım : Getter ve Setter hazırla

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    // 3. Adım Tüm parametleri içeren constructor oluşturalım

    public POJOJsonPlaceHolder(String title, String body, int id, int userId) {
        this.title = title;
        this.body = body;
        this.id = id;
        this.userId = userId;
    }

    // 4. Adım : Parametresiz Constructor create et

    public POJOJsonPlaceHolder(){};

    // 5. Adım : toString() metodu create et


    @Override
    public String toString() {
        return "POJOJsonPlaceHolder{" +
                       "title='" + title + '\'' +
                       ", body='" + body + '\'' +
                       ", id=" + id +
                       ", userId=" + userId +
                       '}';
    }
}
