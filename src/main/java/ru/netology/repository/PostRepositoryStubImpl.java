package ru.netology.repository;

import ru.netology.model.Post;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepositoryStubImpl implements PostRepository {
	private final List<Post> repo;

	private static AtomicLong nextId = new AtomicLong();

	public PostRepositoryStubImpl() {
		this.repo = new CopyOnWriteArrayList<>();
	}

	private long getNextId() {
		return nextId.incrementAndGet();
	}

	public List<Post> all() {
		return repo;
	}

	public Optional<Post> getById(long id) {
		Post result = null;
		for (Post post : repo) {
			if (post.getId() == id) {
				result = post;
			}
		}
		return Optional.ofNullable(result);
	}

	public Post save(Post post) {
		long id = post.getId();
		if (id == 0) {
			id = getNextId();
			post.setId(id);
			repo.add(post);
		} else {
			repo.add(post);
		}
		return post;
	}

	public void removeById(long id) {
		for (Post post : repo) {
			if (post.getId() == id) {
				repo.remove(post);
			}
		}
	}
}
