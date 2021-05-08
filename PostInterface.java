import java.util.Date;

public interface PostInterface {
    // Overview: Tipo di dato non modificabile;
    // Typical Element:
    // AF(c): funzione identità f(x) = x;
    // id = id.get(c);
    //text = text.get(c);
    //RI(c): c.author != null && c.id != null && c.text

    /*
        restituisce il valore associato all'autore del post;
     */
     public String get_author();
     /*
        EFFECTS: dopo l'invocazione l'autore viene restituito;
      */

    /*
       restituisce il valore associato all'id del post;
    */
    public Integer get_id();
    /*
        EFFECTS: dopo l'invocazione l'id viene restituito;
      */
    /*
        restituisce il valore associato al testo del post;
     */
    public String get_text();
    /*
        EFFECTS: dopo l'invocazione il testo del post viene restituito;
      */
    /*
        restituisce il valore associato alla data di pubblicazione del post;
     */
    public String getTimestamp();
    /*
        EFFECTS: dopo l'invocazione la data associata al post viene restituita;
      */
}
