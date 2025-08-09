package com.companyname.one.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.companyname.one.domain.UserAccount;
import com.companyname.one.dto.CoursesDto;
import com.companyname.one.dto.ExamansDto;
import com.companyname.one.dto.LanguagesDto;
import com.companyname.one.dto.UserAccountDto;
import com.companyname.one.security.TokenData;
import com.companyname.one.util.Cryption;
import com.companyname.one.util.User;

@Repository
public class UserAccountDaoImpl implements UserAccountDao {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public UserAccount getUserAccountsById(int userId) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(UserAccount.class, userId);
    }

    @Override
    public List<UserAccountDto> getUserAccounts(String userType) {
        Session session = sessionFactory.getCurrentSession();
        List<UserAccountDto> dtoList = new ArrayList<>();
        TokenData data = User.getTokenData(); // Assuming this returns the token data correctly
        if("TEACHER".equals(data.getRole())) {
            List <Object[]> userList = session.createNativeQuery("SELECT l.languagesId,la.name AS languageName, sua.name AS studentName,sua.startDate,sua.modifiedDate\r\n"
                    + "FROM lessons l\r\n"
                    + "LEFT JOIN courses c ON c.languagesId = l.languagesId\r\n"
                    + "LEFT JOIN languages la ON la.languagesId = l.languagesId\r\n"
                    + "LEFT JOIN useraccount sua ON sua.userAccountId = c.studentId\r\n"
                    + "WHERE l.userAccountId =  :userId\r\n"
                    + "GROUP BY l.languagesId,c.studentId\r\n"
                    + "").setParameter("userId", data.getUserId())
                    .getResultList();
            for (Object[] obj : userList) {
                int languagesId = Integer.parseInt(obj[0].toString());
                String languageName = (String) obj[1];
                String studentName = (String) obj[2];
                Date startDate = (Date) obj[3];
                Date modifiedDate = (Date) obj[4];
                UserAccountDto dto = new UserAccountDto(studentName, startDate, modifiedDate);
                dto.setStudentDto(new UserAccountDto(studentName));
                dto.setLanguagesDto(new LanguagesDto(languagesId, languageName));

                dtoList.add(dto);

//                return dtoList;
            }
        }else {
        	
            if ("TEACHER".equalsIgnoreCase(userType) || "ADMIN".equalsIgnoreCase(userType) || "ALL".equalsIgnoreCase(userType)) {
                // Use HQL for simpler user types (Teacher, Admin) and the 'All' case for now
                // The original logic for these was mostly correct.
                String hqlQuery = "SELECT ua FROM UserAccount ua WHERE ua.status = 1 AND ua.userType = :userType ORDER BY ua.name ASC";
                if ("ALL".equalsIgnoreCase(userType)) {
                    hqlQuery = "SELECT ua FROM UserAccount ua WHERE ua.status = 1 ORDER BY ua.name ASC";
                }
                if ("TEACHER".equalsIgnoreCase(userType)) {
                    userType = "TEACHER";
                }
                if ("ADMIN".equalsIgnoreCase(userType)) {
                    userType = "ADMIN";
                }

                Query hqlQueryObj = session.createQuery(hqlQuery, UserAccount.class);
                if (!"ALL".equalsIgnoreCase(userType)) {
                    hqlQueryObj.setParameter("userType", userType);
                }

                List<UserAccount> userAccounts = hqlQueryObj.getResultList();
                for (UserAccount ua : userAccounts) {
                    UserAccountDto dto = new UserAccountDto(ua);
                    dtoList.add(dto);
                }

            } else if ("STUDENT".equalsIgnoreCase(userType)) {
                // Using a native query for students requires careful mapping of the Object[] results
                String nativeQuery = "SELECT l.languagesId, la.name, sua.userAccountId, sua.name AS studentName, sua.age, sua.gender, \r\n"
                		+ "sua.photo, sua.userName, sua.address, sua.nrc, sua.email, sua.phonenum, sua.degree, sua.file, e.examMark, \r\n"
                		+ "sua.startDate, sua.modifiedDate \r\n"
                		+ "FROM lessons l \r\n"
                		+ "LEFT JOIN languages la ON la.languagesId = l.languagesId \r\n"
                		+ "LEFT JOIN courses c ON c.languagesId = l.languagesId \r\n"
                		+ "LEFT JOIN useraccount sua ON sua.userAccountId = c.studentId \r\n"
                		+ "LEFT JOIN examans e ON e.userAccountId = c.studentId \r\n"
                		+ "WHERE sua.status = 1 \r\n"
                		+ "GROUP BY l.languagesId, la.name, sua.userAccountId, sua.name, sua.age, sua.gender, sua.photo, sua.userName, sua.address, sua.nrc, \r\n"
                		+ "sua.email, sua.phonenum, sua.degree, sua.file, e.examMark, sua.startDate, sua.modifiedDate\r\n"
                		+ "";

//                List<Object[]> userList = session.createNativeQuery(nativeQuery)
//                                               .setParameter("userId", data.getUserId())
//                                               .getResultList();
                List<Object[]> userList = session.createNativeQuery(nativeQuery)
                        
                        .getResultList();


                for (Object[] obj : userList) {
                    UserAccountDto dto = new UserAccountDto();
                    dto.setUserAccountId((Integer) obj[2]);
                    dto.setName((String) obj[3]);
                    dto.setAge((Date) obj[4]);
                    dto.setGender((String) obj[5]);
                    dto.setPhoto((String) obj[6]);
                    dto.setUserName((String) obj[7]);
                    dto.setAddress((String) obj[8]);
                    dto.setNrc((String) obj[9]);
                    dto.setEmail((String) obj[10]);
                    dto.setPhonenum((String) obj[11]);
                    dto.setDegree((String) obj[12]);
                    dto.setFile((String) obj[13]);

                    // Check for null values from LEFT JOINs
                    if (obj[0] != null) {
                        LanguagesDto langDto = new LanguagesDto((Integer) obj[0], (String) obj[1]);
                        dto.setLanguagesDto(langDto);
                    }
                    if (obj[14] != null) {
                        ExamansDto examDto = new ExamansDto();
                        examDto.setExamMark((Integer) obj[14]);
                        dto.setExamDto(examDto);
                    }

                    dto.setStartDate((Date) obj[15]);
                    dto.setModifiedDate((Date) obj[16]);

                    dtoList.add(dto);
                }
            }        	
        }

        return dtoList;
    }

    // ... (rest of your methods)

    @Override
    public void saveUserAccounts(UserAccount user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    public void updateUserAccount(UserAccount user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public void deleteUserAccounts(int userAccountId) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(new UserAccount(userAccountId));
    }

    @Override
    public UserAccount getLoginAccount(String userName, String password) {
        Session session = sessionFactory.getCurrentSession();
        String encryPassword = Cryption.encryption(password);
        List<UserAccount> list = session.createQuery("SELECT ac FROM UserAccount ac WHERE  ac.userName=:userName  AND ac.encryptPassword =:encryPassword AND ac.status = 1  ")
                .setParameter("userName", userName)
                .setParameter("encryPassword", encryPassword)
                .getResultList();
        if(list.size()>0) {
            return list.get(0);
        }
        return null;
    }
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<UserAccount> findById(int userAccountId) {
        return Optional.ofNullable(entityManager.find(UserAccount.class, userAccountId));
    }

    @Override
    public void save(UserAccount user) {
        if (user.getUserAccountId() == 0) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
    }
}