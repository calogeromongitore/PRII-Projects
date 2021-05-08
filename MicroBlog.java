import java.util.*;

public class MicroBlog implements SocialNetwork{

   private Map<String, Set<String>> social;
   private Map<Post,ArrayList<Post>> like;
   private Map<String ,Integer> count_like;
   private Integer unique_ID;

   public MicroBlog(){
       this.social = new HashMap<>();
       this.like = new HashMap<>();
       this.count_like = new HashMap<>();
       this.unique_ID = 0;
   }

    @Override
    public Map<String, Set<String>> guessFollowers(List<Post> ps) throws NullPointerException, EmptyListException {
      if (ps == null) throw new NullPointerException ();
      if (ps.isEmpty()) throw new EmptyListException ();
      Map<String, Set<String>> sub_social = new HashMap<>();
      ArrayList<Post> likes = new ArrayList<>();
      // estraggo da ogni post ps la lista di persone che hanno messo like
        // e da questa costruisco la rete sociale
        if (!likes.isEmpty()) {
            for (Post p : ps) {
                likes = like.get(p);
                ArrayList<String> utenti = new ArrayList<>();
                // estraggo gli autori del like
                for (Post p_like : likes) {
                    utenti.add(p_like.get_author());
                }
                //aggiungo la lista degli autori nel sub_social
                for (String s : utenti) {
                    sub_social.putIfAbsent(s, social.get(s));
                }
            }
        }
      return sub_social;
    }

    @Override
    public List<String> influencers() {

       // lista degli utenti più influencers
       ArrayList<String> result_infl = new ArrayList<>();
        //utilizzo la Map.Entry per manipolare facilmente i contenuti all'interno della Map
        //entrySet restiruisce un set generico contenenti tutti gli elementi di count_like
        //inizializzo la mia sorted_infl con count_like.entrySet()
        List<Map.Entry<String,Integer>> sorted_infl = new ArrayList<>(count_like.entrySet());

        //ordino gli utenti in ordine crescente di followers
        sorted_infl.sort(Map.Entry.comparingByValue());
        for(int i = sorted_infl.size()-1; i>=0; i--){
            result_infl.add(sorted_infl.get(i).getKey());
        }

        return result_infl;
    }

    @Override
    public Set<String> getMentionedUsers() {

       TreeSet<String> menzionati = new TreeSet<>();
       for (Post p : like.keySet()){
           if(!menzionati.contains(p.get_author())){
               menzionati.add(p.get_author());
           }
       }
       return menzionati;
    }

    @Override
    public Set<String> getMentionedUsers(List<Post> ps) throws NullPointerException {
       if (ps == null) throw new NullPointerException();

        TreeSet<String> menzionati = new TreeSet<>();
        for (Post p : like.keySet()){
            if(!menzionati.contains(p.get_author()) && ps.contains(p)){
                menzionati.add(p.get_author());
            }
        }
        return menzionati;

    }

    @Override
    public List<Post> writtenBy(String username) throws NullPointerException {

        if (username == null) throw new NullPointerException();

        ArrayList<Post> lista_post = new ArrayList<>();
        for(Post p : like.keySet()){
            if(p.get_author().equals(username)){
                lista_post.add(p);
            }
        }
        return lista_post;
    }

    @Override
    public List<Post> writtenBy(List<Post> ps, String username) throws NullPointerException, EmptyListException {

       if(username == null || ps == null) throw new NullPointerException();
       if(ps.isEmpty()) throw new EmptyListException();
        ArrayList<Post> lista_post = new ArrayList<>();
        for(Post p : ps){
            if(p.get_author().equals(username)){
                lista_post.add(p);
            }
        }
        return lista_post;

    }

    @Override
    public List<Post> containing(List<String> words) throws NullPointerException {

       if(words == null) throw new NullPointerException();

       ArrayList<Post> selected = new ArrayList<>();
       for (Post p : like.keySet()){
           for (String s : words) {
               if (p.get_text().contains(s)){
                   selected.add(p);
                   break;
               }
           }
       }
       return selected;
    }

    public void addPost(Post p) throws NullPointerException, DuplicatePostException{

       if (p == null) throw new NullPointerException();

      for (Post post : like.keySet()){
          if(p.equals(post)) throw new DuplicatePostException();
      }

      // aggiunge il post p alla hashmap like
      like.putIfAbsent(p,new ArrayList<Post>());
      // aggiunge l'autore del post p alla hashmap del social se non è ancora presente
      social.putIfAbsent(p.get_author(), new TreeSet<String>());
      // aggiunge l'autore del  post p alla hashmap count_like se non è ancora presente
      count_like.putIfAbsent(p.get_author(), 0);

    }

    // autore è quello che mette il like e p.get_author è chi riceve il like
    public void addLike(Post p, String autore, String timestamp) throws DuplicateLikeException, TooLongException, IllegaLikeException {

       Post p_like = new Post(unique_ID++, autore,"like:"+p.get_id(), timestamp);

       if (p_like.get_text().length() > 140) throw new TooLongException();

         if(!like.isEmpty()) {
           for (Post ps : like.get(p)) {
               if (ps.get_author().equals(autore) && ps.get_text().equals(p_like.get_text()))
                   throw new DuplicateLikeException("non puoi mettere due volte like allo stesso post");
           }
           if (p.get_author().equals(autore)){
               throw new IllegaLikeException("non puoi mettere like a te stesso");
           }
       }
        // aggiungo il like p_like alla lista di like relativa al post p
        like.get(p).add(p_like);

       //se l'autore non ha ancora messo alcun like allora lo aggiungo al social ed aggiungo
        //l'utente a cui ho messo like al social di "autore"
        if(social.containsKey(autore) == false){
            social.put(autore, new TreeSet<String>());
        }

        // aggiungo "autore" al set relativo all'autore del post p
        social.get(autore).add(p.get_author());

        // incremento il numero di like dell'autore del post p
        count_like.replace(p.get_author(),count_like.get(p.get_author())+1);

    }

    //debug
   public void printSocial(){
       System.out.println(social);
   }
}
