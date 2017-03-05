package common.main;

import common.hibernate.dao.UserEntity;
import common.hibernate.utils.HibernateSessionFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Alexander on 27.02.2017.
 */
public class ManageUser {
    private static SessionFactory factory = HibernateSessionFactory.getSessionFactory();
    public static Integer pageStartNum = 0;
    public static Integer pageList = 3;
    public static Integer pageMax=0;
    public static String searchName="User";

    /*
    public static void main(String[] args)
    {
        factory = HibernateSessionFactory.getSessionFactory();
        ManageUser manageUser = new ManageUser();
        manageUser.addUser("User Ten", 40,new java.util.Date());
        manageUser.updateUser(1,"Number One",0);
        //manageUser.deleteUser(3);

        ArrayList<UserEntity> list = manageUser.listUser();
        for (UserEntity user:list)
        {
            //UserEntity userEntity = (UserEntity) user;
            System.out.println(user.getName());
        }

        //factory.close();
    }
*/
    public Integer addUser(String name, Integer age, Date crDate) {
        Integer userId;

        Session session = factory.openSession();
        session.beginTransaction();
        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);
        userEntity.setAge(age);
        userEntity.setCreatedDate(crDate);
        userId = (Integer) session.save(userEntity);

        session.getTransaction().commit();
        session.close();
        System.out.println("Insert complite, userId = " + userId);
        return userId;
    }

    public void updateUser(Integer userId, String name, Integer age) {
        Session session = factory.openSession();
        session.beginTransaction();
        UserEntity userEntity = (UserEntity) session.get(UserEntity.class, userId);
        if (name != null) {
            userEntity.setName(name);
        }
        if (age > 0 && age < 100) {
            userEntity.setAge(age);
        }
        session.getTransaction().commit();
        session.close();
    }

    public void updateUser(UserEntity userEntity) {
        Session session = factory.openSession();
        session.beginTransaction();
        UserEntity currUser = (UserEntity) session.get(UserEntity.class, userEntity.getId());

        currUser.setName(userEntity.getName());
        currUser.setAge(userEntity.getAge());
        currUser.setIsAdmin(userEntity.getIsAdmin());
        session.save(currUser);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteUser(Integer userId) {
        Session session = factory.openSession();
        session.beginTransaction();
        UserEntity userEntity = (UserEntity) session.get(UserEntity.class, userId);
        session.delete(userEntity);
        session.getTransaction().commit();
        session.close();
    }

    public UserEntity getUser(Integer userId) {
        Session session = factory.openSession();
        UserEntity userEntity = (UserEntity) session.get(UserEntity.class, userId);
        session.close();
        return userEntity;
    }

    public ArrayList<UserEntity> listUser(SearchFields searchFields) {
        Session session = factory.openSession();
        session.beginTransaction();
        //Query query = session.createQuery("from UserEntity limit");

        Criteria criteria= session.createCriteria(UserEntity.class);

        if (searchFields.getSearchName()!=null)
        {
            criteria.add(Restrictions.like("name",searchFields.getSearchName(), MatchMode.ANYWHERE));
        }
        if (searchFields.getSearchAge()!=null&&searchFields.getSearchAge()>0)
        {
            criteria.add(Restrictions.eq("age",searchFields.getSearchAge()));
        }
        List<Object> listAll = criteria.list();
        pageMax=listAll.size()-1;

        criteria.setFirstResult(pageStartNum);
        criteria.setMaxResults(pageList);

        List<Object> list = criteria.list();

        ArrayList < UserEntity > userList = new ArrayList<UserEntity>();
        for (Object user : list) {
            UserEntity userEntity = (UserEntity) user;
            userList.add(userEntity);
            //System.out.println(userEntity.getName());
        }

        session.getTransaction().commit();
        session.close();

        return userList;

    }

    public static void closeFactory() {
        factory.close();
    }

    public Integer addUser(UserEntity userEntity) {
        Integer userId;

        Session session = factory.openSession();
        session.beginTransaction();

        userId = (Integer) session.save(userEntity);

        session.getTransaction().commit();
        session.close();
        System.out.println("Insert complite, userId = " + userId);
        return userId;
    }
}
