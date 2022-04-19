package ru.webprak.Dao;
import ru.webprak.Models.Instances;

import java.util.List;

public interface InstancesDao {
    void create(Instances instance);
    void update(Instances instance);
    void delete(Instances instance);
    Instances readByID(int id);
    List<Instances> readInstancesById(int book);
    List<Instances> readInstances();
}