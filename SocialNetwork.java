import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SocialNetwork {
    // Overview: Tipo di dato astratto modificabile,
    /*
         // Overview: Tipo di dato astratto modificabile,che rappresenta un'insieme di utent
	// e di posts
    /*
        typical element :<users,posts>
		users=<{ ( utente_0, followers_0), ... ,( utente_n, followers_n) }>
		utente_i=<id_utente>
        followers_i = {utente_0, ..., utente_m} con i!=j comunque preso 1<j<m
		posts=<{ (post_0,likeposts_0,countlikes_0), ... , (utente_l, likeposts_l,contlikes_l) }>
		post_i=<id_post,nomeautore,testo,timestamp>

        AF(this):<social.keyset && like.keyset>
           Comunque preso K ∈ social {keySet social.get(k)[i] = f_i | 1<= i <= social.get(k).size
            Comunque preso K ∈ like {keySet like.get(k)[i] = likepost_i | 1<= i <= like.get(k).size
             Comunque preso K ∈ count_like {keySet count_like.get(k)[i] = count_i && count_i ∈ N| 1<= i <= count_like.get(k).size




        RI(this):social!=null && like!=null && count_like!=null &&
		//social.get(name)!=null && like.gest(post)!=null && count_like!=null

     */

    /*
        restituisce la rete sociale derivata dalla lista di post
     */
    public Map<String, Set<String>> guessFollowers(List<Post> ps) throws NullPointerException, EmptyListException;
    /*
        REQUIRES: ps != null && ps != Empty
        THROWS: se ps == null lancia una NullPointerException oppure
        se ps == Empty lancia una EmptyListException
        EFFECTS: restituisce la lista associata alla rete sociale
     */

    /*
        restituisce gli utenti più influenti della rete sociale, cioè quelli che hanno più follower
     */
    public List<String> influencers();
    /*
        EFFECT: restituisce gli utenti con più follower
     */

    /*
        restituisce l'insieme degli utenti menzionati (quelli inclusi all'interno di un post)
        nella rete sociale
     */
    public Set<String> getMentionedUsers();
    /*
        EFFECT: restituisce l'insieme degli utenti menzionati
     */

    /*
        restituisce l'insieme degli utenti menzionati (quelli inclusi all'interno di un post)
        nella lista di post
     */
    public Set<String> getMentionedUsers(List<Post> ps) throws NullPointerException;
    /*
        REQUIRES: ps != null
        THROWS: se ps == null lancia una NullPointerException
        EFFECT: dopo la chiamata restituisce gli utenti menzionati nella lista di post
     */

    /*
        restituisce la lista dei post effettuati dall'utente nella rete sociale il cui nome
        è dato dal parametro username
     */
    public List<Post> writtenBy(String username) throws NullPointerException;
    /*
        REQUIRES: username != null
        THROWS: se username == null lancia una NullPointerException
        EFFECT: resituisce la lista dei post effettuati dall'utente
     */

    /*
        restituisce la lista dei post effettuati dall'utente il cui nome è dato
        dal parametro username presente nella lista ps
     */
    public  List<Post> writtenBy(List<Post> ps, String username) throws NullPointerException, EmptyListException;
    /*
        REQUIRES: ps != null && ps != Empty && username != null
        THROWS: se ps == null || ps == Empty lancia una NullPointerException
                se username == null lancia una NullPointerException
        EFFECT: restituisce la lista dei post effettuati dall'utente
     */

    /*
        restituisce la lista dei post presenti nella rete sociale che includono almeno
        una delle parole presenti nella lista delle parole argomento del metodo
     */
    public List<Post> containing(List<String> words) throws NullPointerException;
    /*
        REQUIRES: words != null
        THROWS: se words == null lancia una NullPointerException
        EFFECT: restituisce la lista dei post che inclusono almeno una delle parole presenti nella
        lista delle parole;
     */

    /*
        aggiunge altri post nella rete sociale
     */
    public void addPost(Post p) throws NullPointerException, DuplicatePostException;
    /*
        REQUIRES: p != null && = <= i < p.size.() ==> p != p.get(i)
        THROWS: NUllPointerExcepiton (uncheked) if store == null
                DuplicatePostExceptions (checked) if p è già in posts
        EFFECT: aggiunge p alla lista dei post della rete
     */
}
