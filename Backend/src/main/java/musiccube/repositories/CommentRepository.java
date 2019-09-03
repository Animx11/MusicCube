package musiccube.repositories;

import musiccube.entities.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Integer>, PagingAndSortingRepository<Comment, Integer> {

    @Query("SELECT c FROM Comment c WHERE c.song.id = ?1")
    Iterable<Comment> findCommentsBySongId(int id);

    @Query("SELECT c FROM Comment c WHERE c.album.id = ?1")
    Iterable<Comment> findCommentsByAlbumId(int id);

    @Query("SELECT c FROM Comment c WHERE c.band.id = ?1")
    Iterable<Comment> findCommentsByBandId(int id);

    @Query("SELECT c FROM Comment c WHERE c.artist.id = ?1")
    Iterable<Comment> findCommentsByArtistId(int id);



    @Query("SELECT c FROM Comment c WHERE c.user.userName like ?1")
    Iterable<Comment> findAllUserComments(String userName);

}
