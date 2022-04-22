package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class VaxRepository {

    @Autowired
    private SessionFactory sessionFactory;

    // TODO: Descomentar cuando este implementado Support Staff (linea 3 tambien)
    public SupportStaff getSupportStaffByDni(String dni) {
        SupportStaff supportStaff;
        try {
            Session session = this.sessionFactory.getCurrentSession(); // Trae o crea sesion activa
            supportStaff = (SupportStaff) session.createQuery("FROM SupportStaff WHERE Dni = :dni").setParameter("dni", dni).uniqueResult();
        } catch (Exception e) {
            return null;
        }
        return supportStaff;
    }

    public void save(Object objectToSave) throws VaxException {
        try {
            Session session = this.sessionFactory.getCurrentSession(); // Trae o crea sesion activa
            session.save(objectToSave);
        } catch (Exception e) {
            throw new VaxException("SOMETHING WENT WRONG"); // TODO: esto
        }
    }

    public Object update(Object objectToUpdate) throws VaxException {
        try {
            Session session = this.sessionFactory.getCurrentSession(); // Trae o crea sesion activa
            session.update(objectToUpdate);
        } catch (Exception e) {
            throw new VaxException("SOMETHING WENT WRONG"); // TODO: esto
        }
        return objectToUpdate;
    }
}
