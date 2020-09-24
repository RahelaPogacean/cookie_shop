package client;

import model.Comment;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Comment comment = new Comment("Ana", "dobos", "delicios");
        CommentService commentService = new CommentService();
        commentService.addComment("Ana", "dobos", "delicios");

        System.out.println(comment);
    }
}
