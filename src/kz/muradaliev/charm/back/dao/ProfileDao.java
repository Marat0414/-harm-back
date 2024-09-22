package kz.muradaliev.charm.back.dao;

import kz.muradaliev.charm.back.model.Profile;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ProfileDao {
private final ConcurrentHashMap<Long, Profile> storage;
private final AtomicLong idStorage;

    public ProfileDao() {
        this.storage = new ConcurrentHashMap<>();
        this.idStorage = new AtomicLong();
    }

    public Profile save(Profile profile) {
        long id = idStorage.incrementAndGet();
        profile.setId(id);
        storage.put(id,profile);
        return profile;
    }

    public Optional<Profile> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }
    //TODO delete, update, findAll
}