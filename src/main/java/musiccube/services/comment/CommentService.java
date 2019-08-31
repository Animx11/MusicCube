package musiccube.services.comment;

import musiccube.entities.Comment;

import java.util.Optional;

public interface CommentService {

    Optional<Comment> getById(int id);
    Iterable<Comment> getAll();
    Comment save(Comment comment);
    void delete(int id);

    Iterable<Comment> getCommentsBySongId(int id);
    Iterable<Comment> getCommentsByAlbumId(int id);
    Iterable<Comment> getCommentsByBandId(int id);
    Iterable<Comment> getCommentsByArtistId(int id);


    Iterable<Comment> getAllUserComments(String userName);

}
