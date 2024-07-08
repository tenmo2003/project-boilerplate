package com.tenmo.boilerplate.user;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import dev.morphia.Datastore;
import dev.morphia.query.filters.Filters;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDAO {
    private final Datastore datastore;
    private final LoadingCache<String, User> userCache = Caffeine.newBuilder()
            .build(new CacheLoader<String, User>() {
                @Override
                public @Nullable User load(String s) throws Exception {
                    return datastore.find(User.class).filter(Filters.eq("username", s)).first();
                }
            });

    public User findByEmail(String email) {
        return userCache.get(email);
    }

    public User save(User user) {
        return datastore.save(user);
    }
}
