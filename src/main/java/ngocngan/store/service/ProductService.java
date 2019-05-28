package ngocngan.store.service;

import ngocngan.store.constant.Constant;
import ngocngan.store.models.Product;
import ngocngan.store.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ngan nnh on 5/28/2019
 * @project store
 */

@Service
public class ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    private static ProductRepository productRepository;

    @Autowired public ProductService(ProductRepository productRepository){
        ProductService.productRepository = productRepository;
    }

    public List<Product> getProducts(){
        try {
            List<Product> products = productRepository.findAll();
            LOGGER.info(Constant.LOGGER_INFO_PRODUCTS_SIZE(products.size()));
            return products;
        } catch (Exception e) {
            LOGGER.warn(Constant.LOGGER_EXCEPTION(e));
            return null;
        }
    }

    public Product getProductByUUID(String uuid) {
        try {
            Product product = new Product();
            if (productRepository.findByUuid(uuid) != null) {
                product = productRepository.findByUuid(uuid);
                LOGGER.info(Constant.LOGGER_PRODUCT_FOUND(product.getUuid()));
            } else {
                LOGGER.warn(Constant.LOGGER_PRODUCT_NOT_FOUND(uuid));
            }
            return product;
        } catch (Exception e) {
            LOGGER.warn(Constant.LOGGER_EXCEPTION(e));
            return new Product();
        }
    }

    public Product saveProduct(String uuid, String name, float priceIn, float priceOut, String details,
            String imagePath) {
        try {
            Product product = getProductByUUID(uuid);
            product.setUuid(uuid);
            product.setName(name);
            product.setPriceIn(priceIn);
            product.setPriceOut(priceOut);
            product.setDetails(details);
            product.setImagePath(imagePath);
            productRepository.save(product);
            LOGGER.info(Constant.LOGGER_SAVE_PRODUCT_SUCCESS(name));
            return product;
        } catch (Exception e) {
            LOGGER.warn(Constant.LOGGER_EXCEPTION(e));
            return new Product();
        }
    }

    public void deleteProduct(String uuid){
        try{
            productRepository.delete(getProductByUUID(uuid));
            LOGGER.info(Constant.LOGGER_DELETE_PRODUCT_SUCCESS(uuid));
        }catch (Exception e){
            LOGGER.warn(Constant.LOGGER_EXCEPTION(e));
        }
    }
}
