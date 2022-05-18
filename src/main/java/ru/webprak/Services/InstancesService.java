package ru.webprak.Services;

import ru.webprak.Dao.Impl.InstancesDaoImpl;
import ru.webprak.Dao.InstancesDao;
import ru.webprak.Models.Books;
import ru.webprak.Models.Instances;

import java.util.List;

public class InstancesService {
    private final InstancesDao instancesDao = new InstancesDaoImpl();
    public void createInstance(Instances instance) {
        instancesDao.create(instance);
    }

    public List<Instances> readInstancesByBookId(Books book) {
        return instancesDao.readInstancesById(book.getBook_id());
    }

    public List<Instances> readInstances() {
        return instancesDao.readInstances();
    }

    public void deleteInstance(Instances instance) {
        instancesDao.delete(instance);
    }

    public void updateInstance(Instances instance) {
        instancesDao.update(instance);
    }

    public Instances readInstancesByID(int id) {
        return instancesDao.readByID(id);
    }

    public Instances readByBookIdByInstanceId(int book_id, int instance_id){return instancesDao.readByBookIdByInstanceId(book_id, instance_id);}
}
