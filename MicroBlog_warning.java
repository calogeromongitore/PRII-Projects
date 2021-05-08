import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MicroBlog_warning  extends MicroBlog{

    private Map<Post, ArrayList<Post>> warning; // post + post che lo hanno segnalato
    private Integer unique_ID;

    public MicroBlog_warning (){
        super();
        this.warning = new HashMap<>();
        this.unique_ID =0;
    }

    public void warning (Post p, String autore, String timestamp) throws TooLongException, DuplicateWarningException, IllegalWarningException {
        Post p_warning = new Post (unique_ID++,autore,"warning: il testo all'interno del post:"+p.get_id()+ " " +"ha dei contenuti offensivi",timestamp);

        warning.putIfAbsent(p,new ArrayList<>());
        if (!warning.isEmpty()) {
            for (Post ps : warning.get(p)) {
                if (ps.get_author().equals(autore) && ps.get_text().equals(p_warning.get_text()))
                    throw new DuplicateWarningException("hai già segnalato questo post");
            }
            if (p.get_author().equals(autore)){
                throw new IllegalWarningException("non puoi segnalare te stesso");
            }
        }

        warning.get(p).add(p_warning);

    }

    public void print_warnings(){

        for(Post p : warning.keySet()){
            System.out.println();
            System.out.printf("il post ");
            p.display();
            System.out.printf("è stato segnalato da ");
            for (Post ps : warning.get(p)){
                System.out.printf(ps.get_author()+ " ");
            }
            System.out.println();
        }

    }


}
