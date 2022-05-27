package io.opus_point_shop

import io.opus_point_shop.dto.ChargeMoneyDto
import io.opus_point_shop.dto.RegisterProductDto
import io.opus_point_shop.service.CustomerService
import io.opus_point_shop.service.ProductService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.annotation.PostConstruct

//테스트하기에 용이하도록 초기 데이터를 생성해주는 클래스입니다.

@Component
@Transactional
class InitDB(
    private val customerService: CustomerService,
    private val productService: ProductService
) {
    @PostConstruct
    fun initialData() {
        //customer 6명 생성
        customerService.createCustomer("A")
        customerService.createCustomer("B")
        customerService.createCustomer("C")
        customerService.createCustomer("D")
        customerService.createCustomer("E")
        customerService.createCustomer("F")

        //customer 돈 충전
        var chargeMoneyDto = ChargeMoneyDto(1, 10000)
        customerService.chargeMoney(chargeMoneyDto)
        chargeMoneyDto.customerId = 2
        customerService.chargeMoney(chargeMoneyDto)
        chargeMoneyDto.customerId = 3
        customerService.chargeMoney(chargeMoneyDto)
        chargeMoneyDto.customerId = 4
        customerService.chargeMoney(chargeMoneyDto)
        chargeMoneyDto.customerId = 5
        customerService.chargeMoney(chargeMoneyDto)
        chargeMoneyDto.customerId = 6
        customerService.chargeMoney(chargeMoneyDto)


        //product 10종 생성
        var registerProductDto = RegisterProductDto(
            "삼각김밥", 1500, 200, 20, "GS25"
        )
        productService.registerProduct(registerProductDto)

        registerProductDto.name = "튀김우동"
        registerProductDto.price = 1800
        registerProductDto.quantity = 400
        registerProductDto.pointRate = 15
        registerProductDto.owner = "농심"
        productService.registerProduct(registerProductDto)

        registerProductDto.name = "펩시 제로 라임"
        registerProductDto.price = 800
        registerProductDto.quantity = 1000
        registerProductDto.pointRate = 10
        registerProductDto.owner = "펩시"
        productService.registerProduct(registerProductDto)

        registerProductDto.name = "초코파이"
        registerProductDto.price = 4000
        registerProductDto.quantity = 150
        registerProductDto.pointRate = 40
        registerProductDto.owner = "오리온"
        productService.registerProduct(registerProductDto)

        registerProductDto.name = "아메리카노"
        registerProductDto.price = 4500
        registerProductDto.quantity = 1500
        registerProductDto.pointRate = 10
        registerProductDto.owner = "스타벅스"
        productService.registerProduct(registerProductDto)

        registerProductDto.name = "보조배터리"
        registerProductDto.price = 8000
        registerProductDto.quantity = 100
        registerProductDto.pointRate = 20
        registerProductDto.owner = "삼성"
        productService.registerProduct(registerProductDto)

        registerProductDto.name = "마스크"
        registerProductDto.price = 500
        registerProductDto.quantity = 5000
        registerProductDto.pointRate = 50
        registerProductDto.owner = "GS25"
        productService.registerProduct(registerProductDto)

        registerProductDto.name = "텀블러"
        registerProductDto.price = 45000
        registerProductDto.quantity = 200
        registerProductDto.pointRate = 40
        registerProductDto.owner = "스타벅스"
        productService.registerProduct(registerProductDto)

        registerProductDto.name = "노트"
        registerProductDto.price = 1000
        registerProductDto.quantity = 2000
        registerProductDto.pointRate = 60
        registerProductDto.owner = "다이소"
        productService.registerProduct(registerProductDto)

        registerProductDto.name = "QHD 모니터"
        registerProductDto.price = 450000
        registerProductDto.quantity = 100
        registerProductDto.pointRate = 50
        registerProductDto.owner = "LG"
        productService.registerProduct(registerProductDto)
    }
}