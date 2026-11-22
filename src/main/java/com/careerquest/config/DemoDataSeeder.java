package com.careerquest.config;

import com.careerquest.model.Student;
import com.careerquest.model.StudentPerformance;
import com.careerquest.model.StudentAchievement;

import com.careerquest.repository.StudentRepository;
import com.careerquest.repository.StudentPerformanceRepository;
import com.careerquest.repository.StudentAchievementRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
public class DemoDataSeeder {

    @Bean
    CommandLineRunner insertDemoData(
            StudentRepository studentRepository,
            StudentPerformanceRepository performanceRepository,
            StudentAchievementRepository achievementRepository) {

        return args -> {

            BCryptPasswordEncoder encoder =
                    new BCryptPasswordEncoder();

            String encodedPassword =
                    encoder.encode("Demo@123");

            // Student 1: Priya
            Student priya = getOrCreateStudent(
                    studentRepository, encoder,
                    "Priya Sharma",
                    "priya@careerquest.com",
                    "Information Technology", 2027,
                    encodedPassword
            );

            savePerformance(performanceRepository, priya,
                    95, 88, 92, 1850, 7, 92);

            saveAchievements(achievementRepository, priya,
                    java.util.Arrays.asList(
                        new String[]{"Coding Champion",
                            "Excellent coding performance"},
                        new String[]{"Aptitude Star",
                            "Outstanding aptitude score"},
                        new String[]{"Interview Pro",
                            "Excellent mock interview performance"},
                        new String[]{"Placement Ready",
                            "High placement readiness"}
                    ));

            // Student 2: Arun
            Student arun = getOrCreateStudent(
                    studentRepository, encoder,
                    "Arun Kumar",
                    "arun@careerquest.com",
                    "Computer Science", 2027,
                    encodedPassword
            );

            savePerformance(performanceRepository, arun,
                    72, 65, 68, 850, 4, 68);

            saveAchievements(achievementRepository, arun,
                   java.util.Arrays.asList(
                        new String[]{"Problem Solver",
                            "Completed coding challenges"},
                        new String[]{"Quick Learner",
                            "Completed aptitude training"}
                    ));

        

            System.out.println(
                "CareerQuest: 3 demo students and performance records are ready."
            );
        };
    }

    private Student getOrCreateStudent(
            StudentRepository repository,
            BCryptPasswordEncoder encoder,
            String name, String email,
            String department, int year,
            String encodedPassword) {

        Student student = repository.findByEmail(email)
                .orElseGet(Student::new);

        if (student.getId() == null) {
            student.setPasswordHash(encodedPassword);
        }

        student.setFullName(name);
        student.setEmail(email);
        student.setDepartment(department);
        student.setGraduationYear(year);

        return repository.save(student);
    }

    private void savePerformance(
            StudentPerformanceRepository repository,
            Student student,
            int coding, int aptitude, int interview,
            int xp, int level, int readiness) {

        StudentPerformance performance =
                repository.findByStudent_Id(student.getId())
                .orElseGet(StudentPerformance::new);

        performance.setStudent(student);
        performance.setCodingScore(coding);
        performance.setAptitudeScore(aptitude);
        performance.setInterviewScore(interview);
        performance.setXpPoints(xp);
        performance.setLevel(level);
        performance.setPlacementReadiness(readiness);

        repository.save(performance);
    }

    private void saveAchievements(
            StudentAchievementRepository repository,
            Student student,
            List<String[]> achievements) {

        for (String[] item : achievements) {

            boolean exists = repository
                    .existsByStudent_IdAndTitle(
                        student.getId(), item[0]);

            if (!exists) {
                StudentAchievement achievement =
                        new StudentAchievement();

                achievement.setStudent(student);
                achievement.setTitle(item[0]);
                achievement.setDescription(item[1]);

                repository.save(achievement);
            }
        }
    }
}