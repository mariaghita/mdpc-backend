package server;

public interface Repository<ID, Entity> {
    Entity getOne(ID id);
    Iterable<Entity> getAll();
    Entity add(Entity entity);
    Entity update(Entity entity);
}
