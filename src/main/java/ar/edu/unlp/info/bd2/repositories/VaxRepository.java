package ar.edu.unlp.info.bd2.repositories;

//import ar.edu.unlp.info.bd2.model.SupportStaff;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class VaxRepository{

    @Autowired
    private SessionFactory sessionFactory;

    //TODO: Descomentar cuando este implementado Support Staff (linea 3 tambien)
    /*public SupportStaff getSupportStaffById(long id) throws VaxException {
        SupportStaff supportStaff;
        try {
            Session session = this.sessionFactory.getCurrentSession(); //Trae o crea sesion activa
            supportStaff = (SupportStaff)session.createQuery("FROM SupportStaff WHERE Id = :id").setParameter("id", id).uniqueResult();
        } catch (Exception e) {
            throw new VaxException("SOMETHING WENT WRONG"); //TODO: esto
        }
        return supportStaff;
    }*/
}
