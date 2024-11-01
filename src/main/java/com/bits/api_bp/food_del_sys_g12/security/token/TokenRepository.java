package com.bits.api_bp.food_del_sys_g12.security.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, String> {

  @Query(value = """
      select t from Token t inner join Userdetail u\s
      on t.user.userid = u.userid\s
      where u.userid = :id and (t.expired = false or t.revoked = false)\s
      """)
  List<Token> findAllValidTokenByUser(String id);

  Optional<Token> findByToken(String token);
}
