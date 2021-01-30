package com.example.anchorbooks

import com.example.anchorbooks.Model.Local.Book
import com.example.anchorbooks.Model.Remote.ApiApp
import com.google.common.truth.Truth
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiBookTest {

    lateinit var mMockWebServer: MockWebServer
    lateinit var mApiTest: ApiApp

    @Before
    fun setUp() {
        mMockWebServer = MockWebServer()
        val mRetrofit = Retrofit.Builder()
            .baseUrl(mMockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        mApiTest = mRetrofit.create(ApiApp::class.java)
    }

    @After
    fun shutDown() {
        mMockWebServer.shutdown()
    }

    @Test
    fun getAllBreedList() = runBlocking {

        val bookTest =  listOf<Book>(
            Book(id=1, author="Og Madino",country = "EEUU",imageLink = "https://images.cdn3.buscalibre.com/fit-in/360x360/06/90/06900534009e449d94bdc11448097ab4.jpg",
            language="English",title="EL VENDEDOR MAS GRANDEL DEL MUNDO",  ),
            Book( id=2, author="Paola Molina",country = "España",imageLink = "https://images.cdn3.buscalibre.com/fit-in/360x360/06/90/06900534009e449d94bdc11448097ab4.jpg",
                language="Español",title="Ciudad Staelite" )
        )


        mMockWebServer.enqueue(MockResponse().setBody(Gson().toJson(bookTest)))
        // when
        val result = mApiTest.fetchAllBooks() // APLICADA A LA FUNCION DE OBTENER LOS LIBROS
        //then
        assertThat(result).isNotNull()
        val body = result.body()
        if (body != null) {
            assertThat(body).hasSize(2)

        }


    }

















}