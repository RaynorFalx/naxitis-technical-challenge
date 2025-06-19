package com.ecommerce.backend.config;

import com.ecommerce.backend.model.product.Category;
import com.ecommerce.backend.repository.CategoryRepository;
import com.ecommerce.backend.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseInitializer {

    @Bean
    @Transactional
    public CommandLineRunner initData(CategoryRepository categoryRepo, ProductRepository productRepo) {

        return args -> {
            if (categoryRepo.count() == 0) {
                createCategory(categoryRepo, "Electronics", "General electronic products");
                createCategory(categoryRepo, "Books", "Books of various genres");
                createCategory(categoryRepo, "Clothing", "Men and women apparel");
            }
        };
    }

    private void createCategory(CategoryRepository repo, String name, String description) {
        repo.save(Category.builder().name(name).description(description).build());
    }

    //TODO Handle that with S3 integration
//    private void recreateImageDirectory() throws IOException {
//        Path uploadPath = Paths.get(IMAGE_DIR);
//        FileSystemUtils.deleteRecursively(uploadPath);
//        Files.createDirectories(uploadPath);
//    }
//    private void createProductWithImages(ProductRepository pRepo, ProductImageRepository iRepo, Category category, String name, String description, Long productId) throws IOException {
//
//        Path productImageDir = Paths.get(IMAGE_DIR + productId);
//        Files.createDirectories(productImageDir);
//
//        Product product = pRepo.save(Product.builder().name(name).description(description).category(category).productImages(new ArrayList<>()).build());
//
//        category.getProducts().add(product);
//
//        for (String filename : MOCK_IMAGES) {
//            boolean isPrimary = filename.contains("primary");
//            String filePath = "product-images/" + productId + "/" + filename;
//
//            Path imagePath = productImageDir.resolve(filename);
//            Files.createFile(imagePath);
//
//            iRepo.save(ProductImage.builder().filePath(filePath).contentType(isPrimary ? "image/jpeg" : "image/png").primary(isPrimary).product(product).build());
//        }
//    }
}