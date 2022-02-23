USE db_university;

SELECT user_authorities.user_id, user.username, authority.title
FROM user_authorities
JOIN user ON user.id = user_authorities.user_id
JOIN authority ON authority.id = user_authorities.authorities_id
-- WHERE user.username = "Stooniacithly"