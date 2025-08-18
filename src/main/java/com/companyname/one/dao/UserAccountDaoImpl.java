package com.companyname.one.dao;

import com.companyname.one.domain.UserAccount;
import com.companyname.one.dto.CoursesDto;
import com.companyname.one.dto.ExamansDto;
import com.companyname.one.dto.LanguagesDto;
import com.companyname.one.dto.LessonsDto;
import com.companyname.one.dto.UserAccountDto;
import com.companyname.one.security.TokenData;
import com.companyname.one.util.Cryption;
import com.companyname.one.util.User;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class UserAccountDaoImpl implements UserAccountDao {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserAccount getUserAccountsById(int userId) {
        return entityManager.find(UserAccount.class, userId);
    }

    @Override
    public List<UserAccountDto> getUserAccounts(String userType) {
        List<UserAccountDto> dtoList = new ArrayList<>();
       

        // Special public endpoint: userType "TE" shows all users to everyone (logged in or not).
        if ("TE".equals(userType)) {
            String hql = "SELECT ua FROM UserAccount ua WHERE ua.status = 1 AND ua.userType = 'TEACHER' ORDER BY ua.name ASC";
            List<UserAccount> userList = entityManager.createQuery(hql, UserAccount.class).getResultList();
            for (UserAccount ua : userList) {
                dtoList.add(new UserAccountDto(ua));
            }
            return dtoList;
        }
        
        TokenData data = User.getTokenData();
        String userRole = (data != null) ? data.getRole() : null;
        String userId = (data != null) ? data.getUserId() : null;

        // --- All logic below this line requires a specific role: ADMIN or TEACHER ---

        // Check for required roles. If not an ADMIN or TEACHER, access is denied.
        if (!"ADMIN".equals(userRole) && !"TEACHER".equals(userRole)) {
            return dtoList; // Returns an empty list for unauthorized access.
        }

        // Specific logic for a logged-in TEACHER.
        if ("TEACHER".equals(userRole)) {
            // A teacher only sees their students, regardless of the userType parameter.
            String nativeQuery = "SELECT l.lessonsId, l.userAccountId AS lessUser,l.languagesId, la.name, sua.userAccountId, sua.name AS studentName, sua.age, sua.gender,\r\n"
            		+ "sua.photo, sua.userName, sua.address, sua.nrc, sua.email, sua.phonenum, sua.degree, sua.file, e.examMark,\r\n"
            		+ "sua.startDate, sua.modifiedDate, c.type \r\n"
            		+ "FROM lessons l \r\n"
            		+ "LEFT JOIN languages la ON la.languagesId = l.languagesId \r\n"
            		+ "LEFT JOIN courses c ON c.languagesId = l.languagesId \r\n"
            		+ "LEFT JOIN useraccount sua ON sua.userAccountId = c.studentId \r\n"
            		+ "LEFT JOIN examans e ON e.coursesId = c.coursesId\r\n"
            		+ "WHERE l.userAccountId = :userId AND sua.status = 1\r\n"
            		+ "GROUP BY l.languagesId\r\n"
            		+ "";
            List<Object[]> userList = entityManager.createNativeQuery(nativeQuery)
                    .setParameter("userId", userId)
                    .getResultList();

            for (Object[] obj : userList) {
                System.out.println("Row length: " + obj.length);

                UserAccountDto dto = new UserAccountDto();
                dto.setLessonsDto(new LessonsDto(((Number) obj[0]).intValue()));
                dto.setStudentDto(new UserAccountDto(((Number) obj[1]).intValue()));
                if (obj[2] != null) {
                    dto.setLanguagesDto(new LanguagesDto(((Number) obj[2]).intValue(), (String) obj[3]));
                }
                dto.setUserAccountId(obj[4] != null ? ((Number) obj[4]).intValue() : null);
                dto.setName((String) obj[5]);
                dto.setAge((Date) obj[6]);
                dto.setGender((String) obj[7]);
                dto.setPhoto((String) obj[8]);
                dto.setUserName((String) obj[9]);
                dto.setAddress((String) obj[10]);
                dto.setNrc((String) obj[11]);
                dto.setEmail((String) obj[12]);
                dto.setPhonenum((String) obj[13]);
                dto.setDegree((String) obj[14]);
                dto.setFile((String) obj[15]);
                if (obj[16] != null) {
                    ExamansDto examDto = new ExamansDto();
                    examDto.setExamMark(((Number) obj[16]).intValue());
                    dto.setExamDto(examDto);
                }
                dto.setStartDate((Date) obj[17]);
                dto.setModifiedDate((Date) obj[18]);
                CoursesDto coursesDto = new CoursesDto();
                coursesDto.setType((String) obj[19]);
                dto.setCoursesDto(coursesDto);
                dtoList.add(dto);

            }
            return dtoList;
        }

        // Logic for a logged-in ADMIN.
        // The admin can use userType to filter for specific user types.
        if ("STU".equals(userType)) {
             // Logic for getting all students
            String nativeQuery = "SELECT la.languagesId, la.name, sua.userAccountId, sua.name AS studentName, sua.age, sua.gender, sua.photo, sua.userName, sua.address, sua.nrc, sua.email,\r\n"
            		+ "sua.phonenum, sua.degree, sua.file, e.examMark, sua.startDate, sua.modifiedDate, c.type\r\n"
            		+ "FROM courses c\r\n"
            		+ "LEFT JOIN useraccount sua ON c.studentId = sua.userAccountId\r\n"
            		+ "LEFT JOIN languages la ON la.languagesId = c.languagesId\r\n"
            		+ "LEFT JOIN examans e ON e.coursesId = c.coursesId\r\n"
            		+ "            ";
            List<Object[]> userList = entityManager.createNativeQuery(nativeQuery).getResultList();

            for (Object[] obj : userList) {
                System.out.println("Row length: " + obj.length);

                UserAccountDto dto = new UserAccountDto();
                dto.setUserAccountId(obj[2] != null ? ((Number) obj[2]).intValue() : null);
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
                if (obj[0] != null) {
                    dto.setLanguagesDto(new LanguagesDto(((Number) obj[0]).intValue(), (String) obj[1]));
                }
                if (obj[14] != null) {
                    ExamansDto examDto = new ExamansDto();
                    examDto.setExamMark(((Number) obj[14]).intValue());
                    dto.setExamDto(examDto);
                }
                dto.setStartDate((Date) obj[15]);
                dto.setModifiedDate((Date) obj[16]);
                CoursesDto coursesDto = new CoursesDto();
                coursesDto.setType((String) obj[17]);
                dto.setCoursesDto(coursesDto);
                dtoList.add(dto);
            }
            return dtoList;
        } else {
            // General query for any user type, including "ALL", "TEACHER", and other specific types.
            String hql = "SELECT ua FROM UserAccount ua WHERE ua.status = 1";
            if (!"ALL".equals(userType)) {
                hql += " AND ua.userType = :userType";
            }
            hql += " ORDER BY ua.name ASC";

            TypedQuery<UserAccount> query = entityManager.createQuery(hql, UserAccount.class);

            if (!"ALL".equals(userType)) {
                query.setParameter("userType", userType);
            }

            List<UserAccount> userList = query.getResultList();
            for (UserAccount ua : userList) {
                dtoList.add(new UserAccountDto(ua));
            }
            return dtoList;
        }
    }

    @Override
    public void saveUserAccounts(UserAccount user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUserAccount(UserAccount user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUserAccounts(int userAccountId) {
        UserAccount user = entityManager.find(UserAccount.class, userAccountId);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public UserAccount getLoginAccount(String userName, String password) {
        String encryPassword = Cryption.encryption(password);
        String hql = "SELECT ac FROM UserAccount ac WHERE ac.userName = :userName AND ac.encryptPassword = :encryPassword AND ac.status = 1";

        List<UserAccount> list = entityManager.createQuery(hql, UserAccount.class)
                .setParameter("userName", userName.trim())
                .setParameter("encryPassword", encryPassword)
                .getResultList();

        return list.isEmpty() ? null : list.get(0);
    }

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