package io.opus_point_shop.service

import io.opus_point_shop.dto.ProductDto
import io.opus_point_shop.dto.RegisterProductDto
import io.opus_point_shop.entity.Product
import io.opus_point_shop.repository.ProductRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ProductService(private val productRepository: ProductRepository) {
    fun registerProduct(dto: RegisterProductDto): ResponseEntity<Any> {
        if (findByName(dto.name) != null) {
            return ResponseEntity.status(400).body("이미 존재하는 상품 이름입니다.")
        }
        val product = productRepository.save(Product(dto.name, dto.price, dto.quantity, dto.pointRate, dto.owner))
        return ResponseEntity.status(200).body(product)
    }

    fun findAllProduct(): MutableList<ProductDto> {
        val products = productRepository.findAll()
        val list: MutableList<ProductDto> = mutableListOf()
        for (product in products) {
            list.add(product.dtoToEntity(product))
        }
        return list
    }

    fun findByName(name: String): ProductDto? {
        val product = productRepository.findByName(name)
        return product?.dtoToEntity(product)
    }
}