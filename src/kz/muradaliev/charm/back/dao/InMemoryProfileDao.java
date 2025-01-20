package kz.muradaliev.charm.back.dao;

import kz.muradaliev.charm.back.model.Gender;
import kz.muradaliev.charm.back.model.Profile;
import kz.muradaliev.charm.back.model.Role;
import kz.muradaliev.charm.back.model.Status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryProfileDao implements ProfileDao {

    private static final ProfileDao INSTANCE = new InMemoryProfileDao();

    private final AtomicLong idStorage;
    private final ConcurrentHashMap<Long, Profile> storage;

    private InMemoryProfileDao() {
        this.storage = new ConcurrentHashMap<>();
        Profile profile = new Profile();
        profile.setId(1L);
        profile.setEmail("ivanov@mail.ru");
        profile.setPassword("123");
        profile.setName("Ivan");
        profile.setSurname("Ivanov");
        profile.setBirthDate(LocalDate.parse("2001-12-03"));
        profile.setAbout("I am QA");
        profile.setGender(Gender.MALE);
        profile.setStatus(Status.ACTIVE);
        profile.setRole(Role.ADMIN);
        this.storage.put(1L, profile);
        Profile profile1 = new Profile();
        profile1.setId(2L);
        profile1.setEmail("sidorova@mail.ru");
        profile1.setPassword("456");
        profile1.setName("Elena");
        profile1.setSurname("Sidorova");
        profile1.setBirthDate(LocalDate.parse("1999-09-01"));
        profile1.setAbout("I am Java Dev");
        profile1.setGender(Gender.FEMALE);
        profile1.setStatus(Status.INACTIVE);
        profile1.setRole(Role.USER);
        this.storage.put(2L, profile1);
        this.idStorage = new AtomicLong(3L);
    }

    public static ProfileDao getInstance() {
        return INSTANCE;
    }

    @Override
    public Profile save(Profile profile) {
        profile.setId(idStorage.getAndIncrement());
        storage.put(profile.getId(), profile);
        return profile;
    }

    @Override
    public Optional<Profile> findById(Long id) {
        if (id == null) return Optional.empty();
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public Optional<Profile> findByEmailAndPassword(String email, String password) {
        return storage.values().stream()
                .filter(it -> it.getEmail().equals(email) && it.getPassword().equals(password))
                .findAny();
    }

    @Override
    public boolean existByEmail(String email) {
        if (email == null) return false;
        return storage.values().stream().anyMatch(p -> email.equals(p.getEmail()));
    }

    @Override
    public List<Profile> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void update(Profile profile) {
        Long id = profile.getId();
        if (id == null) return;
        storage.put(id, profile);
    }

    @Override
    public boolean delete(Long id) {
        if (id == null) return false;
        return storage.remove(id) != null;
    }
}