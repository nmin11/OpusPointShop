package io.opus_point_shop.controller

import io.opus_point_shop.dto.*
import io.opus_point_shop.service.PurchaseService
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class ViewController(
    private val customerController: CustomerController,
    private val productController: ProductController,
    private val cartController: CartController,
    private val purchaseService: PurchaseService
) {
    private val logger: Logger = LogManager.getLogger()

    @GetMapping("/")
    fun index(model: Model): String {
        val customers = customerController.findAllCustomer()
        val products = productController.findAllProduct()
        model.addAttribute("chargeMoneyDto", ChargeMoneyDto(0, 0))
        model.addAttribute("addToCartDto", AddToCartDto(0, 0, 0))
        model.addAttribute("customers", customers)
        model.addAttribute("products", products)
        return "/index"
    }

    @PostMapping("/charge")
    fun charge(@ModelAttribute("chargeMoneyDto") dto: ChargeMoneyDto): String {
        val result = customerController.chargeMoney(dto)
        logger.info(result.body)
        return "redirect:"
    }

    @PostMapping("/add-cart")
    fun addCart(@ModelAttribute("addToCartDto") dto: AddToCartDto): String {
        val result = cartController.addToCart(dto)
        logger.info(result.body)
        return "redirect:"
    }

    @GetMapping("/cart")
    fun cart(model: Model, @RequestParam name: String): String {
        val customer = customerController.findByName(name)
        val cartList = customer?.id?.let { cartController.findByCustomer(it) }
        logger.info("customer id = " + customer?.id)
        model.addAttribute("customerId", customer?.id)
        model.addAttribute("removeProduct", RemoveProductDto(0, ""))
        model.addAttribute("payToMoney", customer?.id?.let { PurchaseDto(it) })
        model.addAttribute("payToPoint", customer?.id?.let { PurchaseDto(it) })
        model.addAttribute("cartList", cartList)
        return "/cart"
    }

    @PostMapping("/remove-product")
    fun removeProduct(@ModelAttribute("removeProduct") dto: RemoveProductDto): String {
        logger.info("remove product - customer id : ${dto.customerId}")
        logger.info("remove product - product name : ${dto.productName}")
        val productId: Long = productController.findByName(dto.productName)?.id!!
        logger.info("추출한 상품 id $productId")
        val result = cartController.removeFromCart(RemoveFromCartDto(dto.customerId, productId))
        logger.info("상품 제거 결과 : $result")
        return "redirect:"
    }

    @PostMapping("/pay-to-money")
    fun payToMoney(@ModelAttribute("payToMoney") dto: PurchaseDto): String {
        val result = purchaseService.purchaseByMoney(dto.customerId)
        logger.info(result.body)
        return "redirect:"
    }

    @PostMapping("/pay-to-point")
    fun payToPoint(@ModelAttribute("payToPoint") dto: PurchaseDto): String {
        val result = purchaseService.purchaseByPoint(dto.customerId)
        logger.info(result.body)
        return "redirect:"
    }
}