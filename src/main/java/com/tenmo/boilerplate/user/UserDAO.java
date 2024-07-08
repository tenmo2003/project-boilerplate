package com.tenmo.boilerplate.user;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDAO {
    private final MongoTemplate mongoTemplate;
    private final LoadingCache<String, User> userCache = Caffeine.newBuilder()
            .build(new CacheLoader<String, User>() {
                @Override
                public @Nullable User load(String s) throws Exception {
                    return mongoTemplate.findOne(Query.query(Criteria.where("username").is(s)), User.class);
                }
            });

    public User findByEmail(String email) {
        return userCache.get(email);
    }

    public User save(User user) {
        return mongoTemplate.save(user);
    }
}
