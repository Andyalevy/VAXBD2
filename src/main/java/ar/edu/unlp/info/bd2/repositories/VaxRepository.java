package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

public class VaxRepository {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * This method will save any given object.
     * If the table do not exist it will throw an exception.
     * @param objectToSave
     * @throws VaxException
     */
    public void save(Object objectToSave) throws VaxException {
        try {
            Session session = this.sessionFactory.getCurrentSession(); // Trae o crea sesion activa
            session.save(objectToSave);
            session.flush();
        } catch (Exception e) {
            Throwable t = e.getCause();
            while ((t != null) && !(t instanceof ConstraintViolationException)) {
                t = t.getCause();
            }
            if (t instanceof ConstraintViolationException) {
                throw new VaxException("Constraint Violation");
            } else {
                throw new VaxException("SOMETHING WENT WRONG"); // TODO: esto
            }
        }
    }

}
