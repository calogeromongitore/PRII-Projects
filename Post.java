public class Post implements PostInterface{

    private Integer id;
    private String author;
    private String text;
    private String timestamp;

    public Post(Integer id, String author, String text, String timestamp) throws NullPointerException, TooLongException{
        if(id == null || author == null || timestamp == null) throw new NullPointerException();
        if(text.length() >= 140) throw new TooLongException("Il post è troppo lungo");

        this.author = author;
        this.id = id;
        this.text = text;
        this.timestamp = timestamp;
    }

    public String get_author(){
        return author;
    }

    public Integer get_id(){
        return id;
    }

    public String get_text(){
        return text;
    }

    public String getTimestamp(){
        return timestamp;
    }

    public boolean equals (Post p) {
        if (p.get_id()== this.id) return true;
        else return false;
    }
    public void display (){
        System.out.println(author+ " "+ id+ " "+text+ " "+timestamp);
    }

}
