package com.example.skymovieratingservice.config;

import com.example.skymovieratingservice.entity.Movie;
import com.example.skymovieratingservice.entity.User;
import com.example.skymovieratingservice.repository.MovieRepository;
import com.example.skymovieratingservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Load sample movies
        if (movieRepository.count() == 0) {
            movieRepository.save(new Movie("The Shawshank Redemption",
                    "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
                    "Drama", 1994));

            movieRepository.save(new Movie("The Godfather",
                    "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",
                    "Crime", 1972));

            movieRepository.save(new Movie("The Dark Knight",
                    "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests.",
                    "Action", 2008));

            movieRepository.save(new Movie("Pulp Fiction",
                    "The lives of two mob hitmen, a boxer, a gangster and his wife intertwine in four tales of violence and redemption.",
                    "Crime", 1994));

            movieRepository.save(new Movie("Forrest Gump",
                    "The presidencies of Kennedy and Johnson, the events of Vietnam, Watergate and other historical events unfold from the perspective of an Alabama man.",
                    "Drama", 1994));
        }

        // Create default admin user
        if (!userRepository.existsByEmail("admin@sky.com")) {
            User admin = new User();
            admin.setEmail("admin@sky.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(User.Role.ADMIN);
            userRepository.save(admin);
        }

        // Create default test user
        if (!userRepository.existsByEmail("user@sky.com")) {
            User user = new User();
            user.setEmail("user@sky.com");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRole(User.Role.USER);
            userRepository.save(user);
        }
    }
}
