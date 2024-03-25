package mieten17.repositories;

import jakarta.transaction.Transactional;
import mieten17.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Transactional
    @Query(value = "select m.* from messages m " +
            "where (from_user_id = :toUserId and to_user_id = :fromUserId) " +
            "or (from_user_id = :fromUserId and to_user_id = :toUserId) " +
            "and obj_id = :objId",
            nativeQuery = true)
    public List<Message> getMsgToUsers(Long toUserId, Long fromUserId, Long objId);

    @Transactional
    @Query(value = "select m.*, (select id from users where id = " +
            "(case when m.from_user_id = :authId then m.to_user_id else m.from_user_id end)) " +
            "user_id, (select i.path from images i where obj_id = m.obj_id order by i.id limit 1) " +
            "path from messages m where m.id in (select max(m2.id) from messages m2 where :authId in " +
            "(from_user_id, to_user_id) group by " +
            "(case when from_user_id = :authId then to_user_id else from_user_id end)) order by m.status, m.created_at desc;",
            nativeQuery = true)
    public List<Object> myMessages(Long authId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE messages SET status = 1 WHERE id IN (:req);",
            nativeQuery = true)
    public void updateStatus(List<Long> req);

    public Message findMessageByIdAndStatus(Long id, int status);

    void deleteById(Long id);

    @Transactional
    @Modifying
    @Query(value = "delete from messages where " +
            "from_user_id = :fromUserId and to_user_id = :toUserId " +
            "or from_user_id = :toUserId and to_user_id = :fromUserId " +
            "and obj_id = :objId",
            nativeQuery = true)
    public void deleteChat(Long fromUserId, Long toUserId, Long objId);

    @Transactional
    @Modifying
    @Query(value = "select id from messages where from_user_id = :toUserId and to_user_id = :fromUserId  and status = 0;",
            nativeQuery = true)
    List<Long> getUnreadMessages(Long toUserId, Long fromUserId);

    @Transactional
    @Modifying
    @Query(value = "select id from messages where to_user_id = :userId and status = 0;",
            nativeQuery = true)
    List<Long> findMessagesByFromUserIdOrToUserId(Long userId);

}
