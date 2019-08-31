package musiccube.services.comment;


import musiccube.entities.Comment;
import musiccube.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Optional<Comment> getById(int id) {
        return commentRepository.findById(id);
    }

    @Override
    public Iterable<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void delete(int id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Iterable<Comment> getCommentsBySongId(int id) {
        return commentRepository.findCommentsBySongId(id);
    }

    @Override
    public Iterable<Comment> getCommentsByAlbumId(int id) {
        return commentRepository.findCommentsByAlbumId(id);
    }

    @Override
    public Iterable<Comment> getCommentsByBandId(int id) {
        return commentRepository.findCommentsByBandId(id);
    }

    @Override
    public Iterable<Comment> getCommentsByArtistId(int id) {
        return commentRepository.findCommentsByArtistId(id);
    }

    @Override
    public Iterable<Comment> getAllUserComments(String userName) {
        return commentRepository.findAllUserComments(userName);
    }
}
