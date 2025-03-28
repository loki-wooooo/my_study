package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.datajpa.entity.Item;

public interface ItemRepository extends JpaRepository<Item, String> {
}
