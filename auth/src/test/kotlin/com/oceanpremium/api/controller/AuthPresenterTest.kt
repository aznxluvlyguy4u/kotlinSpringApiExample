//package com.oceanpremium.api.controller
//
//import com.oceanpremium.api.gateway.AuthGateway
//import com.oceanpremium.api.gateway.AuthGatewayImpl
//import com.oceanpremium.api.model.Token
//import com.oceanpremium.api.model.User
//import com.oceanpremium.api.presenter.AuthPresenter
//import com.oceanpremium.api.usecase.AuthUseCase
//import com.oceanpremium.api.usecase.AuthUseCaseImpl
//import org.junit.Assert.assertEquals
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.Mockito
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
//import org.springframework.http.HttpHeaders
//import org.springframework.http.MediaType
//import org.springframework.test.context.junit4.SpringRunner
//import org.springframework.test.web.servlet.MockMvc
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
//
//@RunWith(SpringRunner::class)
//@WebMvcTest(value= [AuthPresenter::class], secure = false)
//class AuthPresenterTest {
//
//    @Autowired
//    private val mockMvc: MockMvc? = null
//
//
//    private var authGateway: AuthGateway? = AuthGatewayImpl()
//
//
//    private var authUseCase: AuthUseCase? = AuthUseCaseImpl(authGateway!!)
//
//    @Before
//    fun setup(){
//        authGateway = AuthGatewayImpl()
//        authUseCase = AuthUseCaseImpl(authGateway!!)
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun createAuth() {
//        var mockUser = User("mockedUsername", "mocked@EmailAddress.com")
//
//        var exampleAuthJson =
//            "{\"name\":\"" + mockUser.name + "\",\"emailAddress\":\"" + mockUser.emailAddress + "\"}"
//
//        val mockToken = Token("mockedAccessToken", "mockedRefreshToken", 1234, mockUser.emailAddress!!)
//
//        // to respond back with mockToken
//        Mockito.`when`(
//            authUseCase?.execute(Mockito.mock(User::class.java))
//        ).thenReturn(mockToken)
//
//        // Send as body to /auth
//        val requestBuilder = MockMvcRequestBuilders
//            .post("/auth")
//            .accept(MediaType.APPLICATION_JSON).content(exampleAuthJson)
//            .contentType(MediaType.APPLICATION_JSON)
//
//        val result = mockMvc?.perform(requestBuilder)?.andReturn()
//
//        val response = result?.response
//
//        assertEquals(org.springframework.http.HttpStatus.CREATED.value().toString(), response?.status)
//
//        assertEquals(
//            "http://localhost/auth",
//            response?.getHeader(HttpHeaders.LOCATION)
//        )
//
//    }
//
//}