import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws TooLongException, DuplicatePostException, EmptyListException, DuplicateLikeException, DuplicateWarningException, IllegaLikeException, IllegalWarningException {

        //CREO DEI POST
        Post post0 = new Post(0,"calogero","hello,world","10/08/98");
        Post post1 = new Post(2,"gabriele","hello","01/03/05");
        Post post2 = new Post(3,"giacomo","Trump ha perso le elezioni","10/05/97");
        Post post3 = new Post(1,"lello","Biden ha vinto le elezioni","15/08/90");

        //CREO IL SOCIAL NETWORK ED AGGIUNGO I POST
            MicroBlog SocialNetwork = new MicroBlog();
            try{
                SocialNetwork.addPost(post0);
                SocialNetwork.addPost(post1);
                SocialNetwork.addPost(post2);
                SocialNetwork.addPost(post3);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }

            //AGGIUNGO DEI LIKE-->(FOLLOW) AD UN POST // controllare questo metodo se funziona bene
        try{
            SocialNetwork.addLike(post0,"calogero","10/08/98"); // non posso mettere like a me stesso
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        try{
            SocialNetwork.addLike(post0,"gabriele","10/08/98");
            SocialNetwork.addLike(post0,"gabriele","10/08/98"); // HO GIÀ MESSO LIKE QUINDI NON POSSO
            // AGGIUNGERLO DI NUOVO (DuplicateLikeException)
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        SocialNetwork.addLike(post2,"gabriele","01/09/09");



        // CREO LA LISTA DI POST DA PASSARE AL METODO GUESSFOLLOWERS
        List<Post> post = new ArrayList<>();
        post.add(post0);
        post.add(post1);
        post.add(post3);


        //CREO LA LISTA DI PAOROLE DA PASSARE AL METODO CONTAINING
        List<String> words = new ArrayList<>();
        words.add("ciao");
        words.add("hello");
        words.add("Trump");

     /*System.out.println("influenti:"+ SocialNetwork.influencers());


       System.out.println("menzionati1:"+  SocialNetwork.getMentionedUsers());

       System.out.println("menzionati2:"+ SocialNetwork.getMentionedUsers(post));
        SocialNetwork.printSocial();

       System.out.println("prova containing");
        for(Post p : SocialNetwork.containing(words)){
            p.display();
        }

        System.out.println("prova writtenby1");
        for(Post p : SocialNetwork.writtenBy("calo")){
            p.display();
        }

        System.out.println("prova writtenby2");
        for(Post p : SocialNetwork.writtenBy(post,"lello")){
            p.display();
        }*/

    //============================================================================================================================================================================================

        //CREO ESTENSIONE SOCIAL NETWORK PER GESTIRE I CONTENUTI OFFENSIVI
        // E SEGNALO ALCUNI POST COME OFFENSIVI
        MicroBlog_warning SocialNetwork2 = new MicroBlog_warning();

        SocialNetwork2.addPost(post0);
        SocialNetwork2.addPost(post1);
        SocialNetwork2.addPost(post2);


        SocialNetwork2.warning(post0,"giacomo","10/09/23");
        SocialNetwork2.warning(post0,"lello","10/09/23");
        SocialNetwork2.warning(post1,"calogero","10/09/02");
        try {
            SocialNetwork2.warning(post0,"calogero","10/08/00");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }


        SocialNetwork2.print_warnings();

    }
}
